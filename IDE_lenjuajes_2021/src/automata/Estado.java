package automata;



public class Estado {
	
	private int actual;
	private char letra;
	private int siguiente;
	
	/**
	 * crea un estado 
	 * @param actual el estado actual 
	 * @param letra con la cual se mover 
	 * @param siguiente el estado al que apunta
	 */
	public Estado(int actual, char letra, int siguiente) {
		super();
		this.actual = actual;
		this.letra = letra;
		this.siguiente = siguiente;
	}
	
	/**
	 * verifica si puede continuar con la letra de entrada
	 * @param letra entrada para la verificiacion con la compativilidad del estado
	 * @return
	 */
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
