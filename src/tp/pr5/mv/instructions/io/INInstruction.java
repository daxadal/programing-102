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

public class INInstruction extends NoParamInstruction{

	public INInstruction() {}
	
	public Instruction create() {
		return new INInstruction();
	}
	
	public String getOpcode() {
		return opcode;
	}
	
	public void execute(Memory mem, OperandStack stack, ProgramCounter pc, InMethod entrada, OutMethod salida, Stack<Integer> retStack) throws MVTrap {
			int c;
			try {
				c = entrada.readChar();
				stack.push(c);
				pc.increment();
			} catch (IOException ex) {
				throw new MVTrap(ex);
			}
			
	}
	
	private static final String opcode = "IN";
}
