package archivo;

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

	/**
	 * crea un nuevo archivo 
	 */
	public void crear() {
		archivo = null;
	}
	
	
	/**
	 * metodo para guardar contenido de texto en un archivo 
	 * @param contendio del archivo 
	 * @param ruta en donde se guardara el conenido 
	 * @throws Exception si no hay archivo 
	 */
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
	
	/**
	 * retorna un archivo nuevo 
	 * @return
	 */
	public boolean esArchivoNuevo() {
		return archivo == null;
	}
}
