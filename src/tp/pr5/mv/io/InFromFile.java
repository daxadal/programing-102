package tp.pr5.mv.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import tp.pr5.mv.InMethod;

public class InFromFile implements InMethod{

	public InFromFile(String arch) {
		this.file = arch;
	}
	
	public void open() throws IOException {
		this.cin = new FileInputStream(this.file);
	}

	@Override
	public int readChar() throws IOException {
		return cin.read();
	}

	public void close() throws IOException {
		cin.close();
	}

	private InputStream cin;
	private String file;
}
