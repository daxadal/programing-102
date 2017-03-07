package tp.pr5.mv.window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import tp.pr5.mv.ControllerWindow;
import tp.pr5.mv.Instruction;
import tp.pr5.mv.Memory;
import tp.pr5.mv.Observable;
import tp.pr5.mv.OperandStack;
import tp.pr5.mv.ProgramCounter;


@SuppressWarnings("serial")
public class MemoryPanel extends JPanel implements tp.pr5.mv.CPU.Observer {
	
	private class Modelo extends AbstractTableModel implements tp.pr5.mv.Memory.Observer {

		public Modelo() {
		
			mem = new TreeMap<Integer, Integer>();
		}
		public String getColumnName(int column) {
			if (column == 0)
				return "DIRECCIÓN";
			else
				return "VALOR";
		}
		@Override
		public int getRowCount() {
			return mem.size();
		}

		@Override
		public int getColumnCount() {
			return 2;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if(columnIndex == 0) return cellArray[rowIndex].getKey();
			else return cellArray[rowIndex].getValue();
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public void onReset() {
			SwingUtilities.invokeLater(
				new Runnable() {
					public void run() {
						mem = new TreeMap<Integer, Integer>();
						tablaMemoria.setModel(modelo);
						modelo.fireTableDataChanged();
		
						cellArray = mem.entrySet().toArray(new Map.Entry[0]);	
					}
				}
			);
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public void onWrite(final int pos, final int value) {
			SwingUtilities.invokeLater(
				new Runnable() {
					public void run() {
						mem.put(pos, value);
						tablaMemoria.setModel(modelo);
						modelo.fireTableDataChanged();
		
						cellArray = mem.entrySet().toArray(new Map.Entry[0]);	
					}
				}
			);
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public void onErase(final int pos) {
			SwingUtilities.invokeLater(
				new Runnable() {
					public void run() {
						mem.remove(pos);
						tablaMemoria.setModel(modelo);
						modelo.fireTableDataChanged();
		
						cellArray = mem.entrySet().toArray(new Map.Entry[0]);	
					}
				}
			);
		}

		private TreeMap<Integer, Integer> mem;
		private Map.Entry<Integer, Integer>[] cellArray;
		
		
	}
	
	public MemoryPanel(ControllerWindow controller, Observable<Memory.Observer> mem) {
		this.controller = controller;
		this.modelo = new Modelo();
		mem.addObserver(modelo);
		initGUI();
	}
	
	private void initGUI() {
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Memoria de la máquina"));
		
		//TextArea de la memoria
		tablaMemoria = new JTable();
		tablaMemoria.setModel(modelo);
		this.add(new JScrollPane(tablaMemoria), BorderLayout.CENTER);
		
		//Botones del sur
		JPanel panelSur = new JPanel();
		this.add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new GridLayout(2,1));
		
		JPanel panelCampos = new JPanel();
		panelSur.add(panelCampos);
		panelCampos.setLayout(new FlowLayout());
		
		JPanel panelWrite = new JPanel();
		panelWrite.setLayout(new FlowLayout());
		panelSur.add(panelWrite);
		
		//Campos
		JLabel pos = new JLabel("Pos: ");
		campoPos = new JTextField();
		JLabel val = new JLabel("Val: ");
		campoVal = new JTextField();

		panelCampos.add(pos);
		panelCampos.add(campoPos);
		panelCampos.add(val);
		panelCampos.add(campoVal);
		
		campoPos.setColumns(5);
		campoVal.setColumns(5);
			
		
		botonWrite = new JButton();
		panelWrite.add(botonWrite);
		botonWrite.setText("Write");
		botonWrite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int pos = Integer.parseInt(campoPos.getText());
					int valor = Integer.parseInt(campoVal.getText());
					MemoryPanel.this.controller.executeWrite(pos, valor);
				} catch (NumberFormatException ex) {
					campoPos.setText("");
					campoVal.setText("");
					JOptionPane.showMessageDialog(MemoryPanel.this, "Introduce un entero", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});	
		
		botonErase = new JButton();
		panelWrite.add(botonErase);
		botonErase.setText("Erase");
		botonErase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int pos = Integer.parseInt(campoPos.getText());
					MemoryPanel.this.controller.executeErase(pos);
				} catch (NumberFormatException ex) {
					campoPos.setText("");
					campoVal.setText("");
					JOptionPane.showMessageDialog(MemoryPanel.this, "Introduce un entero", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});	
	}
	
	@Override
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
					botonWrite.setEnabled(false);	
				}
			}
		);
	}

	public void onEndRun(boolean aborted) {
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					botonWrite.setEnabled(true);	
				}
			}
		);	
	}

	@Override
	public void onAnarchicJump(ProgramCounter pc) {}

	@Override
	public void onSwitchStack() {}

	private ControllerWindow controller;
	private JTextField campoPos;
	private JTextField campoVal;
	private JTable tablaMemoria;
	private Modelo modelo;
	private JButton botonWrite;
	private JButton botonErase;
}
