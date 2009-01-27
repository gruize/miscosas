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
    public static int ERROR_EXPRESION_INCORRECTA=7;
    public static int ERROR_FIN_DE_FICHERO_INCORRECTO = 8;
    public static int ERROR_ERRORES_SINTACTICOS = 9;
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
			mensajeError="Error: Token inconrrecto en la linea: " +this.tokenErr.linea+ 
			" columna: "+this.tokenErr.columna;
			if (Token.LEXICOS.get(this.tokenOk) != "" )
				mensajeError = mensajeError+". Se esperaba el token "+Token.LEXICOS.get(this.tokenOk)
					+" y se obtubo: "+tokenErr.lexema;
		}
		else if(this.error==ERROR_ID_DUPLICADO)
		{
			mensajeError="Error: Identificador "+tokenErr.lexema+"duplicado en la linea: " +this.tokenErr.linea+ 
			" columna: "+this.tokenErr.columna;
		}
		else if(this.error==ERROR_ID_PALABRA_RESERVADA)
		{
			mensajeError="Error: En palabra reservada en la linea: " +this.tokenErr.linea+ 
			" columna: "+this.tokenErr.columna;
		}
		else if(this.error==ERROR_FALTA_PUNTO_Y_COMA)
		{
			mensajeError="Error: Falta punto y coma en la linea: " +this.tokenErr.linea+ 
			" columna: "+this.tokenErr.columna;
		}
		else if(this.error==ERROR_TIPOS)
		{
			mensajeError="Error: De tipos en la linea: " +this.tokenErr.linea+ 
			" columna: "+this.tokenErr.columna;
		}
		else if(this.error==ERROR_N0_EXISTE_ID)
		{
			mensajeError="Error: No existe el ID " +tokenErr.lexema+ "en la linea: " +this.tokenErr.linea+ 
			" columna: "+this.tokenErr.columna;
		}
		else if(this.error==ERROR_NO_MODIFICABLE)
		{
			mensajeError="Error: El ID "+tokenErr.lexema+" Es constate, no modificable en la linea: " +this.tokenErr.linea+ 
			" columna: "+this.tokenErr.columna;
		}		
		else if(this.error==ERROR_EXPRESION_INCORRECTA)
		{
			mensajeError="Error: La expresion no es correcta: " +this.tokenErr.linea+ 
			" columna: "+this.tokenErr.columna;
		}
		else if(this.error==ERROR_ERRORES_SINTACTICOS)
		{
			mensajeError="Error: El analisis sintactico no se ha realizado correctamente";
		}
		
		return mensajeError;
		//return tokenErr.linea +": " +errores[error];
		
	}
	
	
}
