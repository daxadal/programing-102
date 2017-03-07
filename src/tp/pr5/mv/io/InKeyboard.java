package tp.pr5.mv.io;

import java.io.IOException;

import tp.pr5.mv.InMethod;

public class InKeyboard implements InMethod {
	
	public InKeyboard() {}

	@Override
	public int readChar() throws IOException {
		return System.in.read();
	}
}
