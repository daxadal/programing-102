package tp.pr5.mv.instructions;

import java.util.Stack;

import tp.pr5.mv.InMethod;
import tp.pr5.mv.Instruction;
import tp.pr5.mv.Memory;
import tp.pr5.mv.OperandStack;
import tp.pr5.mv.OutMethod;
import tp.pr5.mv.ProgramCounter;
import tp.pr5.mv.traps.MVTrap;

public abstract class TwoParamInstruction extends Instruction {

	public Instruction parse(String mnemo, String param, String param2) {
		if (mnemo.equals(this.getOpcode())) {
			try {
				int param_value = Integer.parseInt(param);
				int param2_value = Integer.parseInt(param2);
				return this.create(param_value, param2_value);
			} catch (NumberFormatException ex) {
				return null;
			}
		}
		else return null;
	}
	
	public abstract Instruction create(int param, int param2);

	public abstract void execute(Memory mem, OperandStack stack, ProgramCounter pc, InMethod entrada, OutMethod salida, Stack<Integer> retStack) throws MVTrap;
	
	public abstract String getOpcode();
	
	public String toString() {
		return this.getOpcode() + " " + this.getParam() + " " + this.getParam2();
	}

}
