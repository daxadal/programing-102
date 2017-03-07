package tp.pr5.mv.instructions;

import java.util.Stack;

import tp.pr5.mv.InMethod;
import tp.pr5.mv.Instruction;
import tp.pr5.mv.Memory;
import tp.pr5.mv.OperandStack;
import tp.pr5.mv.OutMethod;
import tp.pr5.mv.ProgramCounter;
import tp.pr5.mv.traps.MVTrap;

public abstract class BinaryInstruction extends NoParamInstruction {
	
	public abstract int operate(int sum2, int sum1) throws MVTrap;
	
	public abstract Instruction create();
	
	public void execute(Memory mem, OperandStack stack, ProgramCounter pc, InMethod entrada, OutMethod salida, Stack<Integer> retStack) throws MVTrap {
		
		if (!stack.hayDosOperandos())
			throw new MVTrap("Error ejecutando " + this.toString() + ": faltan operandos en la pila (hay " + stack.getNumElements()+ ")");
		else {
			int sum1 = stack.top();
			int sum2 = stack.subtop();
			
			int resultado = operate(sum2, sum1);
			
			stack.pop();
			stack.pop();
			stack.push(resultado);
			
			pc.increment();
		}
	}
}
