package tp.pr5.mv.instructions.io;

import java.io.IOException;
import java.util.Stack;

import tp.pr5.mv.InMethod;
import tp.pr5.mv.Instruction;
import tp.pr5.mv.Memory;
import tp.pr5.mv.OperandStack;
import tp.pr5.mv.OutMethod;
import tp.pr5.mv.ProgramCounter;
import tp.pr5.mv.instructions.NoParamInstruction;
import tp.pr5.mv.traps.MVTrap;

public class OUTInstruction extends NoParamInstruction{
	
	public OUTInstruction() {}
	
	public Instruction create() {
		return new OUTInstruction();
	}
	
	public String getOpcode() {
		return opcode;
	}

	public void execute(Memory mem, OperandStack stack, ProgramCounter pc, InMethod entrada, OutMethod salida, Stack<Integer> retStack) throws MVTrap {
		if( stack.isEmpty() )
			throw new MVTrap("Error ejecutando " + this.toString() + ": faltan operandos en la pila (hay " + stack.getNumElements()+ ")");
		else {
			char caracter = (char) stack.top();
			try {
				salida.writeChar(caracter);
				stack.pop();
				pc.increment();
			} catch (IOException ex) {
				throw new MVTrap(ex);
			}
			
		}
	}
	
	private static final String opcode = "OUT";
}
