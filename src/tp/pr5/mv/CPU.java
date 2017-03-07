package tp.pr5.mv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;

import tp.pr5.mv.io.InKeyboard;
import tp.pr5.mv.io.OutOnScreen;
import tp.pr5.mv.traps.MVTrap;


public class CPU extends Observable<CPU.Observer>{
	
	public static interface Observer {
		void onReset(Instruction[] program);
		void onAnarchicJump(ProgramCounter pc);
		void onHalt();
		void onSwitchStack();
		void onTrap(Instruction instr, String msg);
		
		void onStartInstrExecution(Instruction instr);
		void onEndInstrExecution(Instruction instr, Memory mem, OperandStack ops, ProgramCounter pc);
		
		void onStartRun();
		void onEndRun(boolean aborted);
	}
	
	//Constructor
	public CPU() {
		this.memoria = new Memory();
		this.pila = new OperandStack();
		this.pilaDormida = new OperandStack();
		this.pc = new ProgramCounter();
		this.program = new ProgramMV();
		this.entrada = new InKeyboard();	//Entrada por defecto
		this.salida = new OutOnScreen();	//Salida por defecto
		this.mode = Mode.BATCH;		//Modo batch por defecto
		this.running = false;
		this.returnStack = new Stack<Integer>();
	}
	
	//Métodos públicos
	public void loadProgram(ProgramMV prog) {
		this.program = prog;
		this.pc.reset();	
		this.pila.reset();
		this.pilaDormida.reset();
		this.memoria.reset();
		
		for(Observer o: obs)o.onReset(this.getInstructionArray());
	}

	public boolean isHalted() {
		return (this.pc.getPC() < 0 || this.program.getNumInstrs() <= this.pc.getPC());
	}
	
	public boolean step() throws MVTrap {	
		if(!isHalted()) {	
			Instruction instr = this.getNextInstruction();
			
			for(Observer o: obs) o.onStartInstrExecution(instr);
			try {
				instr.execute(memoria, pila, pc, entrada, salida, returnStack);
				for(Observer o: obs) o.onEndInstrExecution(instr, memoria, pila, pc);
			} catch (MVTrap ex) {
				for(Observer o: obs) o.onTrap(instr, ex.getMessage());
				
				throw ex;
			}
			
			if(isHalted()) for(Observer o: obs) o.onHalt();
			return true;
		}
	
		else return false;
	}
	
	public void run(int delayInMsecs) throws MVTrap {
		for(Observer o: obs) o.onStartRun();
		
		try {
			while (!isHalted() && this.running) {
				this.step();
				try {
					Thread.sleep(delayInMsecs);
				} catch (InterruptedException e) {}
			}
			
			if(isHalted()) for(Observer o: obs) o.onEndRun(false); 
			else for(Observer o: obs) o.onEndRun(true); 
		
		} catch (MVTrap ex) { 
			for(Observer o: obs) o.onEndRun(false);
			throw ex; 
		} 
	}
	
	public void anarchicJump(int to) {
		pc.setPC(to);
		for(Observer o: obs) o.onAnarchicJump(pc);
	}
	
	public boolean save() {
		boolean ok;
		try {
			saveMemory();
			saveStack();
			saveProgram();
			savePC();
			ok = true;
		} catch (IOException ex) {
			ok = false;
		}
		return ok;
	}
	
	private void saveMemory() throws IOException {
		PrintWriter salida = new PrintWriter (new FileWriter(memoryFile));
		int[] writtenPos = this.memoria.writtenPos();
		for (int i=0; i<writtenPos.length; i++) {
			salida.print(writtenPos[i]);
			salida.print(" ");
			salida.print(this.memoria.read(writtenPos[i]));
			salida.println();
		}
		salida.close();
	}
	
	private void saveStack() throws IOException {
		PrintWriter salida = new PrintWriter (new FileWriter(stackFile));
		int tam = this.pila.getNumElements();
		for (int i=0; i<tam; i++) {
			salida.println(this.pila.getElementAt(i));
		}
		salida.close();
	}
	
	private void saveProgram() throws IOException {
		PrintWriter salida = new PrintWriter (new FileWriter(programFile));
		int tam = this.program.getNumInstrs();
		for (int i=0; i<tam; i++) {
			salida.println(this.program.getInstrAt(i).toString());
		}
		salida.close();
	}
	
	private void savePC() throws IOException {
		PrintWriter salida = new PrintWriter (new FileWriter(pcFile));
		salida.println(pc.getPC());
		salida.close();
	}
	
	public boolean load() {
		boolean ok;
		try {
			loadMemory();
			loadStack();
			loadProgram();
			loadPC();
			ok = true;
		} catch (IOException ex) {
			ok = false;
		}
		return ok;
	}
	
	private void loadMemory() throws FileNotFoundException {
		Scanner entrada = new Scanner(new FileInputStream(memoryFile));
		this.memoria.reset();
		int pos, valor;
		while (entrada.hasNext()) {
			pos = entrada.nextInt();
			valor = entrada.nextInt();
			this.memoria.write(pos, valor);
		}
		entrada.close();
	}
	
	private void loadStack()throws FileNotFoundException {
		Scanner entrada = new Scanner(new FileInputStream(stackFile));
		this.pila.reset();
		int dato;
		while (entrada.hasNext()) {
			dato = entrada.nextInt();
			this.pila.push(dato);
		}
		entrada.close();
	}
	
	private void loadProgram() throws FileNotFoundException, IOException {
		Scanner entrada = new Scanner(new FileReader(programFile));
		ProgramMV program = new ProgramMV();
		Instruction instruction;
		String line;	
		do {
			line = entrada.nextLine();
			
			if(line.split(";").length > 0) {	//Comprobación de array no vacío
				line = line.split(";")[0];
				line = line.trim();
			}
			
			else 
				line = "";	
				
			if(!line.equals("")) {
				instruction = InstructionParser.parse(line);
				if (instruction == null) {
					entrada.close();
					throw new IOException("Error en el programa. Linea: " + line);
				}
				else
					program.addInstruction(instruction);
			}
		} while (entrada.hasNextLine());
			
		entrada.close();
		this.program = program;
		for(Observer o: obs) o.onReset(getInstructionArray());
	}
	
	private void loadPC() throws FileNotFoundException {
		Scanner entrada = new Scanner(new FileInputStream(pcFile));
		pc.setPC(entrada.nextInt());
		entrada.close();
		for(Observer o: obs) o.onAnarchicJump(pc);
	}
	
	public void switchPila() {
		OperandStack aux = pila;
		pila = pilaDormida;
		pilaDormida = aux;
		for(Observer o: obs) o.onSwitchStack();
	}
	
	//Getters-setters
	public OperandStack getOperandStack() {return pila;}
	public OperandStack getOperandStack2() {return pilaDormida;}

	public Memory getMemory() {return memoria;}

	public ProgramCounter getPC() {return pc;}
	
	public InMethod getInMethod() {return this.entrada;}
	public void setInMethod(InMethod in) {this.entrada = in;}
	
	public OutMethod getOutMethod() {return this.salida;}
	public void setOutMethod(OutMethod out) {this.salida = out;}
	
	public Instruction getNextInstruction() {return this.program.getInstrAt(this.pc.getPC());}
	
	public ProgramMV getProgram() {return program;}
	
	public Instruction[] getInstructionArray() {
		Instruction[] programArray = new Instruction[this.program.getNumInstrs()];
		for (int i=0; i<this.program.getNumInstrs(); i++)
			programArray[i] = this.program.getInstrAt(i);
		return programArray;
	}

	public Mode getMode() {return this.mode;}
	public void setMode(Mode m) {this.mode = m;}

	public void setRunning(boolean b) {this.running = b;}
	
	//Atributos
	private Memory memoria;
	private OperandStack pila; //Pila utilizada
	private OperandStack pilaDormida; //Pila en espera
	private ProgramCounter pc;
	private ProgramMV program;
	private InMethod entrada;
	private OutMethod salida;
	private Mode mode;
	private boolean running;
	private Stack<Integer> returnStack;
	
	//Archivos
	private static final String memoryFile = "savedMemory.txt";
	private static final String stackFile = "savedStack.txt";
	private static final String programFile = "savedProgram.txt";
	private static final String pcFile = "savedPC.txt";
	
	
}
