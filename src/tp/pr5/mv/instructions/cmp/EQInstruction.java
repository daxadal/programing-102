package tp.pr5.mv.instructions.cmp;

import tp.pr5.mv.Instruction;
import tp.pr5.mv.instructions.BinaryInstruction;
import tp.pr5.mv.traps.MVTrap;

public class EQInstruction extends BinaryInstruction {

	public EQInstruction() {}
	
	public Instruction create() {
		return new EQInstruction();
	}
	
	public String getOpcode() {
		return opcode;
	}
	
	public int operate(int sum2, int sum1) throws MVTrap {
		if(sum2 == sum1) return 1;
		else return 0;
	}

	private static final String opcode = "EQ";
}
