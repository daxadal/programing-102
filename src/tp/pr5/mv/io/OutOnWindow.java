package tp.pr5.mv.io;

import java.io.IOException;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import tp.pr5.mv.OutMethod;

public class OutOnWindow implements OutMethod {
	
	public OutOnWindow(JTextArea textArea, OutMethod out) { //El fichero viene ya abierto
		this.outMethod = out;
		this.outText = new StringBuilder();
		this.textArea = textArea;
	}
	
	@Override
	public void writeChar(char c) throws IOException {
		outText.append(c);
		
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					textArea.setText(outText.toString());
				}
			}
		);
		
		outMethod.writeChar(c);
	}
	
	private StringBuilder outText;
	private JTextArea textArea;
	private OutMethod outMethod;
}
