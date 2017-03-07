package tp.pr5.mv.window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import tp.pr5.mv.ControllerWindow;
import tp.pr5.mv.Instruction;
import tp.pr5.mv.Memory;
import tp.pr5.mv.OperandStack;
import tp.pr5.mv.ProgramCounter;

@SuppressWarnings("serial")
public class StackPanel extends JPanel implements tp.pr5.mv.OperandStack.Observer, tp.pr5.mv.CPU.Observer {

	public StackPanel(ControllerWindow controller) {
		this.controller = controller;
		this.model = new DefaultListModel<Integer>();
		this.modelDormido = new DefaultListModel<Integer>();
		this.modelPap = new DefaultListModel<Integer>();
		initGUI();
	}
	
	private void initGUI() {
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Pila de operandos"));
		
		//TextArea de la pila
		JPanel panelPilas = new JPanel();
		panelPilas.setLayout(new GridLayout(1,3,5,5));
		this.add(panelPilas);
		
		listaPila = new JList<Integer>();
		listaPila.setBorder(new TitledBorder("Pila Activa"));
		listaPila.setEnabled(true);
		panelPilas.add(new JScrollPane(listaPila), BorderLayout.CENTER);
		
		listaPilaDormida = new JList<Integer>();
		listaPilaDormida.setBorder(new TitledBorder("Pila Dormida"));
		listaPilaDormida.setEnabled(false);
		panelPilas.add(new JScrollPane(listaPilaDormida), BorderLayout.CENTER);
		
		listaPapelera = new JList<Integer>();
		listaPapelera.setBorder(new TitledBorder("Papelera"));
		panelPilas.add(new JScrollPane(listaPapelera), BorderLayout.CENTER);
		
		//Botones del sur
		JPanel panelSur = new JPanel();
		this.add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new GridLayout(2,1));
		
		JPanel panelPush = new JPanel();
		panelSur.add(panelPush);
		panelPush.setLayout(new FlowLayout());
		
		JPanel panelPop = new JPanel();
		panelPop.setLayout(new FlowLayout());
		panelSur.add(panelPop);
		
		//Boton push
		JLabel valor = new JLabel("Valor: ");
		campo = new JTextField();
		
		botonPush = new JButton();
		panelPush.add(valor);
		panelPush.add(campo);
		panelPush.add(botonPush);
		
		campo.setColumns(10);
		botonPush.setText("Push");
		
		botonPush.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int dato = Integer.parseInt(campo.getText());
					StackPanel.this.controller.executePush(dato);
				} catch (NumberFormatException ex) {
					campo.setText("");
					JOptionPane.showMessageDialog(StackPanel.this, "Introduce un entero", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}		
		});
			
		
		//Boton pop
		botonPop = new JButton();
		panelPop.add(botonPop);
		botonPop.setText("Pop");
		botonPop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StackPanel.this.controller.executePop();
			}
		});
		
		//Boton switch
		botonSwitch = new JButton();
		panelPop.add(botonSwitch);
		botonSwitch.setText("Switch stack");
		botonSwitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StackPanel.this.controller.executeSwitchStack();
			}
		});
	}
	
	@Override
	public void onReset() {
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					model = new DefaultListModel<Integer>();
					listaPila.setModel(model);	
					modelDormido = new DefaultListModel<Integer>();
					listaPilaDormida.setModel(modelDormido);	
					modelPap = new DefaultListModel<Integer>();
					listaPapelera.setModel(modelPap);
				}
			}
		);
	}

	@Override
	public void onPop(final int value) {
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					model.remove(0);
					listaPila.setModel(model);	
					modelPap.add(0, value);
					listaPapelera.setModel(modelPap);	
				}
			}
		);
	}

	@Override
	public void onPush(final int value) {
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					model.add(0, value);
					listaPila.setModel(model);		
				}
			}
		);
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
					botonPush.setEnabled(false);
					botonPop.setEnabled(false);	
				}
			}
		);
	}

	public void onEndRun(boolean aborted) {
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					botonPush.setEnabled(true);
					botonPop.setEnabled(true);		
				}
			}
		);
	}
	
	@Override
	public void onAnarchicJump(ProgramCounter pc) {}

	@Override
	public void onSwitchStack() {
		DefaultListModel<Integer> aux = model;
		model = modelDormido;
		modelDormido = aux;
		
		listaPila.setModel(model);
		listaPilaDormida.setModel(modelDormido);		
	}

	private ControllerWindow controller;
	private JList<Integer> listaPila;
	private JList<Integer> listaPilaDormida;
	private JList<Integer> listaPapelera;
	private DefaultListModel<Integer> model;
	private DefaultListModel<Integer> modelDormido;
	private DefaultListModel<Integer> modelPap;
	private JTextField campo;
	private JButton botonPush;
	private JButton botonPop;
	private JButton botonSwitch;
}
