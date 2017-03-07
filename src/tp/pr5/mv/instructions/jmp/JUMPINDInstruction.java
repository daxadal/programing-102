package tp.pr5.mv.instructions.jmp;

import java.util.Stack;

import tp.pr5.mv.InMethod;
import tp.pr5.mv.Instruction;
import tp.pr5.mv.Memory;
import tp.pr5.mv.OperandStack;
import tp.pr5.mv.OutMethod;
import tp.pr5.mv.ProgramCounter;
import tp.pr5.mv.instructions.NoParamInstruction;
import tp.pr5.mv.traps.MVTrap;

public class JUMPINDInstruction extends NoParamInstruction{

	public JUMPINDInstruction() {}
	
	public Instruction create() {
		return new JUMPINDInstruction();
	}
	
	public String getOpcode() {
		return opcode;
	}
	
	public void execute(Memory mem, OperandStack stack, ProgramCounter pc, InMethod entrada, OutMethod salida, Stack<Integer> retStack) throws MVTrap {
		if(stack.isEmpty())
			throw new MVTrap("Error ejecutando " + this.toString() + ": faltan operandos en la pila (hay " + stack.getNumElements() + ")");
		
		pc.setPC(stack.top());
		stack.pop();
	}
	
	private static final String opcode = "JUMPIND";
}
