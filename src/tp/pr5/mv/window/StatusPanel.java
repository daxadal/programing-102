package tp.pr5.mv.window;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import tp.pr5.mv.CPU;
import tp.pr5.mv.Instruction;
import tp.pr5.mv.Memory;
import tp.pr5.mv.Observable;
import tp.pr5.mv.OperandStack;
import tp.pr5.mv.ProgramCounter;

@SuppressWarnings("serial")
public class StatusPanel extends JPanel implements tp.pr5.mv.Memory.Observer, tp.pr5.mv.OperandStack.Observer, tp.pr5.mv.CPU.Observer {
	
	public StatusPanel(Observable<Memory.Observer> mem, Observable<OperandStack.Observer> ops, Observable<OperandStack.Observer> ops2, Observable<CPU.Observer> cpu) {
		mem.addObserver(this);
		ops.addObserver(this);
		ops2.addObserver(this);
		cpu.addObserver(this);
		initGUI();
	}
	
	public void initGUI() {
		this.setLayout(new FlowLayout());
		
		notificacionParada = new JLabel();
		notificacionParada.setForeground(Color.red);
		this.add(notificacionParada);
		
		notificacionInstrucciones = new JLabel();
		this.add(notificacionInstrucciones);
		
		memoriaModificada = new JCheckBox();
		this.add(memoriaModificada);
		
		etiquetaMemoria = new JLabel();
		this.add(etiquetaMemoria);
		
		pilaModificada = new JCheckBox();
		this.add(pilaModificada);
		
		etiquetaPila = new JLabel();
		this.add(etiquetaPila);
		
		numInstrucciones = 0;
		notificacionParada.setText("");
		notificacionInstrucciones.setText("Num. Instrucciones ejecutadas: " + numInstrucciones);
		memoriaModificada.setEnabled(false);
		memoriaModificada.setSelected(false);
		etiquetaMemoria.setText("Memoria modificada");
		pilaModificada.setSelected(false);
		pilaModificada.setEnabled(false);
		etiquetaPila.setText("Pila modificada");
	}
	

	@Override
	public void onReset(Instruction[] program) {
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					numInstrucciones = 0;
					notificacionInstrucciones.setText("Num. Instrucciones ejecutadas: " + numInstrucciones);
					memoriaModificada.setSelected(false);
					etiquetaMemoria.setText("Memoria modificada");
					pilaModificada.setSelected(false);
					etiquetaPila.setText("Pila modificada");		
				}
			}
		);
	}

	@Override
	public void onHalt() {
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					notificacionParada.setText("Máquina parada");
				}
			}
		);
	}

	@Override
	public void onTrap(Instruction instr, final String msg) {
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					JOptionPane.showMessageDialog(StatusPanel.this, msg, "Error", JOptionPane.ERROR_MESSAGE);		
				}
			}
		);
	}

	@Override
	public void onStartInstrExecution(Instruction instr) {
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					memoriaModificada.setSelected(false);
					pilaModificada.setSelected(false);	
				}
			}
		);
	}

	@Override
	public void onEndInstrExecution(Instruction instr, Memory mem, OperandStack ops, ProgramCounter pc) {
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					numInstrucciones++;
					notificacionInstrucciones.setText("Num. Instrucciones ejecutadas: " + numInstrucciones);
				}
			}
		);
	}

	@Override
	public void onStartRun() {
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					notificacionParada.setText("Ejecutando");
					memoriaModificada.setSelected(false);
					pilaModificada.setSelected(false);	
				}
			}
		);
	}

	@Override
	public void onEndRun(boolean aborted) {
		SwingUtilities.invokeLater(
				new Runnable() {
					public void run() {
						notificacionParada.setText("Máquina en pausa");
					}
				}
			);
	}

	@Override
	public void onPop(int value) {
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					pilaModificada.setSelected(true);	
				}
			}
		);
	}

	@Override
	public void onPush(int value) {
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					pilaModificada.setSelected(true);	
				}
			}
		);
	}

	@Override
	public void onReset() {}

	@Override
	public void onWrite(int pos, int value) {
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					memoriaModificada.setSelected(true);	
				}
			}
		);	
	}
	
	@Override
	public void onErase(int pos) {
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					memoriaModificada.setSelected(true);	
				}
			}
		);	
	}

	@Override
	public void onAnarchicJump(ProgramCounter pc) {}

	@Override
	public void onSwitchStack() {}

	private JLabel notificacionParada;
	private JLabel notificacionInstrucciones;
	private JCheckBox memoriaModificada;
	private JLabel etiquetaMemoria;
	private JCheckBox pilaModificada;
	private JLabel etiquetaPila;
	private int numInstrucciones;
}
