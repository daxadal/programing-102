package tp.pr5.mv.instructions;

import java.util.Stack;

import tp.pr5.mv.InMethod;
import tp.pr5.mv.Instruction;
import tp.pr5.mv.Memory;
import tp.pr5.mv.OperandStack;
import tp.pr5.mv.OutMethod;
import tp.pr5.mv.ProgramCounter;
import tp.pr5.mv.traps.MVTrap;

public abstract class OneParamInstruction extends Instruction {

	public Instruction parse(String mnemo, String param, String param2) {
		if (mnemo.equals(this.getOpcode()) && param2 == null) {
			try {
				int param_value = Integer.parseInt(param);
				return this.create(param_value);
			} catch (NumberFormatException ex) {
				return null;
			}
		}
		else return null;
	}
	
	public abstract Instruction create(int param);

	public abstract void execute(Memory mem, OperandStack stack, ProgramCounter pc, InMethod entrada, OutMethod salida, Stack<Integer> retStack) throws MVTrap;
	
	public abstract String getOpcode();
	
	public String toString() {
		return this.getOpcode() + " " + this.getParam();
	}
}
