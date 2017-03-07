package tp.pr5.mv.io;

import java.io.IOException;

import tp.pr5.mv.InMethod;

public class InNowhere implements InMethod {
	public InNowhere() {}
	
	@Override
	public int readChar() throws IOException {
		return -1;
	}
}
