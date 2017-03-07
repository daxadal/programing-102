package tp.pr5.mv.io;

import tp.pr5.mv.OutMethod;

public class OutOnScreen implements OutMethod{

	public OutOnScreen() {}
	
	public void writeChar(char c) {
		System.out.print(c);
	}

}
