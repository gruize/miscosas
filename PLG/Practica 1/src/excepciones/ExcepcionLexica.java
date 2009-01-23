package excepciones;

import java.util.Vector;

public class ExcepcionLexica extends Exception {
public Vector erroresLex=null;
public int numErrores=0;

	public ExcepcionLexica(int i, int numLinea) {
		
		erroresLex.add(new Mensaje("i",numLinea));
		numErrores++;
		// TODO Auto-generated constructor stub
	}

	public ExcepcionLexica(int i, String string, int lastLine) {
		// TODO Auto-generated constructor stub
	}

}
