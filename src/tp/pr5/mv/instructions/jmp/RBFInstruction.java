package tp.pr5.mv.instructions.jmp;

import java.util.Stack;

import tp.pr5.mv.InMethod;
import tp.pr5.mv.Instruction;
import tp.pr5.mv.Memory;
import tp.pr5.mv.OperandStack;
import tp.pr5.mv.OutMethod;
import tp.pr5.mv.ProgramCounter;
import tp.pr5.mv.instructions.OneParamInstruction;
import tp.pr5.mv.traps.MVTrap;

public class RBFInstruction extends OneParamInstruction{
	public RBFInstruction(int param) {
		this.param = param;
	}
	
	public Instruction create(int param) {
		return new RBFInstruction(param);
	}
	
	public String getOpcode() {
		return opcode;
	}
	
	public void execute(Memory mem, OperandStack stack, ProgramCounter pc, InMethod entrada, OutMethod salida, Stack<Integer> retStack) throws MVTrap {
		if (stack.top() == 0)
			pc.addToPC(this.param);
		else
			pc.increment();
		
		stack.pop();	
	}
	
	private static final String opcode = "RBF";
}
