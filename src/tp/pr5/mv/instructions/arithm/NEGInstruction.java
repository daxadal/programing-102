package tp.pr5.mv.instructions.arithm;

import java.util.Stack;

import tp.pr5.mv.InMethod;
import tp.pr5.mv.Instruction;
import tp.pr5.mv.Memory;
import tp.pr5.mv.OperandStack;
import tp.pr5.mv.OutMethod;
import tp.pr5.mv.ProgramCounter;
import tp.pr5.mv.instructions.NoParamInstruction;
import tp.pr5.mv.traps.MVTrap;


public class NEGInstruction extends NoParamInstruction {

	public NEGInstruction() {}

	public Instruction create() {
		return new NEGInstruction();
	}
	
	public String getOpcode() {
		return opcode;
	}
	
	public void execute(Memory mem, OperandStack stack, ProgramCounter pc, InMethod entrada, OutMethod salida, Stack<Integer> retStack) throws MVTrap {
		if( stack.isEmpty() )
			throw new MVTrap("Error ejecutando " + this.toString() + ": faltan operandos en la pila (hay " + stack.getNumElements()+ ")");
		else {
			int value = stack.top();
			stack.pop();
			
			stack.push(-value);
			pc.increment();
		}
	}
	
	private static final String opcode = "NEG";
}
