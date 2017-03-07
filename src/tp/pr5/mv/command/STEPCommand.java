package tp.pr5.mv.command;

import tp.pr5.mv.CPU;
import tp.pr5.mv.traps.MVTrap;

public class STEPCommand extends CommandInterpreter{
	
	public STEPCommand() {}
	
	public void execute(CPU cpu){
		try {
			cpu.step();
		} catch (MVTrap e) {

		}
	}
	
	public CommandInterpreter parse(String mnemo, String param1, String param2) {
		if(mnemo.equals("STEP") && param1 == null && param2 == null) return new STEPCommand();
		else return null;
	}
}
