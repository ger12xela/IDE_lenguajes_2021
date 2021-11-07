package gramatica;

public class Produccion {

	String nombre;
	boolean Termimal;
	public Produccion(String nombre, boolean termimal) {
		super();
		this.nombre = nombre;
		Termimal = termimal;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isTernimal() {
		return Termimal;
	}
	public void setTernimal(boolean termimal) {
		Termimal = termimal;
	}
	
}
