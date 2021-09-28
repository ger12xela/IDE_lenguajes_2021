package archivo;

import java.io.IOError;

public class Editor {
	
	private Archivo archivo;
	
	public Editor () {
		archivo =null;
	}
	
	//metodos
	/**
	 * abre un archivo existente
	 * @param nombre (nombre del archivo)
	 * @return string el contenido del archivo
	 * @throws Exception 
	 */
	public String abrir(String nombreArchivo) throws Exception {
		String contenido = "";
		archivo = new Archivo(nombreArchivo);
		try {
			contenido = archivo.getContenido();
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("Error al leer el archivo", e );
		}
		
		return contenido;
	}

	public void crear() {
		archivo = null;
	}
	
	public void guardar(String contendio, String ruta) throws Exception {
		if(archivo == null) {
			archivo = new Archivo(ruta);
		}
		try {
			
			archivo.guardar(contendio);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("error al guardar archivo",e);
		}
	}
	
	public boolean esArchivoNuevo() {
		return archivo == null;
	}
}
