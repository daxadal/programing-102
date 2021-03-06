package tp.pr5.mv.instructions.mem;

import java.util.Stack;

import tp.pr5.mv.InMethod;
import tp.pr5.mv.Instruction;
import tp.pr5.mv.Memory;
import tp.pr5.mv.OperandStack;
import tp.pr5.mv.OutMethod;
import tp.pr5.mv.ProgramCounter;
import tp.pr5.mv.instructions.NoParamInstruction;
import tp.pr5.mv.traps.MVTrap;

public class STOREINDInstruction extends NoParamInstruction{

	public STOREINDInstruction() {}
	
	public Instruction create() {
		return new STOREINDInstruction();
	}
	
	public String getOpcode() {
		return opcode;
	}
	
	public void execute(Memory mem, OperandStack stack, ProgramCounter pc, InMethod entrada, OutMethod salida, Stack<Integer> retStack) throws MVTrap {
		if(!stack.hayDosOperandos())
			throw new MVTrap("Error ejecutando " + this.toString() + ": faltan operandos en la pila (hay " + stack.getNumElements() + ")");
		
		if(!mem.canRead(stack.subtop()))
			throw new MVTrap("Error ejecutando " + this.toString() + ": dirección incorrecta (" + stack.subtop() + ")");
			
		mem.write(stack.subtop(), stack.top());		//Opera con la cima y la subcima (cima = dato, subcima = pos)
		stack.pop();	
		stack.pop();	
		
		pc.increment();
	}
	
	private static final String opcode = "STOREIND";
}
