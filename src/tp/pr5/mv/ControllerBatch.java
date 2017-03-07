package tp.pr5.mv;

import java.util.Scanner;

import tp.pr5.mv.command.CommandInterpreter;
import tp.pr5.mv.command.RUNCommand;

public class ControllerBatch implements Controller {

	public ControllerBatch(CPU cpu) {
		this.cpu = cpu;
	}
	
	public void start(Scanner cin) {
		cpu.setRunning(true);
		CommandInterpreter run = new RUNCommand(0);
		run.execute(cpu);
	}

	private CPU cpu;
}
