package tp.pr5.mv.instructions.stack;

import java.util.Stack;

import tp.pr5.mv.InMethod;
import tp.pr5.mv.Instruction;
import tp.pr5.mv.Memory;
import tp.pr5.mv.OperandStack;
import tp.pr5.mv.OutMethod;
import tp.pr5.mv.ProgramCounter;
import tp.pr5.mv.instructions.NoParamInstruction;
import tp.pr5.mv.traps.MVTrap;


public class FLIPInstruction extends NoParamInstruction {

	public FLIPInstruction() {}

	public Instruction create() {
		return new FLIPInstruction();
	}
	
	public String getOpcode() {
		return opcode;
	}
	
	public void execute(Memory mem, OperandStack stack, ProgramCounter pc, InMethod entrada, OutMethod salida, Stack<Integer> retStack) throws MVTrap {
		if (!stack.hayDosOperandos())
			throw new MVTrap("Error ejecutando " + this.toString() + ": faltan operandos en la pila (hay " + stack.getNumElements()+ ")");
		else {
			int cima = stack.top();
			stack.pop();
			int subcima = stack.top();
			stack.pop();

			stack.push(cima);	//Coloca en la subcima lo que antes era la cima
			stack.push(subcima);
			
			pc.increment();
		}
	}
	
	private static final String opcode = "FLIP";
}