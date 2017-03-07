package tp.pr5.mv;


public class ProgramMV {

	public ProgramMV() {
		this.program = new Instruction[TAM_INI];
		this.capacidad = TAM_INI;
		this.contador = 0;		
	}
	
	public int getNumInstrs() {
		return this.contador;
	}
	
	public Instruction getInstrAt(int i) {
		if (i < 0 || this.contador <= i)
			return null;	
		else
			return this.program[i];
	}
	
	public void addInstruction(Instruction instr) {
		if (this.contador == this.capacidad)
			this.redimensionar();
		this.program[contador] = instr;
		this.contador++;
	}
	
	/** Duplica el tama�o del programa */
	private void redimensionar() {
		this.capacidad *= 2;
		Instruction[] new_program = new Instruction[this.capacidad];
		for (int i=0; i<this.contador; i++)
			new_program[i] = this.program[i];
		this.program = new_program;
	}


	private static final int TAM_INI = 5;
	private Instruction[] program;
	private int contador;
	private int capacidad;
}
