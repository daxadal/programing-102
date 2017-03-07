package tp.pr5.mv;

public class RunThread extends Thread {
	public RunThread(ControllerWindow controller) {
		this.controller = controller;
	}
	
	public void run() {
		controller.executeRun();
	}
	
	private ControllerWindow controller;
}
