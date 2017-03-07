
package tp.pr5.mv.window;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import tp.pr5.mv.ControllerWindow;
import tp.pr5.mv.Instruction;
import tp.pr5.mv.Main;
import tp.pr5.mv.Memory;
import tp.pr5.mv.OperandStack;
import tp.pr5.mv.ProgramCounter;
import tp.pr5.mv.RunThread;
import tp.pr5.mv.Window;

@SuppressWarnings("serial")
public class ButtonPanel extends JPanel implements tp.pr5.mv.CPU.Observer {

	public ButtonPanel(Window window, ControllerWindow controller) {
		initGUI(window);
		this.controller = controller;
	}
	
	private void initGUI(final Window window) {
		this.setLayout(new FlowLayout());
		this.setBorder(new TitledBorder("Acciones"));
		
		URL urlStep = Main.class.getResource("step.png");
		botonStep = new JButton(new ImageIcon(urlStep));
		this.add(botonStep);
		
		botonStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonPanel.this.controller.executeStep();		
			}
		});
		
		this.campoNum = new JTextField(4);
		this.add(new JLabel("Nº Instr: "));
		this.add(campoNum);
		
		URL urlStepN = Main.class.getResource("stepN.png");
		botonStepN = new JButton(new ImageIcon(urlStepN));
		this.add(botonStepN);
		
		botonStepN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int num = Integer.parseInt(campoNum.getText());
					ButtonPanel.this.controller.executeStepN(num);	
				} catch (NumberFormatException ex) {
					campoNum.setText("");
					JOptionPane.showMessageDialog(ButtonPanel.this, "Introduce un entero", "Error", JOptionPane.ERROR_MESSAGE);
				}
					
			}
		});
		
		URL urlRun = Main.class.getResource("run.png");
		botonRun = new JButton(new ImageIcon(urlRun));
		this.add(botonRun);
		
		botonRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RunThread hebra = new RunThread(controller);	
				hebra.start();
			}
		});
		
		URL urlPause = Main.class.getResource("pause.png");
		botonPause = new JButton(new ImageIcon(urlPause));
		botonPause.setEnabled(false);
		this.add(botonPause);
		
		botonPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.executePause();
			}	
		});
		
		URL urlExit = Main.class.getResource("exit.png");
		botonExit = new JButton(new ImageIcon(urlExit));
		this.add(botonExit);
		
		botonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.executePause();
				
				int ret = JOptionPane.showConfirmDialog(window, "Â¿Seguro que desea salir?", "Aviso", JOptionPane.YES_NO_OPTION);
				if (ret == JOptionPane.YES_OPTION) {
					window.setVisible(false);
					window.dispose();
				}
			}	
		});
		
		botonSave = new JButton("Save state");
		this.add(botonSave);
		
		botonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonPanel.this.controller.executeSave();		
			}
		});
		
		botonLoad = new JButton("Load state");
		this.add(botonLoad);
		
		botonLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonPanel.this.controller.executeLoad();		
			}
		});
	}

	public void onReset(Instruction[] program) {}

	public void onHalt() {}

	public void onTrap(Instruction instr, String msg) {}

	public void onStartInstrExecution(Instruction instr) {}

	public void onEndInstrExecution(Instruction instr, Memory mem,
			OperandStack ops, ProgramCounter pc) {}

	public void onStartRun() {
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					botonRun.setEnabled(false);
					botonPause.setEnabled(true);
					botonStep.setEnabled(false);
				}
			}
		);		
	}

	public void onEndRun(boolean aborted) {
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					botonRun.setEnabled(true);
					botonPause.setEnabled(false);
					botonStep.setEnabled(true);
				}
			}
		);	
	}
	
	@Override
	public void onAnarchicJump(ProgramCounter pc) {}

	@Override
	public void onSwitchStack() {}

	private ControllerWindow controller;
	private JTextField campoNum;
	private JButton botonRun;
	private JButton botonPause;
	private JButton botonStep;
	private JButton botonStepN;
	private JButton botonExit;
	private JButton botonSave;
	private JButton botonLoad;
}
