package main;

import java.io.IOException;

/**
 * @author GabiPC
 * Permite crear objetos testeables
 * Tanto el analizador lexico, sintactico y la MaquinaEjecucion deben implementar el metodo run() 
 * definiendo el codigo de las pruebas.
 */
public interface Testeable {

	public void run() throws Exception;
	
	/**
	 * permite la modificacion del archivo de lectura.
	 * @param source
	 */
	public void setArchivoLectura(String source) throws IOException;
	
	public void finish();
	
	/**
	 * Verifica si el archivo de lectura tiene la extension correcta
	 * @param source
	 * @return
	 */
	public boolean extensionArchivoValida(String file);	
	
}
