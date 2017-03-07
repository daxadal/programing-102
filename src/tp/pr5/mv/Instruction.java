package tp.pr5.mv;

import java.util.Stack;

import tp.pr5.mv.instructions.HALTInstruction;
import tp.pr5.mv.instructions.arithm.ADDInstruction;
import tp.pr5.mv.instructions.arithm.DIVInstruction;
import tp.pr5.mv.instructions.arithm.MULInstruction;
import tp.pr5.mv.instructions.arithm.NEGInstruction;
import tp.pr5.mv.instructions.arithm.SUBInstruction;
import tp.pr5.mv.instructions.cmp.EQInstruction;
import tp.pr5.mv.instructions.cmp.GTInstruction;
import tp.pr5.mv.instructions.cmp.LEInstruction;
import tp.pr5.mv.instructions.cmp.LTInstruction;
import tp.pr5.mv.instructions.io.INInstruction;
import tp.pr5.mv.instructions.io.OUTInstruction;
import tp.pr5.mv.instructions.jmp.BFInstruction;
import tp.pr5.mv.instructions.jmp.BTInstruction;
import tp.pr5.mv.instructions.jmp.JUMPINDInstruction;
import tp.pr5.mv.instructions.jmp.JUMPInstruction;
import tp.pr5.mv.instructions.jmp.LKDJUMPInstruction;
import tp.pr5.mv.instructions.jmp.RBFInstruction;
import tp.pr5.mv.instructions.jmp.RBTInstruction;
import tp.pr5.mv.instructions.jmp.RETURNInstruction;
import tp.pr5.mv.instructions.jmp.RJUMPInstruction;
import tp.pr5.mv.instructions.logic.ANDInstruction;
import tp.pr5.mv.instructions.logic.NOTInstruction;
import tp.pr5.mv.instructions.logic.ORInstruction;
import tp.pr5.mv.instructions.mem.LOADINDInstruction;
import tp.pr5.mv.instructions.mem.LOADInstruction;
import tp.pr5.mv.instructions.mem.STOREINDInstruction;
import tp.pr5.mv.instructions.mem.STOREInstruction;
import tp.pr5.mv.instructions.mem.WRITEInstruction;
import tp.pr5.mv.instructions.stack.DUPInstruction;
import tp.pr5.mv.instructions.stack.FLIPInstruction;
import tp.pr5.mv.instructions.stack.POPInstruction;
import tp.pr5.mv.instructions.stack.PUSHInstruction;
import tp.pr5.mv.traps.MVTrap;

public abstract class Instruction {
	
	//Mï¿½todos
	public abstract void execute(Memory mem, OperandStack stack, ProgramCounter pc, InMethod entrada, OutMethod salida, Stack<Integer> retStack) throws MVTrap;
	
	public abstract Instruction parse(String mnemo, String param, String param2);
	
	public abstract String getOpcode();

	public int getParam() {
		return this.param;
	}
	
	public int getParam2() {
		return this.param2;
	}
	
	public abstract String toString();
	
	//Getters
	public static Instruction[] getInstructionSet() {
		return instructionSet;
	}
	
	public static int getInstructionSetLength() {
		return instructionSet.length;
	}
	
	//Atributos
	private static Instruction[] instructionSet = {
		//Arithm
		new ADDInstruction(), new SUBInstruction(), new MULInstruction(), new DIVInstruction(), new NEGInstruction(),
		//Logic
		new ANDInstruction(), new ORInstruction(), new NOTInstruction(),
		//Compare
		new EQInstruction(), new GTInstruction(), new LTInstruction(), new LEInstruction(),
		//Branch-Jump
		new JUMPInstruction(0), new BTInstruction(0), new BFInstruction(0),
		new RJUMPInstruction(0), new RBTInstruction(0), new RBFInstruction(0),
		new JUMPINDInstruction(),
		new LKDJUMPInstruction(0), new RETURNInstruction(),
		//Stack
		new DUPInstruction(), new FLIPInstruction(), new POPInstruction(), new PUSHInstruction(0),
		//In-Out
		new OUTInstruction(), new INInstruction(),
		//Memory
		new LOADInstruction(0), new STOREInstruction(0),
		new LOADINDInstruction(), new STOREINDInstruction(),
		new WRITEInstruction(0,0),
		//Halt
		new HALTInstruction()
	};
	protected int param;
	protected int param2;
}
