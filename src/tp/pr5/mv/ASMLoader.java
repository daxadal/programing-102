package tp.pr5.mv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ASMLoader {
	public static ProgramMV loadASMfile(String file) throws ASMSyntaxErrorException{
		Scanner entrada;
		try {
			entrada = new Scanner(new FileReader(file));
		} catch (FileNotFoundException ex) {
			throw new ASMSyntaxErrorException("Uso incorrecto: Error al acceder al fichero de entrada (" + file + ")", ex);
		}
		ProgramMV program = new ProgramMV();
		Instruction instruction;
		
		String line;
			
		do {
			line = entrada.nextLine();
			
			if(line.split(";").length > 0) {	//Comprobación de array no vacío
				line = line.split(";")[0];
				line = line.trim();
			}
			
			else 
				line = "";	
				
			if(!line.equals("")) {
				instruction = InstructionParser.parse(line);
				if (instruction == null) {
					entrada.close();
					throw new ASMSyntaxErrorException("Error en el programa. Linea: " + line);
				}
				else
					program.addInstruction(instruction);
			}
		} while (entrada.hasNextLine());
			
		entrada.close();
		return program;
	}
	
	public static ProgramMV parseASMkeyboard(Scanner cin) {
		final String END = "END";
		ProgramMV program = new ProgramMV();
		Instruction instruction;
		String line;
		
		System.out.println("Introduce el programa fuente");
		System.out.print("> ");
		line = cin.nextLine();
		while (!line.trim().equalsIgnoreCase(END)) {
			instruction = InstructionParser.parse(line);
			if (instruction == null)
				System.out.println("Error: Instrucción incorrecta");
			else
				program.addInstruction(instruction);
			
			System.out.print("> ");
			line = cin.nextLine();
		}
		mostrarPrograma(program);
		
		return program;
	}
	
	public static void mostrarPrograma(ProgramMV program) {
		System.out.println("El programa introducido es:");
		for (int i=0; i<program.getNumInstrs(); i++)
			System.out.println("" + i + ": " + program.getInstrAt(i).toString());
	}
}
