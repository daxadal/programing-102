package tp.pr5.mv.command;

import tp.pr5.mv.CPU;
import tp.pr5.mv.traps.MVTrap;

public class RUNCommand extends CommandInterpreter {

	public RUNCommand(int delay){
		this.delay = delay;
	}
	
	public void execute(CPU cpu) {
		try {
			cpu.run(delay);
		} catch (MVTrap e) {
			
		}
	}
	
	public CommandInterpreter parse(String mnemo, String param1, String param2) {
		if(mnemo.equals("RUN") && param1 == null && param2 == null) return new RUNCommand(700);
		else return null;
	}

	private int delay;
}
