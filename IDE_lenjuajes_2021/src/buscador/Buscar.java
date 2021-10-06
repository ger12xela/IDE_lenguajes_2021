package buscador;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import automata.Estado;

public class Buscar {
	
	ArrayList<Estado> lista = new ArrayList<Estado>();
	String palabraBuscar;
	JTextArea entrada; 
	StyleContext st = new StyleContext(); 
	PanelMuestra pm = new PanelMuestra();
	JTextPane salida = pm.getTextpaneBusqueda();
	
	/**
	 * optiene la palabra a buscar
	 * @param palabraBuscar String palabra que se buscara en el editor 
	 * @param entrada el texto en el editor atrabes de un jTextarea
	 */
	public Buscar(String palabraBuscar, JTextArea entrada ) {
		this.palabraBuscar = palabraBuscar;
		this.entrada = entrada;
		salida.setText(entrada.getText());
		pm.setVisible(true);
		pm.setBusqueda(palabraBuscar);
		
	}

	/**
	 * utiliza la misma idea del automata genera estados apartir de la palabra de entrada para
	 * busqueda
	 */
	public void creaarEstados() {
		for (int i = 0; i < palabraBuscar.length(); i++) {
			char letra = palabraBuscar.charAt(i);
			Estado temp = new Estado(i, letra, i+1);
			lista.add(temp);
			
		}
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i).getActual()+" con "+lista.get(i).getLetra()+ " se mueve a " +lista.get(i).getSiguiente());
			
			
		}
	
	}
	
	/**
	 * con los estados creados ejecuta la busqueda en el texto de entrada 
	 */
	public void encontrar() {
		int actual = 0;
		
		for (int i = 0; i < entrada.getText().length(); i++) {
			char letra = entrada.getText().charAt(i);
			
			for (int j = 0; j < lista.size(); j++) {
				if(actual== lista.get(j).getActual()) {
					if(letra == lista.get(j).getLetra()) {
						actual = lista.get(j).getSiguiente();
					}
				}
				if (actual == lista.size()) {
					
					pintar(i);
					actual = 0;
					
				}
			}
		}		
	}
	
	
	int coincidencias = 0 ;
	
	/**
	 * colorea la palabra que conincidan con la busqueda, de color rojo 
	 * @param fin entero que indica la posicion de la pabalara, la ultima letra
	 */
	public void pintar(int fin) {
		coincidencias ++;
		int termina = fin+1;
		int inicia = termina- lista.size();

		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.red);
		
		salida.setCaretPosition(inicia);
		salida.setCharacterAttributes(aset, false);

		salida.select(inicia, termina);
		salida.setSelectedTextColor(Color.red);
		salida.requestFocusInWindow();
		salida.replaceSelection(palabraBuscar);
		pm.setCantidad(""+coincidencias);

	}
}
