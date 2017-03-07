package tp.pr5.mv;

public class ProgramCounter {

	public ProgramCounter() {
		this.pc = 0;
	}
	
	public int getPC() {
		return this.pc;
	}
	
	public void increment() {
		this.pc++;
	}
	
	public void reset() {
		this.pc = 0;
	}
	
	public void setPC(int newPC) {
		this.pc = newPC;
	}
	
	public void addToPC(int param) {
		this.pc += param;
	}
	
	private int pc;
}
