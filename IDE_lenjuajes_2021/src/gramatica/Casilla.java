package gramatica;

import java.util.Stack;

public class Casilla {
	
		Stack <Produccion> pila = new Stack<Produccion>();
		public Casilla() {
		}
		
		public Stack<Produccion> getPila() {
			
			return pila;
		}
		
		public void push(Produccion c) {
			pila.push(c);
			

		}
		
		public void pop() {
			pila.pop();

		}
		
		public boolean estaVacia() {
			return pila.empty();
		}
		
	
	
	
	
//	public static void main(String[] args) {
//		Stack <Produccion> pila = new Stack <Produccion>();
//		
//		Produccion uno = new Produccion("H", true);
//		Produccion dos = new Produccion("O", true);
//		Produccion tres = new Produccion("L", true);
//		Produccion cuatro = new Produccion("A", true);
//		
//		pila.push(uno);
//		pila.push(dos);
//		pila.push(tres);
//		pila.push(cuatro);
//		
//		int i = 0;
//		while(i<pila.size()) {
//			System.out.println(pila.get(i).nombre);
//			System.out.println("entrada");
//			i++;
//		}
//		
//		do {
//			System.out.println(pila.peek().nombre);
//			pila.pop();
//		} while (pila.empty()==false);
//		
//	}

}
