package excepciones;

import java.util.Vector;

public class ExcepcionSintactica extends Exception{
	public static boolean CortaAnalisisSintactico;
	/**
	 * UN serial por defecto
	 */
	static public Vector<Mensaje> errores = new Vector<Mensaje>(); 
	private static final long serialVersionUID = 2L;
	public ExcepcionSintactica(){
		super();
	}
	public void addMensaje(Mensaje msg){
		errores.add(msg);
	}
	public void printAll(){
		for (Mensaje msg:errores){
			System.out.println(msg.toString());
		}
	}
}
