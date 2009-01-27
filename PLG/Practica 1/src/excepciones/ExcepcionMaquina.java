package excepciones;

import java.util.Vector;

/**
 * @author GabiPC
 *
 */
public class ExcepcionMaquina extends Exception {
	
	/**
	 * Un serial por defecto
	 */
	private static final long serialVersionUID = -951330632360201136L;
	public MensajeMaquina erroresMaquina;
	
	/**
	 * Constructor parametrizado que ejecuta el metodo de imprimirErro() del mensaje de
	 * error.
	 * @param i Tipo de excepcion producida
	 */
	public ExcepcionMaquina(int i){
		this.erroresMaquina = new MensajeMaquina(i);
		this.erroresMaquina.imprimirError();
	}
}
