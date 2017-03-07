package tp.pr5.mv;


public class ConsoleInteractive implements CPU.Observer{

	public ConsoleInteractive(Observable<CPU.Observer> cpu){
		cpu.addObserver(this);
	}
	
	@Override
	public void onReset(Instruction[] program) {}

	@Override
	public void onHalt() {}

	@Override
	public void onTrap(Instruction instr, String msg) {
		System.err.println(msg);
	}

	@Override
	public void onStartInstrExecution(Instruction instr) {
		System.out.println("Comienza la ejecución de " + instr);
	}

	@Override
	public void onEndInstrExecution(Instruction instr, Memory mem,
			OperandStack ops, ProgramCounter pc) {
		
		int[] written_pos;
		int numElements;
		
		
		System.out.println("El estado de la máquina tras ejecutar la instrucción es:");
		System.out.print("Memoria:");
			
		//MOSTRAR mem
		written_pos = mem.writtenPos();
		if (written_pos.length == 0)
			System.out.print(" <vacía>");
		else for (int i=0; i< written_pos.length; i++)
			System.out.print(" [" + written_pos[i] + "]:" + mem.read(written_pos[i]));
		System.out.println();
		
		//MOSTRAR ops
		System.out.print("Pila de operandos:");
		numElements = ops.getNumElements();
		if (numElements == 0)
			System.out.print(" <vacía>");
		else for (int i=0; i< numElements; i++)
			System.out.print(" " + ops.getElementAt(i));
		System.out.println();
	}

	@Override
	public void onStartRun() {}

	@Override
	public void onEndRun(boolean aborted) {}

	@Override
	public void onAnarchicJump(ProgramCounter pc) {}

	@Override
	public void onSwitchStack() {}
}
