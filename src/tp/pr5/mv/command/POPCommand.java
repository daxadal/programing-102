package tp.pr5.mv.command;

import tp.pr5.mv.CPU;

public class POPCommand extends CommandInterpreter {

	public POPCommand() {}
	
	public void execute(CPU cpu){
		if(!cpu.getOperandStack().isEmpty()) {
			cpu.getOperandStack().pop();
		}
	}

	public CommandInterpreter parse(String mnemo, String param1, String param2) {
		if(mnemo.equals("POP") && param1 == null && param2 == null) return new POPCommand();
		else return null;
	}

}
