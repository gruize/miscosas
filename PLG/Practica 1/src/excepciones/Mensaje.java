package excepciones;

import analizadorLex.Token;

public class Mensaje{
	public int error;
	public Token tokenErr;
	public int tokenOk;
	public static int ERROR_TOKEN_INCORRECTO = 0;
	public static String errores[] = {
		"Palabra no esperada"//ERROR_TOKEN_INCORRECTO 
	};
	public Mensaje(int error, int tokenOk, Token tokenErr) {
		super();
		this.error = error;
		this.tokenOk = tokenOk;
		this.tokenErr = tokenErr;
	}
	public Mensaje(String string, int numLinea) {
		// TODO Auto-generated constructor stub
	}
	public String toString(){
		
		return tokenErr.linea +": " +errores[error];
		
	}
}
