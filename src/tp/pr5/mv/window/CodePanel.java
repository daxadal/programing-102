package tp.pr5.mv.window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import tp.pr5.mv.ControllerWindow;
import tp.pr5.mv.Instruction;
import tp.pr5.mv.Memory;
import tp.pr5.mv.OperandStack;
import tp.pr5.mv.ProgramCounter;

@SuppressWarnings("serial")
public class CodePanel extends JPanel implements ScrollPaneConstants, tp.pr5.mv.CPU.Observer{
	
	public CodePanel(Instruction[] prog, ControllerWindow controller) {
		this.controller = controller;
		this.instructionSet = new String[prog.length];
		for (int i=0; i<prog.length; i++)
			this.instructionSet[i] = prog[i].toString();
		initGUI(prog);
	}
	
	private void initGUI(Instruction[] prog) {
		//Panel Programa
		this.setBorder(new TitledBorder("Programa"));
		this.setLayout(new BorderLayout());
		
		textArea = new JTextArea();
		textArea.setColumns(20);
		textArea.setEditable(false);
		textArea.setFont(new Font("Courier new", 0, 12));
		
		JScrollPane panel = new JScrollPane(textArea);
		panel.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(panel);
		
		mostrarPrograma(new ProgramCounter());
		
		//Panel AnarchicJump
		JPanel panelJump = new JPanel();
		panelJump.setLayout(new FlowLayout());
		panelJump.add(new JLabel("Dir: "));
		field = new JTextField(4);
		panelJump.add(field);
		JButton jbJump = new JButton("Jump");
		jbJump.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int to = Integer.parseInt(field.getText());
					CodePanel.this.controller.executeAnarchicJump(to);
				} catch (NumberFormatException ex) {
					field.setText("");
					JOptionPane.showMessageDialog(CodePanel.this, "Introduce un entero", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelJump.add(jbJump);
		this.add(panelJump, BorderLayout.SOUTH);
		
		
		
	}
	
	public void mostrarPrograma(ProgramCounter pc) {
		StringBuilder text = new StringBuilder();
		for(int i=0; i<this.instructionSet.length; i++) {
			if (pc.getPC() == i) text.append(" * ");
			else text.append("   ");
			
			text.append(String.format("%4d", i) + ": ");
			text.append(this.instructionSet[i]);
			text.append("\n");
		}
		textArea.setText(text.toString());
	}
	
	private ControllerWindow controller;
	private String[] instructionSet;
	private JTextArea textArea;
	private JTextField field;
	
	///////////////////////////////////
	@Override
	public void onReset(final Instruction[] program) {
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					CodePanel.this.instructionSet = new String[program.length];
					for (int i=0; i<program.length; i++)
					CodePanel.this.instructionSet[i] = program[i].toString();	
				}
			}
		);
		
	}

	@Override
	public void onHalt() {}

	@Override
	public void onTrap(Instruction instr, String msg) {}

	@Override
	public void onStartInstrExecution(Instruction instr) {}

	@Override
	public void onEndInstrExecution(Instruction instr, Memory mem,
			OperandStack ops, final ProgramCounter pc) {
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					CodePanel.this.mostrarPrograma(pc);		
				}
			}
		);
	}

	@Override
	public void onStartRun() {}

	@Override
	public void onEndRun(boolean aborted) {}

	@Override
	public void onAnarchicJump(final ProgramCounter pc) {
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					CodePanel.this.mostrarPrograma(pc);		
				}
			}
		);
	}

	@Override
	public void onSwitchStack() {}
}
