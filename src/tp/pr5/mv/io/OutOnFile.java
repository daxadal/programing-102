package tp.pr5.mv.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import tp.pr5.mv.OutMethod;


public class OutOnFile implements OutMethod{
	
	public OutOnFile(String arch) {
		this.file = arch;
	}
	
	public void open() throws IOException {
		this.salida = new FileOutputStream(this.file);
	}
	
	@Override
	public void writeChar(char c) throws IOException {
		salida.write(c);		
	}

	public void close() throws IOException {
		this.salida.close();
	}
	
	private OutputStream salida;
	private String file;
}
