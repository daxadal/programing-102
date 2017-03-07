package tp.pr5.mv;



public class Memory extends Observable<Memory.Observer>{
	
	public static interface Observer {
		void onReset();
		void onWrite(int pos, int value);
		void onErase(int pos);
	}
	
	public Memory() {
		this.Memory = new int [2][TAM_INI];
		this.contador = 0;
		this.capacidad = TAM_INI;
	}
	
	public void reset() {
		this.contador = 0;
		for(Observer o: obs) o.onReset();
	}
	
	/** Busqueda de posici�n real a traves de la posicion virtual
	 * @param pos : Recibe la posicion virtual del objeto que desea buscar
	 * @return Devuelve la posicion real donde est� (si lo encuentra), 
	 * o donde deber�a estar (si no lo encuentra)
	 */
	private int buscar(int pos) {
		int ini = 0,
			fin = this.contador-1, 
			mitad = (ini + fin)/2;
		
		while (ini<=fin && pos != Memory[POS][mitad]) {
			if (pos < Memory[POS][mitad]) 
				fin = mitad-1;
			else //(pos > Memory[POS][mitad])
				ini = mitad+1;
			mitad = (ini + fin)/2;
		}
		if (ini<=fin) return mitad;
		/*else*/ return  fin+1;
	}
	
	
	/** Abre la memoria (redimensiona si es necesario)
	 * @param pos Posicion por la que abrir
	 */
	private void abrir(int pos) {
		if (this.contador == this.capacidad)
			this.redimensionar();
		
		for (int i = contador-1; i>=pos; i--) {
			this.Memory[POS][i+1] = this.Memory[POS][i];
			this.Memory[DATO][i+1] = this.Memory[DATO][i];
		}
		this.contador++;
	}
	
	private void cerrar(int pos) {
		for (int i=pos; i<this.contador;i++) {
			this.Memory[POS][i] = this.Memory[POS][i+1];
			this.Memory[DATO][i] = this.Memory[DATO][i+1];
		}
		this.contador--;
	}
	
	/** Duplica el tama�o de la memoria
	 */
	private void redimensionar() {
		this.capacidad *= 2;
		int[][] new_Memory = new int[2][this.capacidad];
		for (int i=0; i<this.contador; i++) {
			new_Memory[DATO][i] = this.Memory[DATO][i];
			new_Memory[POS][i] = this.Memory[POS][i];
		}
		this.Memory = new_Memory;
	}
		
	/**Operaci�n de escritura en memoria
	 * 
	 * @param pos Poscion virtual sobre la que escribir. Debe ser positiva
	 * @param value Valor que escribir
	 * @return true si la escritura fue correcta, false en caso contrario
	 */
	public boolean write(int pos, int value) {
		if (pos < 0) return false; //No se puede escribir en posiciones negativas
		
		/*else*/
		int indice_real = this.buscar(pos);
		
		if (indice_real < this.contador && this.Memory[POS][indice_real] == pos) { //Encontrado (la posicion encontrada coincide con la buscada)
			this.Memory[DATO][indice_real] = value;
		}
		
		else  /*(this.Memory[POS][indice_real] != pos) */ { //No encontrado
			
			this.abrir(indice_real);
			this.Memory[POS][indice_real] = pos;
			this.Memory[DATO][indice_real] = value;
		}
		for(Observer o: obs) o.onWrite(pos, value);
		return true;
	}
	
	public boolean erase(int pos) {
		if (pos < 0) return false; //No se puede escribir en posiciones negativas
		
		/*else*/
		int indice_real = this.buscar(pos);
		
		if (indice_real < this.contador && this.Memory[POS][indice_real] == pos) { //Encontrado (la posicion encontrada coincide con la buscada)
			cerrar(pos);
			for(Observer o: obs) o.onErase(pos);
			return true;
		}
		
		else  /*(this.Memory[POS][indice_real] != pos) */ { //No encontrado
			return false;
		}
	}
	
	
	public boolean canRead(int pos) {
		return pos>=0;
	}
	
	public int read(int pos) {
		int indice_real = this.buscar(pos);
		if (this.Memory[POS][indice_real] == pos)  //Encontrado (la posicion encontrada coincide con la buscada)
			return this.Memory[DATO][indice_real];
		/*else*/
			return (int) Math.round(Math.random() *1000000);
	}
	/** 
	 * @return Devuelve un array (de tama�o exacto) con todas las posiciones en las que
	 *  se ha escrito en la memoria
	 */
	public int[] writtenPos() {
		int[] wr_pos = new int[this.contador];
		for (int i=0; i<this.contador; i++)
			wr_pos[i] = this.Memory[POS][i];
		return wr_pos;
	}
	
	public int getSize() {
		return contador;
	}
	
	private static final int POS = 0;
	private static final int DATO = 1;
	private static final int TAM_INI = 5;
	private int contador;
	private int capacidad;
	private int [][]Memory; //Array de 2 filas (POS, DATO) y N columnas
}
