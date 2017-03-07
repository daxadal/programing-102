package tp.pr5.mv;

import tp.pr5.mv.CPU.Observer;

public class ConsoleBatch implements Observer {
	
	public ConsoleBatch(Observable<CPU.Observer> cpu){
		cpu.addObserver(this);
	}

	@Override
	public void onReset(Instruction[] program) {}

	@Override
	public void onHalt() {}

	@Override
	public void onTrap(Instruction instr, String msg) {
		System.err.println(msg);
	}

	@Override
	public void onStartInstrExecution(Instruction instr) {}

	@Override
	public void onEndInstrExecution(Instruction instr, Memory mem,
			OperandStack ops, ProgramCounter pc) {}

	@Override
	public void onStartRun() {}

	@Override
	public void onEndRun(boolean aborted) {}

	@Override
	public void onAnarchicJump(ProgramCounter pc) {}

	@Override
	public void onSwitchStack() {}

}
