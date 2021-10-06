package archivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Archivo {

	private File archivo;
	
	
	public Archivo (String nombreArchivo) {
		archivo = new File(nombreArchivo);
	}

	/**
	 * manejara los textos en un archivo 
	 * @return
	 * @throws IOException
	 */
	public String getContenido() throws IOException{
		String contenido="";
		FileReader fr = new FileReader(archivo);
		BufferedReader lector = new BufferedReader(fr);
		String linea = lector.readLine();
		
		while(linea != null) {
			contenido += linea + "\n";
			linea = lector.readLine();
		}
		
		lector.close();
		fr.close();
		return contenido;
	}
	
	
	public void guardar(String contenido)throws IOException {
		
		PrintWriter escritor = new PrintWriter(archivo);
		escritor.write(contenido);
		escritor.close();
		
	}
}
