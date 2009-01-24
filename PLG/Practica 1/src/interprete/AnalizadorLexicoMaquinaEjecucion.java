package interprete;

import java.io.IOException;

import utilidades.BufferedFileReader;
import utilidades.Reader;

public class AnalizadorLexicoMaquinaEjecucion {

	/** Numero de línea del ultimo caracter leido */
	private int numLine;
	
	private Character ultimoCaracterLeido;
	
	/** Realiza la lectura de los caracteres del codigo fuente */
	private Reader lector = null;

	public AnalizadorLexicoMaquinaEjecucion() {
		/** Inicializa la lectura */
		this.numLine = 1; 
	}

	/** Constructor que inicializa la lectura y establece un lector del codigo fuente
	 * Puede generar excepciones debido a la lectura incorrecta de un caracter
	 * @param lector
	 * @throws IOException
	 */
	public AnalizadorLexicoMaquinaEjecucion(Reader lector) throws IOException{
		this.lector = lector;
		this.numLine = 1;
		this.ultimoCaracterLeido = null;
		this.leerCaracter();
	}
	

	/** Constructor que inicializa la lectura y establece un lector de codigo fuente mediante
	 * la especificacion del archivo de lectura. Puede generar excepciones debido a una mala
	 * apertura del archivo
	 * @param archivo
	 * @throws IOException
	 */
	public AnalizadorLexicoMaquinaEjecucion(String archivo) throws IOException {
		this.numLine = 1;
		this.establecerLector(archivo);
		this.leerCaracter();
	}
	
	/**
	 * Excepciones heredades de generar un nuevo BufferedFileReader
	 * @param archivo
	 * @return
	 * @throws IOException
	 */
	public void establecerLector(String archivo) throws IOException{
		if(this.lector != null){
			this.cerrarLector();
			this.lector = null;
		}
		this.lector = new BufferedFileReader(archivo);
		this.numLine = 1;
		this.ultimoCaracterLeido = null;
		
	}
	
	public void cerrarLector() throws IOException{
		this.lector.close();
	}
	
	public int getNumLine() {
		return numLine;
	}

	public void setNumLine(int numLine) {
		this.numLine = numLine;
	}

	public Character getUltimoCaracterLeido() {
		return ultimoCaracterLeido;
	}

	public void setUltimoCaracterLeido(Character ultimoCaracterLeido) {
		this.ultimoCaracterLeido = ultimoCaracterLeido;
	}

	public Reader getLector() {
		return lector;
	}

	public void setLector(Reader lector) {
		this.lector = lector;
	}

	public Character leerCaracter() throws IOException{
		if(this.lector == null)
			throw new IOException("Hay que especificar el lector");
		Character resultado = null;
		this.setUltimoCaracterLeido(this.lector.readCharacter());
		if(this.finDeLinea(this.getUltimoCaracterLeido()))
			this.setNumLine(this.getNumLine() + 1);		
		return resultado;
	}
	
	public boolean finDeLinea(Character caracter){
		return caracter.charValue() == '\n';
	}
	
	public TokenMaquina siguienteToken() throws Exception{
		TokenMaquina siguiente = null;
		//Separadores
		
		//End Of File <EOF>
		
		//Menos --> Genera numeros negativos
		
		//Coma
		
		//Numeros
		
		//Instrucciones
		
		return siguiente;
	}
}
