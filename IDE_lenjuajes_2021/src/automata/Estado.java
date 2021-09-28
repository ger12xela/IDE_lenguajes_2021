package automata;



public class Estado {
	
	private int actual;
	private char letra;
	private int siguiente;
	
	public Estado(int actual, char letra, int siguiente) {
		super();
		this.actual = actual;
		this.letra = letra;
		this.siguiente = siguiente;
	}
	
	public boolean camino(char letra) {
		boolean movido = false;
		if (this.letra == letra) {
			movido= true;
		}
		return movido;
	}

	public int getActual() {
		return actual;
	}

	public void setActual(int actual) {
		this.actual = actual;
	}

	public char getLetra() {
		return letra;
	}

	public void setLetra(char letra) {
		this.letra = letra;
	}

	public int getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(int siguiente) {
		this.siguiente = siguiente;
	}
	
	

}
