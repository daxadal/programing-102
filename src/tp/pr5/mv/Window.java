package tp.pr5.mv;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import tp.pr5.mv.io.InFromFile;
import tp.pr5.mv.io.OutOnFile;
import tp.pr5.mv.window.ButtonPanel;
import tp.pr5.mv.window.CodePanel;
import tp.pr5.mv.window.InPanel;
import tp.pr5.mv.window.MemoryPanel;
import tp.pr5.mv.window.OutPanel;
import tp.pr5.mv.window.StackPanel;
import tp.pr5.mv.window.StatusPanel;

@SuppressWarnings("serial")
public class Window extends JFrame{
	
	public Window(ControllerWindow controller, Observable<CPU.Observer> cpu, Observable<Memory.Observer> mem, Observable<OperandStack.Observer> ops, Observable<OperandStack.Observer> ops2,
            Instruction[] program, final Scanner cin, final InFromFile metodo_in, final OutOnFile metodo_out) throws IOException { 
          
        super("Maquina virtual de TP"); 
        setSize(800,600); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        this.controller = controller; 
          
        addWindowListener(new WindowAdapter() { 
            public void windowClosed(WindowEvent ev) { 
                 try { 
                     if (metodo_in != null)  
                         metodo_in.close(); //*** Puede lanzar IOException 
                     if (metodo_out != null) 
                         metodo_out.close(); //*** Puede lanzar IOException 
              
                     if (cin != null) 
                         cin.close();             
                     System.exit(0); //Fin del programa 
                       
                } catch (IOException ex) {   
                    System.err.println("Error al cerrar los archivos"); 
                      
                    if (cin != null) 
                        cin.close(); 
                    System.exit(0); 
                } 
            } 
        }); 
          
        initGUI(cpu, mem, ops, ops2, program); 
    } 
	
	private void initGUI(Observable<CPU.Observer> cpu, Observable<Memory.Observer> mem, Observable<OperandStack.Observer> ops, Observable<OperandStack.Observer> ops2, Instruction[] prog) throws IOException{
		ButtonPanel buttonPanel = new ButtonPanel(this, this.controller);
		cpu.addObserver(buttonPanel);
		this.add(buttonPanel, BorderLayout.NORTH);
		
		CodePanel code = new CodePanel(prog, controller);
		this.add(code, BorderLayout.WEST);
		cpu.addObserver(code);
		
		JPanel panelCentral = new JPanel();
		this.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(2,1));
		
		StatusPanel estado = new StatusPanel(mem, ops, ops2, cpu);
		this.add(estado, BorderLayout.SOUTH);
		
		JPanel panelPilaMem = new JPanel();
		JPanel panelEntrSal = new JPanel();
		panelCentral.add(panelPilaMem);
		panelCentral.add(panelEntrSal);
		
		panelPilaMem.setLayout(new GridLayout(1,2));
		StackPanel stack = new StackPanel(this.controller);
		panelPilaMem.add(stack);
		cpu.addObserver(stack);
		ops.addObserver(stack);
		ops2.addObserver(stack);
		
		MemoryPanel memory = new MemoryPanel(this.controller, mem);
		panelPilaMem.add(memory);
		cpu.addObserver(memory);
		
		panelEntrSal.setLayout(new GridLayout(2,1));
		InPanel in = new InPanel();
		this.jtaIn = in.getTextArea();
		panelEntrSal.add(in);
		
		
		OutPanel out = new OutPanel();
		this.jtaOut = out.getTextArea();
		panelEntrSal.add(out);
		
		this.setLocationRelativeTo(null);
	}
	
	public JTextArea getJtaIn() {
		return jtaIn;
	}

	public JTextArea getJtaOut() {
		return jtaOut;
	}

	private JTextArea jtaIn;
	private JTextArea jtaOut;
	
	private ControllerWindow controller;
}
