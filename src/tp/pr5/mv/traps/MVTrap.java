package tp.pr5.mv.traps;

@SuppressWarnings("serial")
public class MVTrap extends Exception {

	public MVTrap() {
		super();
	}
	
	public MVTrap(String mensaje) {
		super(mensaje);
	}
	
	public MVTrap(Throwable th) {
		super(th);
	}
	
	public MVTrap(String mensaje, Throwable th) {
		super(mensaje, th);
	}
	
}
