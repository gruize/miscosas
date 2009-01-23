package excepciones;

import java.util.Vector;

import analizadorLex.Token;

public class ExcepcionSintactica extends Exception{
	public static boolean CortaAnalisisSintactico = false;
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
	public void addMensaje(int error, int tokenOk, Token tokenErr){
		errores.add(new Mensaje(error, tokenOk, tokenErr));
	}
	public void printAll(){
		for (Mensaje msg:errores){
			System.out.println(msg.toString());
		}
	}
}
