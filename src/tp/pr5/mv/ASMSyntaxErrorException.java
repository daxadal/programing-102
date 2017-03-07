package tp.pr5.mv;

@SuppressWarnings("serial")
public class ASMSyntaxErrorException extends Exception {
	public ASMSyntaxErrorException() {
		super();
	}
	
	public ASMSyntaxErrorException(String mensaje) {
		super(mensaje);
	}
	
	public ASMSyntaxErrorException(Throwable th) {
		super(th);
	}
	
	public ASMSyntaxErrorException(String mensaje, Throwable th) {
		super(mensaje, th);
	}
}
