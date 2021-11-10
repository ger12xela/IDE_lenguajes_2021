package gramatica;

import java.util.Stack;

import automata.Token;

public class Tabla {

	Casilla _00 = new Casilla();

	Casilla c10 = new Casilla();
	Casilla c20 = new Casilla();
	Casilla c30 = new Casilla();
	Casilla c01 = new Casilla();
	Casilla c02 = new Casilla();
	Casilla c03 = new Casilla();
	Casilla c04 = new Casilla();

	Casilla c13 = new Casilla();
	Casilla c23 = new Casilla();
	Casilla c31 = new Casilla();
	Casilla c32 = new Casilla();
	Casilla c34 = new Casilla();

	Casilla[][] matriz = new Casilla[4][5];
	{
		// filas primero
		matriz[0][0] = _00;
		matriz[1][0] = c10;
		matriz[2][0] = c20;
		matriz[3][0] = c30;
		matriz[0][1] = c01;
		matriz[0][2] = c02;
		matriz[0][3] = c03;
		matriz[0][4] = c04;

		matriz[1][3] = c13;
		matriz[2][3] = c23;
		matriz[3][1] = c31;
		matriz[3][2] = c32;
		matriz[3][4] = c34;
	}

	public Tabla() {
		c10.push(new Produccion("A", false));
		c20.push(new Produccion("B", false));
		c30.push(new Produccion("C", false));
		c01.push(new Produccion("+", true));
		c02.push(new Produccion("-", true));
		c03.push(new Produccion("numero", true));
		c04.push(new Produccion("ter", true));

		// interior tabla
		c13.push(new Produccion("B", false));
		c13.push(new Produccion("C", false));

		c23.push(new Produccion("numero", true));

		c31.push(new Produccion("+", true));
		c31.push(new Produccion("B", false));
		c31.push(new Produccion("C", false));

		c32.push(new Produccion("-", true));
		c32.push(new Produccion("B", false));
		c32.push(new Produccion("C", false));

		c34.push(new Produccion("ter", true));

	}

	Stack<Produccion> principal = new Stack<Produccion>();

	public void recorrido(Token tkn) {
		String nombre = tkn.getTipo();
		int fila = 0;
		int columna = 0;
		boolean reduce = false;
		while (reduce == false) {

		if (principal.empty()) {
			principal.push(new Produccion("ter", true));
			principal.push(c10.getPila().peek());

		}

			this.imprimir();
			System.out.println("recibido ------" + nombre);

			for (int i = 1; i < matriz.length; i++) {
				if (matriz[i][0].getPila().peek().getNombre().equals(principal.peek().getNombre())) {
					fila = i;
				}
			}
			for (int i = 1; i < matriz[0].length; i++) {
				if (matriz[0][i].getPila().peek().getNombre().equals(nombre)) {
					columna = i;
				}
			}

			// temporal
			Stack<Produccion> tmp = new Stack<Produccion>();
			try {
				for (int i = 0; i < matriz[fila][columna].getPila().size(); i++) {
					tmp.add(matriz[fila][columna].getPila().get(i));

				}

			} catch (Exception e) {
				// TODO: handle exception error en automata 
				System.out.println("error en la pila " + tkn.getColumna()+tkn.getColumna());
				

			}
			// shift
			principal.pop();

			while (tmp.empty() == false) {
				if (tmp.peek().getNombre().equals("ter") == false)
					principal.push(tmp.pop());
				else
					tmp.pop();
				System.out.println(principal.peek().getNombre() + " ingresa");
			}

			// reduce
			if (principal.empty() == false) {

				if (principal.peek().isTernimal()) {
					System.out.println(principal.peek().getNombre() + " se reduce");
					if (principal.peek().getNombre().equals(nombre)) {
						principal.pop();
						reduce = true;
					}
				}
			}

		}
	}

	public void imprimir() {
//		Stack<Produccion> imp = principal;
//		do {
//			System.out.print(imp.pop().getNombre() + " -->");
//		} while (imp.empty() == false);F
		for (int i = 0; i < principal.size(); i++) {
			System.out.print(principal.get(i).getNombre() + "-->");
		}
		System.out.println("");
		if (principal.empty())
			System.out.println("fin de archivo");
	}

	public static void main(String[] args) {
		Tabla tb = new Tabla();

		String a = "numero";
		tb.recorrido(a);
//		String b = "+";
//		tb.recorrido(b);
		String c = "numero";
		tb.recorrido(c);
		String d = "-";
		tb.recorrido(d);
		String e = "numero";
		tb.recorrido(e);
		String f = "ter";
		tb.recorrido(f);
		tb.imprimir();
	}
}
