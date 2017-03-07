package tp.pr5.mv;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

import tp.pr5.mv.io.InFromFile;
import tp.pr5.mv.io.InKeyboard;
import tp.pr5.mv.io.InNowhere;
import tp.pr5.mv.io.InWindow;
import tp.pr5.mv.io.OutNowhere;
import tp.pr5.mv.io.OutOnFile;
import tp.pr5.mv.io.OutOnScreen;
import tp.pr5.mv.io.OutOnWindow;


public class Main {
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		final String BATCH = "batch";
		final String INTERACTIVE = "interactive";
		final String WINDOW = "window";
		
    	Scanner cin = null;
		CommandLineParser parser = null;  
        CommandLine cmdLine = null;
        String file_program = null, file_in = null, file_out = null;
        
		Options options = new Options();
		options.addOption(
				OptionBuilder.withLongOpt("asm")
					.withArgName("asmfile")
					.hasArg()
					.withDescription("Fichero con el codigo en ASM del programa a ejecutar. Obligatorio en modo batch.")
					.create("a")
					);
		
		options.addOption(
				OptionBuilder.withLongOpt("mode")
					.withArgName("mode")
					.hasArg()
					.withDescription("Modo de funcionamiento (batch | interactive | window). Por defecto, batch.")
					.create("m")
				);
		
		options.addOption(
				OptionBuilder.withLongOpt("in")
					.withArgName("infile")
					.hasArg()
					.withDescription("Entrada del programa de la maquina-p.")
					.create("i")
				);
		
		options.addOption(
				OptionBuilder.withLongOpt("out")
					.withArgName("outfile")
					.hasArg()
					.withDescription("Fichero donde se guarda la salida del programa de la maquina-p.")
					.create("o")
				);
 
	    options.addOption("h", "help", false, "Muestra esta ayuda");  
	    
	    try {  
	        parser  = new BasicParser();  
	        cmdLine = parser.parse(options, args);  //***Puede lanzar ParseException
	        
	        //Opción ayuda
	        if (cmdLine.hasOption("h")){    //Se pide la ayuda
                new HelpFormatter().printHelp(Main.class.getCanonicalName(), options, true );  
                System.exit(0);  //No se debe hacer nada más en el programa
            }  
            
	        //Modo
	        Mode mode;
	        
	        if (cmdLine.hasOption("m") && cmdLine.getOptionValue("m").equals(BATCH) //Se especifica modo batch...
	        		|| !cmdLine.hasOption("m")){  //.. o no se especifica modo
            	mode = Mode.BATCH;
            } else if (cmdLine.hasOption("m") && cmdLine.getOptionValue("m").equals(INTERACTIVE)){  //Se especifica modo interactivo
            	mode = Mode.INTERACTIVE;
            } else if(cmdLine.hasOption("m") && cmdLine.getOptionValue("m").equals(WINDOW)) {
            	mode = Mode.WINDOW;
            } else 	//Modo incorrecto
            	throw new org.apache.commons.cli.ParseException("Modo incorrecto (parametro -m|--mode)");

	        //Archivo de programa
            if (cmdLine.hasOption("a")){  
            	file_program = cmdLine.getOptionValue("a");
            } else if (mode == Mode.BATCH)
            	throw new org.apache.commons.cli.ParseException("Fichero ASM no especificado."); //Fichero obligatorio en modo batch
            
            
            //Archivo de entrada
            if (cmdLine.hasOption("i")){  
            	file_in = cmdLine.getOptionValue("i");
            } 
            
            //Archivo de salida
            if (cmdLine.hasOption("o")){  
            	file_out = cmdLine.getOptionValue("o");
            } 
            
            ////////////////////////
            //Carga del programa
            
            ProgramMV program;
            if (file_program == null) {
            	if (cin == null)
            		cin = new Scanner(System.in);
            	program = ASMLoader.parseASMkeyboard(cin);
            }
            else
            	program = ASMLoader.loadASMfile(file_program); //*** Puede lanzar ASMSyntaxErrorException
            
            ////////////////////////
            //Configuración de la entrada/salida
            InMethod in = null;
            InFromFile inFile = null;
            OutMethod out = null;
	        OutOnFile outFile = null;
	        Window window;
	        
           if(mode != Mode.WINDOW) {
	            
	            if (file_in != null) {
	            	in = inFile = new InFromFile(file_in);
	            }
	            else if (file_in == null && mode == Mode.BATCH)
	            	in = new InKeyboard();
	            else /*file_in == null && mode != Mode.BATCH*/
	            	in = new InNowhere();
	         
	            
	           
	            if (file_out != null)
	            	out = outFile = new OutOnFile(file_out);
	            else if (file_out == null && mode == Mode.BATCH)
	            	out = new OutOnScreen();
	            else /*file_out == null && !mode == Mode.BATCH*/
	            	out = new OutNowhere();
            }
          
            
            
            ////////////////////////
            //Configuración de la CPU
            CPU cpu = new CPU();
            cpu.setMode(mode);	//Guarda en la cpu el modo de ejecución
            cpu.loadProgram(program);	
            if(mode != Mode.WINDOW) {
	            try {
	            	if (inFile != null) 
	            		inFile.open();
	
	            	cpu.setInMethod(in);
	            } catch (IOException ex) {
	            	throw new org.apache.commons.cli.ParseException("Error al acceder al fichero de entrada ("+ file_in +")");
	            }
	            try {
	            	if (outFile != null)
	            		outFile.open();
	            		
	            	cpu.setOutMethod(out);
				} catch (IOException ex) {
					throw new org.apache.commons.cli.ParseException("Error al acceder al fichero de salida ("+ file_out +")");
				}
            }
            
            ////////////////////////
            //Configurar controlador
            Controller contr = null;
            ControllerWindow contrW = null;
            switch (mode) {
            case INTERACTIVE: 	contr = new ControllerInteractive(cpu);
            					break;
            case WINDOW:		contrW = new ControllerWindow(cpu);
            					break;
            default: 			contr = new ControllerBatch(cpu);
								break;
            }
            
           
            
            ////////////////////////
            //Ejecución
            if (program.getNumInstrs() > 0) {
            	if(mode != Mode.WINDOW) {
            		if (cin == null)
        				cin = new Scanner(System.in);
            		if (mode == Mode.INTERACTIVE) 
            			new ConsoleInteractive(cpu);
            		else
            			new ConsoleBatch(cpu);
            		contr.start(cin);
            		try {
	            		 if (inFile != null) 
	            			 inFile.close(); //*** Puede lanzar IOException
	            		 if (outFile != null)
	            			 outFile.close(); //*** Puede lanzar IOException
	            
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
            	 else /*mode == Mode.WINDOW */{ 
                     if (file_in != null) { 
                         inFile = new InFromFile(file_in); 
                         try { 
                             if (inFile != null)  
                                 inFile.open(); 
                         } catch (IOException ex) { 
                             throw new org.apache.commons.cli.ParseException("Error al acceder al fichero de entrada ("+ file_in +")"); 
                         } 
                     } 
                       
                     if(file_out != null) { 
                         outFile = new OutOnFile(file_out); 
                          try { 
                             if (outFile != null) 
                                 outFile.open(); 
                         } catch (IOException ex) { 
                             throw new org.apache.commons.cli.ParseException("Error al acceder al fichero de salida ("+ file_out +")"); 
                         } 
                     } 
                       
                     window = new Window(contrW, cpu, cpu.getMemory(), cpu.getOperandStack(), cpu.getOperandStack2(), cpu.getInstructionArray(), cin, inFile, outFile); 
                       
                     if(file_in != null) in = new InWindow(window.getJtaIn(), inFile); 
                     else in = new InWindow(window.getJtaIn(), new InNowhere()); 
                           
                     if(file_out != null) out = new OutOnWindow(window.getJtaOut(), outFile); 
                     else out = new OutOnWindow(window.getJtaOut(), new OutNowhere()); 
                       
                     cpu.setInMethod(in); 
                     cpu.setOutMethod(out); 
                     window.setVisible(true); 
                 } 
            }
            
	    } catch (org.apache.commons.cli.ParseException ex){  
            System.err.println("Uso incorrecto: " + ex.getMessage());  
            System.err.println("Use -h|--help para más detalles.");
            
            if (cin != null)
            	cin.close();
            System.exit(1); 
        } catch (ASMSyntaxErrorException ex) {
        	System.err.println(ex.getMessage());
        	System.exit(2);
        } catch (IOException e) {
			System.exit(0);
		} 
	    
	    
	}

}
