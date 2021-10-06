package automata;

public class EstadoRango extends Estado{
	
	private int rangoAlto;
	private int rangoBajo;
	

	/**
	 * hereda de rango permite establecer un rango de comprovacion de 
	 * entrada por ejemplo letras mayusculas o números dijitos 
	 * @param actual el estado actual 
	 * @param letra con la cual se comprobara la compatibilidad
	 * @param siguiente el estado al que apunta si la entra es correcta 
	 * @param Bajo numero entero el cual señala el inicio del rango ( en codigo ascii)
	 * @param Alto numero entero el cual señala el fin del rango (en codigo ascii)
	 */
	public EstadoRango(int actual, char letra, int siguiente, int Bajo, int Alto) {
		super(actual, letra, siguiente);
		this.rangoAlto = Alto;
		this.rangoBajo = Bajo;
		// TODO Auto-generated constructor stub
	}


	@Override
	public boolean camino(char letra) {
		boolean movido = false;
		int valorLetra = (int)letra;
		if (rangoBajo<=valorLetra) {
			if(rangoAlto>= valorLetra) {
				
				movido = true;
			}
		}

		return movido;
	}

}
