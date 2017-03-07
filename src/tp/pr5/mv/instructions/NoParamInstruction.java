package tp.pr5.mv.instructions;

import java.util.Stack;

import tp.pr5.mv.InMethod;
import tp.pr5.mv.Instruction;
import tp.pr5.mv.Memory;
import tp.pr5.mv.OperandStack;
import tp.pr5.mv.OutMethod;
import tp.pr5.mv.ProgramCounter;
import tp.pr5.mv.traps.MVTrap;

public abstract class NoParamInstruction extends Instruction{

	public Instruction parse(String mnemo, String param, String param2) {
		if(mnemo.equals(this.getOpcode()) && param == null && param2 == null) return this.create();
		else return null;
	}
	
	public abstract Instruction create();

	public abstract void execute(Memory mem, OperandStack stack, ProgramCounter pc, InMethod entrada, OutMethod salida, Stack<Integer> retStack) throws MVTrap;
	
	public abstract String getOpcode();
	
	public String toString() {
		return this.getOpcode();
	}
}
