package automata;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class Guia {

	public Guia() {

	}

	// array de estados predeterminados para el atomata 
	private int[] estadosAcepatacion = { 53, 12, 16, 24, 32, 35, 109, 45, 56, 58, 60, 62, 64, 66, 70, 75};
	private Estado[] estados = { 
			new Estado(0, 'E', 5), 
			new Estado(5, 'N', 47), 
			new Estado(47, 'T', 48), 
			new Estado(48, 'O', 49), 
			new Estado(49, 'O', 50), 
			new Estado(50, 'C', 51),
			new Estado(51, 'E', 52),
			new Estado(52, 'S', 53), //ACETACION ENTONCES
			new Estado(5, 'S', 6),
			new Estado(6, 'C', 7),
			new Estado(7, 'R', 8),
			new Estado(9, 'I', 9),
			new Estado(9, 'B', 10),
			new Estado(10, 'I', 11),
			new Estado(11, 'R', 12), // ACEOTACION ESCRIBIR
			new Estado(0, 'F', 14),
			new Estado(14, 'I', 15),
			new Estado(15, 'N', 16),//ACEPTACION FIN
			new Estado(0, 'R', 18),
			new Estado(19, 'E', 19),
			new Estado(19, 'P', 20),
			new Estado(20, 'E', 21),
			new Estado(21, 'T', 22),
			new Estado(22, 'I', 23),
			new Estado(23, 'R', 24),// ACEPTACION REPETIR
			new Estado(0, 'I', 26),
			new Estado(26, 'N', 27),
			new Estado(27, 'I', 28),
			new Estado(28, 'C', 29),
			new Estado(29, 'I', 30),
			new Estado(30, 'A', 31),
			new Estado(31, 'R', 32),
			new Estado(31, 'R', 32),// ACEPTACION INICIAR
			new Estado(0, 'S', 34),
			new Estado(34, 'I', 35),// ACEPTACION SI
			new Estado(0, 'V', 101),
			new Estado(101, 'E', 102),
			new Estado(102, 'R', 103),
			new Estado(103, 'D', 104),
			new Estado(104, 'A', 105),
			new Estado(105, 'D', 106),
			new Estado(106, 'E', 107),
			new Estado(107, 'R', 108),
			new Estado(108, 'O', 109),// ACEPTACION VERDADERO
			new Estado(0, 'F', 41),
			new Estado(41, 'A', 42),
			new Estado(42, 'L', 43),
			new Estado(43, 'S', 44),
			new Estado(44, 'O', 45),// ACEPTACION FALSO
			new EstadoRango(0,' ',56,48,57), // Digitos 
			new EstadoRango(56,' ',56,48,57), // Digitos 
			new Estado(0, '-', 55),
			new EstadoRango(55,' ',56,48,57), // Digitos haceptacion numero enteros
			new Estado(0, ')', 58),// cerrar agrupacion
			new Estado(0, '(', 60),// abrir agrupacion
			new Estado(0, '*', 62),// mutiplicacion 
			new Estado(0, '+', 64),// suma 
			new Estado(0, '=', 66),// asiganacion 
			new Estado(0, '/', 68),//  
			new Estado(68, '/', 69),//  
			new EstadoRango(69,' ',70,65,90),		// letras mayusculas 
			new EstadoRango(69,' ',79,97,122), // letras minusculas 
			new EstadoRango(69,' ',70,48,57), // Digitos                   comentario linea
			new Estado(0, '"', 73),//  
			new EstadoRango(73,' ',74,35,250),
			new Estado(74, '"', 75),//  literal
			new Estado(0, '_', 77),
			new EstadoRango(77,' ',2,65,90),		// letras mayusculas 
			new EstadoRango(2,' ',2,97,122), // letras minusculas 
			
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
