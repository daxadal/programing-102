package tp.pr5.mv.window;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class InPanel extends JPanel{

	public InPanel() throws IOException {
		initGUI();
	}
	
	private void initGUI() {
		this.setBorder(new TitledBorder("Entrada del programa-p"));
		this.setLayout(new BorderLayout());
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Courier new", 0, 12));

		JScrollPane panel = new JScrollPane(textArea);
		panel.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(panel);
	}

	private JTextArea textArea;

	public JTextArea getTextArea() {
		return textArea;
	}
	
}
