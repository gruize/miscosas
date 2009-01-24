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
	private static final long serialVersionUID = 1L;
	public Vector erroresMaquina = new Vector();
	public int numErroresMaquina = 0;
	
	public ExcepcionMaquina(int i, int numLinea){
		this.erroresMaquina.add(new Mensaje("i",numLinea));
		this.numErroresMaquina++;
	}
	

	public ExcepcionMaquina(int i, String string, int numLinea) {
		this.erroresMaquina.add(new Mensaje("i"+" "+string,numLinea));
	}
}
