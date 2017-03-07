package tp.pr5.mv;

public class InstructionParser {
	
	public static Instruction parse (String line) {
		String mnemo, param = null, param2 = null;
		line = line.trim();
		line = line.toUpperCase();
		String[] word = line.split(" +");
		if (word.length == 0 || 3 < word.length)
			return null;
		else  {
			mnemo = word[0];
			if (word.length > 1) 
				param = word[1];
			if (word.length > 2) 
				param2 = word[2];
		}
		
		Instruction[] instructionSet = Instruction.getInstructionSet();
		int length = Instruction.getInstructionSetLength();
		Instruction newInstruction = null;
		
		int i = 0;
		while(i < length && newInstruction == null) {
			newInstruction = instructionSet[i].parse(mnemo, param, param2);
			i++;
		}
		return newInstruction;
	}
}