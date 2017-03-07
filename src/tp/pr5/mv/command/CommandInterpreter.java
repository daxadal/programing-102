package tp.pr5.mv.command;

import tp.pr5.mv.CPU;

public abstract class CommandInterpreter {
	
	//Metodos
	public abstract void execute(CPU cpu);
	
	public abstract CommandInterpreter parse(String mnemo, String param1, String param2);
	
	//Getters
	public int getParam1() {
		return this.param1;
	}
	
	public int getParam2() {
		return this.param2;
	}
	
	public static CommandInterpreter[] getCommandSet() {
		return commandSet;
	}
	
	public static int getCommandSetLength() {
		return commandSet.length;
	}
	
	//Atributos
	private static CommandInterpreter[] commandSet = {
		//Ejecucion
		new STEPCommand(), new STEPNCommand(0), new RUNCommand(0),
		//Cambio de estado
		new PUSHCommand(0), new POPCommand(), new WRITECommand(0,0)		
	};
	protected int param1, param2;
}
