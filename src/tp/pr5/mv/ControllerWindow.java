package tp.pr5.mv;

import tp.pr5.mv.command.CommandInterpreter;
import tp.pr5.mv.command.POPCommand;
import tp.pr5.mv.command.PUSHCommand;
import tp.pr5.mv.command.RUNCommand;
import tp.pr5.mv.command.STEPCommand;
import tp.pr5.mv.command.STEPNCommand;
import tp.pr5.mv.command.WRITECommand;

public class ControllerWindow{
	
	public ControllerWindow(CPU cpu) {
		this.cpu = cpu;
	}
	
	public void executeStep() {
		CommandInterpreter step = new STEPCommand();
		step.execute(cpu);
	}
	
	public void executeStepN(int num) {
		CommandInterpreter stepN = new STEPNCommand(num);
		stepN.execute(cpu);
	}
	
	public void executeRun() {
		cpu.setRunning(true);
		CommandInterpreter run = new RUNCommand(350);
		run.execute(cpu);
	}
	
	public void executePause() {
		cpu.setRunning(false);
	}
	
	public void executePush(int dato) {
		CommandInterpreter push = new PUSHCommand(dato);
		push.execute(cpu);
	}
	
	public void executePop() {
		CommandInterpreter pop = new POPCommand();
		pop.execute(cpu);
	}
	
	public void executeWrite(int pos, int valor) {
		CommandInterpreter write = new WRITECommand(pos, valor);
		write.execute(cpu);	
	}
	
	public void executeErase(int pos) {
		cpu.getMemory().erase(pos);
	}
	
	public void executeAnarchicJump(int to) {
		cpu.anarchicJump(to);
	}
	
	private CPU cpu;

	public void executeSave() {
		cpu.save();
	}

	public void executeLoad() {
		cpu.load();
	}
	
	public void executeSwitchStack() {
		cpu.switchPila();
	}
}
