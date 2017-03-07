package tp.pr5.mv;

import java.util.Scanner;

import tp.pr5.mv.command.CommandInterpreter;
import tp.pr5.mv.command.CommandParser;

public class ControllerInteractive implements Controller {
	
	public ControllerInteractive(CPU cpu) {
		this.cpu = cpu;
	}
	
	public void start(Scanner cin) {
		cpu.setRunning(true);
		
		final String QUIT = "QUIT";
		String line;
	    CommandInterpreter command;
		
		System.out.print("> ");
		line = cin.nextLine();
		
		while (!cpu.isHalted() && !line.trim().equalsIgnoreCase(QUIT)) {
			command = CommandParser.parse(line);
			if (command == null)
				System.out.println("No te entiendo.");
			else {
				command.execute(cpu);
			}
			
			if (!cpu.isHalted()) {
				System.out.print("> ");
				line = cin.nextLine();
			}
		}
	}
	private CPU cpu;
}
