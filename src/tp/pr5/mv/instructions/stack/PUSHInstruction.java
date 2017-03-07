package tp.pr5.mv.instructions.stack;

import java.util.Stack;

import tp.pr5.mv.InMethod;
import tp.pr5.mv.Instruction;
import tp.pr5.mv.Memory;
import tp.pr5.mv.OperandStack;
import tp.pr5.mv.OutMethod;
import tp.pr5.mv.ProgramCounter;
import tp.pr5.mv.instructions.OneParamInstruction;
import tp.pr5.mv.traps.MVTrap;

public class PUSHInstruction extends OneParamInstruction {
	
	public PUSHInstruction(int param) {
		this.param = param;
	}
	
	public Instruction create(int param) {
		return new PUSHInstruction(param);
	}
	
	public String getOpcode() {
		return opcode;
	}
	
	public void execute(Memory mem, OperandStack stack, ProgramCounter pc, InMethod entrada, OutMethod salida, Stack<Integer> retStack) throws MVTrap {
		stack.push(param);
		pc.increment();
	}
	
	private static final String opcode = "PUSH";
}
