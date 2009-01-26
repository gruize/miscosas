package excepciones;

import java.util.Vector;



public class ExcepcionLexica extends Exception{
	
	/**
	 * UN serial por defecto
	 */
	 
	public Vector<MensajeLex> errores = new Vector<MensajeLex>(); 
	private static final long serialVersionUID = 2L;
	public ExcepcionLexica(){
		super();
	}
	
	
	public ExcepcionLexica(MensajeLex mensajeLex) {
		
	}
	
	
	public void addMensaje(MensajeLex msg){
		errores.add(msg);
	}
	public void addMensaje(String m, int f, int c){
		errores.addElement(new MensajeLex(m,f,c));
	}
	public void printAll(){
		for (MensajeLex msg:errores){
			System.out.println(msg.toString());
		}
	}
}