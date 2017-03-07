package tp.pr5.mv.instructions.mem;

import java.util.Stack;

import tp.pr5.mv.InMethod;
import tp.pr5.mv.Instruction;
import tp.pr5.mv.Memory;
import tp.pr5.mv.OperandStack;
import tp.pr5.mv.OutMethod;
import tp.pr5.mv.ProgramCounter;
import tp.pr5.mv.instructions.TwoParamInstruction;
import tp.pr5.mv.traps.MVTrap;

public class WRITEInstruction extends TwoParamInstruction {

	public WRITEInstruction(int param, int param2){
		this.param = param;
		this.param2 = param2;
	}
	
	@Override
	public Instruction create(int param, int param2) {
		return new WRITEInstruction(param, param2);
	}

	@Override
	public void execute(Memory mem, OperandStack stack, ProgramCounter pc,
			InMethod entrada, OutMethod salida, Stack<Integer> retStack) throws MVTrap {
		if(this.param >= 0) { //Posici�n positiva
			mem.write(this.param, this.param2);
		}
		pc.increment();
	}

	public String getOpcode() {
		return opcode;
	}

	private static final String opcode = "WRITE";
}
