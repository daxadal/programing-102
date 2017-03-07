package tp.pr5.mv.command;

import tp.pr5.mv.CPU;

public class WRITECommand extends CommandInterpreter {

	public WRITECommand(int param1, int param2){
		this.param1 = param1; //Posicion
		this.param2 = param2; //Valor
	}
	
	public void execute(CPU cpu){
		if(this.param1 >= 0) { //Posici�n positiva
			cpu.getMemory().write(this.param1, this.param2);
		}
	}
	
	public CommandInterpreter parse(String mnemo, String param1, String param2) {
		if (mnemo.equals("WRITE")) {
			try {
				int param_value1 = Integer.parseInt(param1);	
				int param_value2 = Integer.parseInt(param2);	
				return new WRITECommand(param_value1, param_value2);
			} catch (NumberFormatException ex) {
				return null;
			}
		}
		else return null;
	}

}
