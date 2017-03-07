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

public class LKDJUMPInstruction extends OneParamInstruction {

	public LKDJUMPInstruction(int param) {
		this.param = param;
	}
	
	public Instruction create(int param) {
		return new LKDJUMPInstruction(param);
	}
	
	public String getOpcode() {
		return opcode;
	}
	
	public void execute(Memory mem, OperandStack stack, ProgramCounter pc, InMethod entrada, OutMethod salida, Stack<Integer> retStack) throws MVTrap {
		retStack.push(pc.getPC()); //guardar dirección de vuelta
		pc.setPC(getParam()); //saltar
	}
	
	private static final String opcode = "LKDJUMP";

}
