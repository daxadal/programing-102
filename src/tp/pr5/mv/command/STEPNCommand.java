package tp.pr5.mv.command;

import tp.pr5.mv.CPU;
import tp.pr5.mv.traps.MVTrap;

public class STEPNCommand extends CommandInterpreter{

	public STEPNCommand(int param1){
		this.param1 = param1;
	}
	
	public void execute(CPU cpu){
		try {
			int i=0;
			while (i<this.param1 && !cpu.isHalted()) {
				cpu.step();
				i++;
			}
		} catch (MVTrap e) {

		}
	}
	
	public CommandInterpreter parse(String mnemo, String param1, String param2) {
		if (mnemo.equals("STEP") && param2 == null) {
			try {
				int param_value = Integer.parseInt(param1);	
				return new STEPNCommand(param_value);
			} catch (NumberFormatException ex) {
				return null;
			}
		}
		else return null;
	}
}
