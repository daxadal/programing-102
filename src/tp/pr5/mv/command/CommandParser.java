package tp.pr5.mv.command;

public class CommandParser {
	
	public static CommandInterpreter parse (String line) {
		String mnemo, param1 = null, param2 = null;
		line = line.trim();
		line = line.toUpperCase();
		String[] word = line.split(" +");
		if (word.length == 0 || 3 < word.length) 
			return null;
		else  {
			mnemo = word[0];
			if (word.length > 1) 
				param1 = word[1];
			if (word.length > 2)
				param2 = word[2];
		}
			
		CommandInterpreter[] commandSet = CommandInterpreter.getCommandSet();
		int length = CommandInterpreter.getCommandSetLength();
		CommandInterpreter newCommand = null;
		
		int i = 0;
		while(i < length && newCommand == null) {
			newCommand = commandSet[i].parse(mnemo, param1, param2);
			i++;
		}
		return newCommand;
	}
}
