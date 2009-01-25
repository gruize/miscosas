package excepciones;

import analizadorLex.Token;

public class Mensaje{
	public int error;
	public Token tokenErr;
	public int tokenOk;
	public static int ERROR_TOKEN_INCORRECTO = 0;
	public static int ERROR_ID_DUPLICADO= 1;
	public static int ERROR_ID_PALABRA_RESERVADA=2;
	public static int ERROR_FALTA_PUNTO_Y_COMA= 3;
	public static int ERROR_TIPOS= 4;
	public static int ERROR_N0_EXISTE_ID=5;
    public static int ERROR_NO_MODIFICABLE=6;
	//public static String errores[];
	
	public Mensaje(int error, int tokenOk, Token tokenErr) {
		super();
		this.error = error;
		this.tokenOk = tokenOk;
		this.tokenErr = tokenErr;
	}
	public Mensaje(String error, int numF,int numC)
	{
		
	}
	public Mensaje(String string, int numLinea) {
		// TODO Auto-generated constructor stub
	}
	public String toString(){
		
		String mensajeError="";
		if(this.error==ERROR_TOKEN_INCORRECTO)
		{
			mensajeError="Error token inconrrecto en la línea: " +this.tokenErr.linea+ 
			"columna: "+this.tokenErr.columna+"/nSe esperaba: "+this.tokenOk;
		}
		else if(this.error==ERROR_ID_DUPLICADO)
		{
			mensajeError="Error: Identificador duplicado en la línea: " +this.tokenErr.linea+ 
			"columna: "+this.tokenErr.columna;
		}
		else if(this.error==ERROR_ID_PALABRA_RESERVADA)
		{
			mensajeError="Error: En palabra reservada en la línea: " +this.tokenErr.linea+ 
			"columna: "+this.tokenErr.columna;
		}
		else if(this.error==ERROR_FALTA_PUNTO_Y_COMA)
		{
			mensajeError="Error: Falta punto y coma en la línea: " +this.tokenErr.linea+ 
			"columna: "+this.tokenErr.columna;
		}
		else if(this.error==ERROR_TIPOS)
		{
			mensajeError="Error: De tipos en la línea: " +this.tokenErr.linea+ 
			"columna: "+this.tokenErr.columna;
		}
		else if(this.error==ERROR_N0_EXISTE_ID)
		{
			mensajeError="Error: No existe ID en la línea: " +this.tokenErr.linea+ 
			"columna: "+this.tokenErr.columna;
		}
		else if(this.error==ERROR_NO_MODIFICABLE)
		{
			mensajeError="Error: Es constate, no modificable en la línea: " +this.tokenErr.linea+ 
			"columna: "+this.tokenErr.columna;
		}
		
		return mensajeError;
		//return tokenErr.linea +": " +errores[error];
		
	}
	
	
}
