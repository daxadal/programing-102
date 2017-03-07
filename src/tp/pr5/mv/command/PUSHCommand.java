package tp.pr5.mv.command;

import tp.pr5.mv.CPU;

public class PUSHCommand extends CommandInterpreter {

	public PUSHCommand(int param1) {
		this.param1 = param1;
	}
	
	public void execute(CPU cpu){
		cpu.getOperandStack().push(param1);
	}

	public CommandInterpreter parse(String mnemo, String param1, String param2) {
		if (mnemo.equals("PUSH") && param2 == null) {
			try {
				int param_value = Integer.parseInt(param1);	
				return new PUSHCommand(param_value);
			} catch (NumberFormatException ex) {
				return null;
			}
		}
		else return null;
	}
}
