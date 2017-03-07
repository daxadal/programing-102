package tp.pr5.mv;

public class OperandStack extends Observable<OperandStack.Observer>{
	
	public static interface Observer {
		void onReset();
		void onPop(int value);
		void onPush(int value);
	}
	
	//Constructor
	public OperandStack() {
		this.stack = new int[TAM_INI];
		this.contador = 0;
		this.capacidad = TAM_INI;
	}
	
	//Comprobaciones
	public boolean isEmpty() {
		return contador == 0;
	}
		
	
	public boolean hayDosOperandos() {
		return contador >= 2;
	}
	
	//Operaciones internas
	/** Duplica el tamaño de la pila */
	private void redimensionar() {
		this.capacidad *= 2;
		int[] new_stack = new int[this.capacidad];
		for (int i=0; i<this.contador; i++)
			new_stack[i] = this.stack[i];
		this.stack = new_stack;
	}
	
	//Operaciones externas
	public void push(int value) {
		 if (this.contador == this.capacidad)
			this.redimensionar();
		this.stack[this.contador] = value;
		this.contador++;
		
		for(Observer o: obs) o.onPush(value);
	}
	
	public int top() {
		if (!this.isEmpty())
			return this.stack[this.contador - 1];
		else
			return (int) Math.round(Math.random() *1000000);
	}
	
	public int subtop() {
		if (this.hayDosOperandos())
			return this.stack[this.contador - 2];
		else
			return (int) Math.round(Math.random() *1000000);
	}
	
	public void pop() {
		if(contador > 0) {
			int top = this.stack[this.contador - 1];
			this.contador--;
			for(Observer o: obs) o.onPop(top);
		}
	}
	
	public void reset() {
		this.contador = 0;
		for(Observer o: obs) o.onReset();
	}
	
	//Getters
	public int getNumElements() {
		return contador;
	}
	
	public int getElementAt(int pos) {
		return stack[pos];
	}
	
	private static final int TAM_INI = 5;
	private int []stack;
	private int contador;
	private int capacidad;
}
