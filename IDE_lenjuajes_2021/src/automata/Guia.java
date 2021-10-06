package automata;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class Guia {

	public Guia() {

	}

	// array de estados predeterminados para el atomata 
	private int[] estadosAcepatacion = { 1, 2, 3, 4, 6, 7, 8 };
	private Estado[] estados = { 
			new Estado(0, '+', 1), 
			new Estado(0, '*', 1), 
			new Estado(0, '/', 1), 
			new Estado(0, '%', 1), 
			new Estado(0, '%', 1), 
			new Estado(0, '%', 1),
			new EstadoRango(0,' ',2,65,90),
			new EstadoRango(0,' ',2,97,122),
			new EstadoRango(2,' ',2,65,90),		// letras mayusculas 
			new EstadoRango(2,' ',2,97,122), // letras minusculas 
			new EstadoRango(0,' ',4,48,57), // Digitos 
			new Estado(0, '-', 3),
			new EstadoRango(3,' ',4,48,57), // Digitos 
			new EstadoRango(4,' ',4,48,57), // Digitos 
			new Estado(4, '.', 5),
			new EstadoRango(5,' ',6,48,57), // Digitos 
			new EstadoRango(6,' ',6,48,57), // Digitos 
			new Estado(0, '.', 8),
			new Estado(0, ',', 8),
			new Estado(0, ';', 8),
			new Estado(0, ':', 8),
			new Estado(0, '(', 7),
			new Estado(0, ')', 7),
			new Estado(0, '[', 7),
			new Estado(0, ']', 7),
			new Estado(0, '{', 7),
			new Estado(0, '}', 7),
			
			};

	public static final String IDENTIFICADOR = "IDENTIFICADOR";
	public static final String NUMERO = "NUMERO";
	public static final String DECIMAL = "DECIMAL";
	public static final String PUNTUACION = "PUNTUACION";
	public static final String OPERADOR = "OPERADOR";
	public static final String AGRUPACION = "AGRUPACION";
	public static final String ERROR = "ERROR";

	private JTable table;

	/**
	 * inicia el automata 
	 * @param panelEditar panel donde esta ocurriendo la edicion
	 * @param panelText panel donde se describe el camino del automata
	 * @param tablaInformacion tabla donde se presenta la informacion
	 * @return
	 */
	public String paso1(JTextArea panelEditar, JTextArea panelText, JTable tablaInformacion) {

		table = tablaInformacion;
		panelText.setText("");
		String Entrada = panelEditar.getText();
		int Actual = 0;
		String palabra = "";

		// recorre el texto en el TextArea
		for (int i = 0; i < Entrada.length(); i++) { // i = pos en textArea
			char letra = Entrada.charAt(i);
			int pos = i;
			int linea = 0;
			int columna = 0;

			try {

				linea = panelEditar.getLineOfOffset(pos);
				columna = pos - panelEditar.getLineStartOffset(linea);

			} catch (Exception e) {
				// TODO: handle exception
			}
			boolean error = true;
			if (this.espacioEnter(letra)) {

				for (int j = 0; j < estados.length; j++) { // separacion de char

					if (Actual == estados[j].getActual()) { // recorrer array de estados

						if (estados[j].camino(letra)) { // comparacion char con char de estados
							error = false;
							Actual = estados[j].getSiguiente();
							String pasosAutomata = "me movi del estado " + estados[j].getActual() + " al " + Actual
									+ " con " + letra;
							panelText.setText(panelText.getText() + pasosAutomata + "\n");
							palabra += letra;
							if (Entrada.length() - 1 == i && this.perteneceAceptacion(Actual)) {
								panelText.setText(panelText.getText() + palabra + "-->es valida" + "\n"); // punto
																											// valido
								this.tipoToken(Actual, palabra, linea, columna);

							}

						}
					}
				}
				if (error) {
					if (palabra.length() > 0) {
						this.tipoToken(200, palabra+letra, linea, columna);
					} else {
						this.tipoToken(200, ""+letra, linea, columna);
					}
					palabra = "";
					Actual = 0;
				}
			} else { // si hay un espacio
				if (this.perteneceAceptacion(Actual)) {
					panelText.setText(panelText.getText() + palabra + "-->es valido" + "\n"); // punto valido
					this.tipoToken(Actual, palabra, linea, columna);
				} else {
					if (palabra.length() > 0) {
						this.tipoToken(200, palabra, linea, columna);
					}
				}
				palabra = "";
				Actual = 0;
			}
		}
		return null;
	}

	/**
	 * comprueba la entra con los estados
	 * @param estado estado actual
	 * @return verdadero  si es compatible con el estado, falso si es error con este estado
	 */
	public boolean perteneceAceptacion(int estado) {
		boolean aceptado = false;
		for (int i = 0; i < estadosAcepatacion.length; i++) {
			if (estadosAcepatacion[i] == estado)
				aceptado = true;

		}
		return aceptado;
	}

	// array de tokens encontrados 
	ArrayList<Token> lista = new ArrayList<Token>();

	/**
	 * identifica a que tipo de token pertenese, incluye el token error
	 * @param Actual el estado actual que hace referencia al estado de aceptacion 
	 * si es igual a 200 es conciderado un error 
	 * @param palabra la palabra que se examino 
	 * @param fila la linea donde se encuentra la palabra
	 * @param columna la columna donde se encuentra la palabra 
	 */
	public void tipoToken(int Actual, String palabra, int fila, int columna) {

		Token tk;
		int identificador = 2;
		int operacion = 1;
		int operacio = 3;
		int numero = 4;
		int decimal = 6;
		int agrupacion = 7;
		int puntuacion = 8;
		
		if (Actual == identificador) {
			tk = new Token(IDENTIFICADOR, palabra, fila, columna);
			lista.add(tk);
		}else if(Actual == operacion) {
			tk = new Token(OPERADOR, palabra, fila, columna);
			lista.add(tk);
		}else if(Actual == operacio) {
		tk = new Token(OPERADOR, palabra, fila, columna);
		lista.add(tk);
		}else if(Actual == numero) {
		tk = new Token(NUMERO, palabra, fila, columna);
		lista.add(tk);
		}else if(Actual == decimal) {
		tk = new Token(DECIMAL, palabra, fila, columna);
		lista.add(tk);
		}else if(Actual == agrupacion) {
		tk = new Token(AGRUPACION, palabra, fila, columna);
		lista.add(tk);
		}else if(Actual == puntuacion) {
		tk = new Token(PUNTUACION, palabra, fila, columna);
		lista.add(tk);
		}else if (Actual == 200) {
		tk = new Token(ERROR, palabra, fila, columna);
		lista.add(tk);
		}
		mostrar();
	}

	/**
	 * llena la tabla de salida con los tokens ya identificados 
	 */
	public void mostrar() {
		String[][] matris = new String[lista.size()][4];
		if (recuentoErrores()) {

			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i).getTipo().equals(ERROR)) {					
					matris[i][0] = lista.get(i).getTipo();
					matris[i][1] = lista.get(i).getPalabra();
					matris[i][2] = String.valueOf(lista.get(i).getFila());
					matris[i][3] = String.valueOf(lista.get(i).getColumna() + 1);
				}else {
					lista.remove(i);
				}
			}

			table.setModel(new javax.swing.table.DefaultTableModel(matris,
					new String[] { "token", "lexema", "Fila", "Columna" }

			));
		} else {
			for (int i = 0; i < lista.size(); i++) {
				matris[i][0] = lista.get(i).getTipo();
				matris[i][1] = lista.get(i).getPalabra();
				matris[i][2] = String.valueOf(lista.get(i).getFila());
				matris[i][3] = String.valueOf(lista.get(i).getColumna() + 1);
			}
		}

			table.setModel(new javax.swing.table.DefaultTableModel(matris, 
					new String[] { "token", "lexema", "Fila", "Columna" }
			
				));

	}

	/**
	 * cuenta los errores, si es mayor a 0 solo se mostraran en la tabla de salida los errores
	 * 
	 * @return verdadero si cantidad de errores es mayor a 0 y falso si no hay errores
	 */
	public boolean recuentoErrores() {
		boolean a = false;
		int cantidadErrores = 0;
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getTipo().equals("ERROR")) {
				cantidadErrores++;
			}
		}
		if (cantidadErrores > 0)
			a = true;
		return a;
	}

	/**
	 * verifica si es la entrada es un espacio o un salto de linea
	 * @param letra la entrada a verificar
	 * @return verdadero si es una letra para comprobar y falso si es un espacio o salto
	 * de liena 
	 */
	public boolean espacioEnter(char letra) {
		boolean espacioLiena = false;
		int valor = letra;
		if (valor != 32) {
			espacioLiena = true;
			if (valor == 10)
				espacioLiena = false;
		}
		return espacioLiena;
	}

}
