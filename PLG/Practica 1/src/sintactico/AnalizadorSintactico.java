package sintactico;

import java.io.FileInputStream;
import java.lang.reflect.Array;

import tablaSimbolos.TablaSimbolo;
import tablaSimbolos.Tipo;
import utilidades.PalabrasReservadas;

import excepciones.*;

import analizadorLex.AnalizadorLexico;
import analizadorLex.Token;

public class AnalizadorSintactico {
	public Token tokens[]; 
	public int pos_token;
	public ExcepcionSintactica excepcion = new ExcepcionSintactica();
	public TablaSimbolo tablaDeSimbolos= new TablaSimbolo();
	public FileInputStream ficheroEntrada;
	public void run (String fichero){
		
	}
	public void finish() {
		
	}
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
		if (this.tokenActual() == Token.VAR)
			VARIABLES1();
		else
			VARIALBES2();
		CONSTANTES();
		// TODO Auto-generated method stub
		
	}
	
	private void VARIABLES1() {
		Token t = rec();
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
		String lex_de_DEC = new String();
		int tipo_de_DEC= 0;
		DEC(lex_de_DEC,tipo_de_DEC);

		
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

	private int tokenActual() {
		// TODO Auto-generated method stub
		return 0;
	}

	private void TIPO(int tipo) {
		// TODO Auto-generated method stub
		
	}

	private void VARIABLE(String lex) {
		Token t = rec();
		// ID
		if (PalabrasReservadas.PALABRAS_RESERVADAS.containsKey(t.lexema)){
			// this isn't good word
			excepc
			
		}
		
		// TODO Auto-generated method stub
		
	}

}
