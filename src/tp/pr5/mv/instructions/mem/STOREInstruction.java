package tp.pr5.mv.instructions.mem;

import java.util.Stack;

import tp.pr5.mv.InMethod;
import tp.pr5.mv.Instruction;
import tp.pr5.mv.Memory;
import tp.pr5.mv.OperandStack;
import tp.pr5.mv.OutMethod;
import tp.pr5.mv.ProgramCounter;
import tp.pr5.mv.instructions.OneParamInstruction;
import tp.pr5.mv.traps.MVTrap;

public class STOREInstruction extends OneParamInstruction {

	public STOREInstruction(int param) {
		this.param = param;
	}
	
	public Instruction create(int param) {
		return new STOREInstruction(param);
	}
	
	public String getOpcode() {
		return opcode;
	}
	
	public void execute(Memory mem, OperandStack stack, ProgramCounter pc, InMethod entrada, OutMethod salida, Stack<Integer> retStack) throws MVTrap {
		if(stack.isEmpty())
			throw new MVTrap("Error ejecutando " + this.toString() + ": faltan operandos en la pila (hay " + stack.getNumElements() + ")");
		
		if(this.param < 0) 
			throw new MVTrap("Error ejecutando " + this.toString() + ": dirección incorrecta (" + this.param + ")");
		
		mem.write(this.param, stack.top());
		stack.pop();
			
		pc.increment();
	}
	
	private static final String opcode = "STORE";
}