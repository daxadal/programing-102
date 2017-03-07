package tp.pr5.mv.instructions.arithm;

import tp.pr5.mv.Instruction;
import tp.pr5.mv.instructions.BinaryInstruction;
import tp.pr5.mv.traps.MVTrap;

public class DIVInstruction extends BinaryInstruction {
	
	public DIVInstruction() {}
	
	public Instruction create() {
		return new DIVInstruction();
	}
	
	public String getOpcode() {
		return opcode;
	}
	
	public int operate(int sum2, int sum1) throws MVTrap {
		if (sum1 == 0)
			throw new MVTrap("División por cero");
		return (sum2/sum1);
	}
	
	private static final String opcode = "DIV";
}
