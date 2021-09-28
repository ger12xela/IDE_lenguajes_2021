package automata;


public class Automata {

	public Automata() {

	}

	private int[] aceptacion = {
			2
			};
	
	private Estado[] estados = { 
			new Estado(0, 'a', 1), 
			new Estado(1, 'b', 2) 
			};

	int estadoActual= 0;
	public String iniciado(char letra) {

		System.out.println(letra);
		String retorno = null;
		Estado temp;
		boolean movido; 
		for (int i = 0; i < estados.length; i++) {
			if(estados[i].getActual() == estadoActual) {
				if(estados[i].camino(letra)) {
					estadoActual = estados[i].getSiguiente();
					movido =  true;
					retorno += letra;
					
					for (int j = 0; j < aceptacion.length; j++) {
						if(aceptacion[i]== estadoActual) {
							comprobarToken(estadoActual);
						}
					}

					
				}
			}
		}
		return retorno;

	}
	public void comprobarToken(int estadoActual) {
		if ( estadoActual == 2) System.out.println("ab");
		// TODO Auto-generated method stub
		
	}
}
