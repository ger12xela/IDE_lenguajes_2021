package automata;

public class EstadoRango extends Estado{
	
	private int rangoAlto;
	private int rangoBajo;
	

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
