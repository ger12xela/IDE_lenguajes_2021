package automata;

public class Token {
	String tipo, palabra;
	int fila, columna;
	
	/**
	 * describe un tipo de token 
	 * @param tipo el tipo de token numero, decimal, palabra, aperador, agrupacion
	 * @param palabra que es de ese tipo 
	 * @param fila donde se encuentra la palabra 
	 * @param columna donde se encuentra la palabra
	 */
	public Token(String tipo, String palabra, int fila, int columna) {
		this.tipo = tipo;
		this.palabra = palabra;
		this.fila = fila;
		this.columna = columna;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}
	
	

}
