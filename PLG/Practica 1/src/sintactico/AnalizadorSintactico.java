package sintactico;

import java.lang.reflect.Array;

import tablaSimbolos.TablaSimbolo;
import tablaSimbolos.Tipo;

import excepciones.*;

import analizadorLex.AnalizadorLexico;
import analizadorLex.Token;

public class AnalizadorSintactico {
	public Token tokens[]; 
	public int pos_token;
	public ExcepcionSintactica excepcion = new ExcepcionSintactica();
	public TablaSimbolo tablaDeSimbolos= new TablaSimbolo();
	public AnalizadorSintactico(){
		pos_token = 0;
		AnalizadorLexico aLex = new AnalizadorLexico();
		tokens = aLex.getTokens();
		try {
			this.PROG();
		} catch (ExcepcionSintactica e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Token rec(){
		this.pos_token++;
		return this.tokens[this.pos_token-1];
	}
	
	
	private void PROG() throws ExcepcionSintactica{
		CABECERA();
		CUERPO();

		
	}
	private void CABECERA () throws ExcepcionSintactica {
		Token t = rec();
		if (t.codigo != Token.PROGRAM){
			this.excepcion.addMensaje(Mensaje.ERROR_TOKEN_INCORRECTO,Token.PROGRAM,t);
		}
		t = rec();
		if (t.codigo != Token.ID){
			this.excepcion.addMensaje(Mensaje.ERROR_TOKEN_INCORRECTO,Token.ID,t);
		}
		t = rec();
		if (t.codigo != Token.PUNTO_Y_COMA){
			this.excepcion.addMensaje(Mensaje.ERROR_TOKEN_INCORRECTO,Token.PUNTO_Y_COMA,t);
		}
	}

	
	private void CUERPO() throws ExcepcionSintactica {
		DECLARACIONES();
		INSTRUCCIONES();// TODO Auto-generated method stub
		
	}

	private void INSTRUCCIONES() {
		// TODO Auto-generated method stub
		
	}

	private void DECLARACIONES() {
		VARIABLES();
		CONSTANTES();
		// TODO Auto-generated method stub
		
	}

	private void CONSTANTES() {
		// TODO Auto-generated method stub
		
	}

	private void VARIABLES() {
		Token t = rec();
		if (t.codigo != Token.VAR){
			/*
			 * VARIABLES::= {ts=creaTS()}
			 */
			this.pos_token--;
			this.tablaDeSimbolos.creaTS();
		}
		
		else {
			/*
			 * VARIABLES::= {Rec(tkVAR)}
			 * 				DECS 
			 */
			DECS();
		}
		

		// TODO Auto-generated method stub
		
	}

	private void DECS() {
		String lex_de_DEC = new String();
		int tipo_de_DEC= 0;
		DEC(lex_de_DEC,tipo_de_DEC);
		// TODO Auto-generated method stub
		
	}

	private void DEC(String lex, int tipo) {
		
		// XXX posible error
		VARIABLE(lex);
		Token t = rec();
		TIPO(tipo);
		if (t.codigo != Token.DOSPUNTOS)
		{
			this.excepcion.addMensaje(Mensaje.ERROR_TOKEN_INCORRECTO, Token.DOSPUNTOS, t);
		}
		
		// TODO Auto-generated method stub
		
	}

	private void TIPO(int tipo) {
		// TODO Auto-generated method stub
		
	}

	private void VARIABLE(String lex) {
		// TODO Auto-generated method stub
		
	}

}
