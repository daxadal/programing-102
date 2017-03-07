package tp.pr5.mv.instructions;

import java.util.Stack;

import tp.pr5.mv.InMethod;
import tp.pr5.mv.Instruction;
import tp.pr5.mv.Memory;
import tp.pr5.mv.OperandStack;
import tp.pr5.mv.OutMethod;
import tp.pr5.mv.ProgramCounter;
import tp.pr5.mv.traps.MVTrap;

public class HALTInstruction extends NoParamInstruction{

	public HALTInstruction() {}
	
	public Instruction create() {
		return new HALTInstruction();
	}
	
	public String getOpcode() {
		return opcode;
	}

	public void execute(Memory mem, OperandStack stack, ProgramCounter pc, InMethod entrada, OutMethod salida, Stack<Integer> retStack) throws MVTrap {
		pc.setPC(-1);
	}
	
	private static final String opcode = "HALT";
}
