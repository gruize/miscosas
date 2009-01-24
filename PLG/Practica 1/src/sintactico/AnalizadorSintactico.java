package sintactico;

import java.io.FileInputStream;
import java.lang.reflect.Array;

import sintactico.tablaSimbolos.DatosTabla;
import sintactico.tablaSimbolos.TablaSimbolo;
import sintactico.tablaSimbolos.Tipo;
import utilidades.BufferedFileReader;
import utilidades.PalabrasReservadas;

import excepciones.*;

import analizadorLex.AnalizadorLexico;
import analizadorLex.Token;

public class AnalizadorSintactico {
	public Token tokens[]; 
	public int pos_token;
	public ExcepcionSintactica excepcion = new ExcepcionSintactica();
	public TablaSimbolo tablaDeSimbolos= new TablaSimbolo();
	public BufferedFileReader ficheroEntrada;
	public void run (){
		
	}
	public void finish() {
		
	}
	public AnalizadorSintactico(BufferedFileReader file){
		ficheroEntrada = file;
		pos_token = 0;
		AnalizadorLexico aLex = new AnalizadorLexico();
		tokens = aLex.getTokens();
		try {
			this.PROG();
		} catch (ExcepcionSintactica e) {
			
			
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
		INSTRUCCIONES();
		
	}

	private void INSTRUCCIONES() {
		// TODO Auto-generated method stub
		
	}

	private void DECLARACIONES() {
		if (this.tokenSiguiente() == Token.VAR)
			VARIABLES1();
		else
			VARIALBES2();
		CONSTANTES();
		
		
	}
	
	private void VARIABLES1() {
		rec(); //VAR 
		// it's necessary because it's cheked in DECLARACIONES
		// if (t.codigo == Token.VAR)
		DECS();
		
		
		
	}

	private void VARIALBES2() {
		this.tablaDeSimbolos.creaTS();
		
	}

	private void CONSTANTES() {
		// TODO Auto-generated method stub
		
	}

	

	private void DECS() {
		Token lex_de_DEC = new Token();
		Token tipo_de_DEC= new Token();
		DEC(lex_de_DEC,tipo_de_DEC);
		tablaDeSimbolos.creaTS();
		// it's not necessary because is the first variable
		// tablaDeSimbolos.existeID();
		
		tablaDeSimbolos.añadeID(lex_de_DEC.lexema,tipo_de_DEC.codigo,true);
		if (tokenSiguiente() == Token.PUNTO_Y_COMA)
			RDECS1();
		//RDECS2(); no hace nada no se crea
		
	}

	private void RDECS1() {
		// TODO Auto-generated method stub
		Token t = rec();
		// no es necesario
		/*if (t.codigo != Token.PUNTO_Y_COMA)
			excepcion.addMensaje(Mensaje.ERROR_FALTA_PUNTO_Y_COMA,Token.PUNTO_Y_COMA,t);
			*/
		
		// XXX puede no funcionar referencias y mierdas varias
		Token tipo_de_DEC = new Token();
		Token lex_de_DEC = new Token();
		DEC(lex_de_DEC,tipo_de_DEC);
		if(tablaDeSimbolos.existeID(lex_de_DEC.lexema))
			excepcion.addMensaje(Mensaje.ERROR_ID_DUPLICADO,Token.ID,lex_de_DEC);
	}
	private void DEC(Token lex_de_DEC, Token tipo_de_DEC) {
		
		// XXX posible error
		
		VARIABLE(lex_de_DEC);
		
		Token t = rec();
		if (t.codigo != Token.DOSPUNTOS)
		{
			this.excepcion.addMensaje(Mensaje.ERROR_TOKEN_INCORRECTO, Token.DOSPUNTOS, t);
		}
		
		TIPO(tipo_de_DEC);
		
		
		// TODO Auto-generated method stub
		
	}

	private int tokenSiguiente() {
		// TODO Auto-generated method stub
		return 0;
	}

	private void TIPO(Token tipo_de_DEC) {
		// TODO Auto-generated method stub
		
	}

	private void VARIABLE(Token lex_de_DEC) {
		Token t = rec();
		// ID
		if (PalabrasReservadas.PALABRAS_RESERVADAS.containsKey(t.lexema)){
			// this isn't good word
			excepcion.addMensaje(Mensaje.ERROR_ID_PALABRA_RESERVADA,Token.ID,t);
			
		}
		lex_de_DEC = t.clon(); 
	}

}
