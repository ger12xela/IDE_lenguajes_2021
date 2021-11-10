package tablaSimbolos;

import java.util.ArrayList;

import automata.Token;

public class Simbolos {

	ArrayList<Token> sim = new ArrayList<Token>();

	public Simbolos() {

	}

	public void agregar(Token a) {

	}

	public void buscar(Token a) {
		for (int i = 0; i < sim.size(); i++) {
			if (sim.get(i).getTipo().equals(a.getTipo())) {

			}
		}
	}
}
