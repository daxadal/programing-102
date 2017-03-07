package tp.pr5.mv.instructions.logic;

import tp.pr5.mv.Instruction;
import tp.pr5.mv.instructions.BinaryInstruction;
import tp.pr5.mv.traps.MVTrap;

public class ORInstruction extends BinaryInstruction {
	
	public ORInstruction() {}
	
	public Instruction create() {
		return new ORInstruction();
	}
	
	public String getOpcode() {
		return opcode;
	}
	
	public int operate(int sum2, int sum1) throws MVTrap {
		if(sum2 == 0 && sum1 == 0) return 0;
		else return 1;
	}
	
	private static final String opcode = "OR";
}
