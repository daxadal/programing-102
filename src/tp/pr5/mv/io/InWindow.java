package tp.pr5.mv.io;

import java.io.IOException;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import tp.pr5.mv.InMethod;

public class InWindow implements InMethod {

	public InWindow(JTextArea textArea, InMethod in) { //El fichero viene ya abierto
		this.textArea = textArea;
		this.puntero = 0;
		this.punteroExcepc = 0;
		this.ex = null;
		
		this.inText = new StringBuilder();

		try { //Intenta leer el fichero del tiron
			int c = in.readChar();
			while (c != -1) {
				inText.append((char)c);
				punteroExcepc++;
				c = in.readChar();
			}
			punteroExcepc = -2; //No ha saltado excepci�n
		} catch (IOException ex){
			this.ex = ex; //Guarda la excepcion pala lanzarla en el momento apropiado
		}
		
		this.textArea.setText(inText.toString());
	}
	
	@Override
	public int readChar() throws IOException {
		
		
		
		
		
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					
				}
			}
		);
		if (puntero == punteroExcepc) throw this.ex; //Al leer esa posici�n se gener� la excepcion
		
		else if (puntero < inText.length()) {
			int c = inText.charAt(puntero);
			if(puntero < inText.length() && inText.charAt(puntero) != '\n') 
				inText = inText.replace(puntero, puntero+1, "*");
				
			puntero++;
			
			SwingUtilities.invokeLater(
				new Runnable() {
					public void run() {
						textArea.setText(inText.toString());
					}
				}
			);
			
			return c;
		}
		else 
			return -1;
		
	}
	
	private StringBuilder inText;
	private int puntero;
	private JTextArea textArea;
	private int punteroExcepc;
	private IOException ex;
}
