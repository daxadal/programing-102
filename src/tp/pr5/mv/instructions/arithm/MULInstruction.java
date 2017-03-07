package tp.pr5.mv.instructions.arithm;

import tp.pr5.mv.Instruction;
import tp.pr5.mv.instructions.BinaryInstruction;
import tp.pr5.mv.traps.MVTrap;

public class MULInstruction extends BinaryInstruction {
	
	public MULInstruction() {}
	
	public Instruction create() {
		return new MULInstruction();
	}
	
	public String getOpcode() {
		return opcode;
	}
	
	public int operate(int sum2, int sum1) throws MVTrap {
		return sum2*sum1;
	}
	
	private static final String opcode = "MUL";
}
