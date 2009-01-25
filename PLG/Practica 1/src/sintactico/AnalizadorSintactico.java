package sintactico;

import interprete.Operandos;
import interprete.TokenMaquina;

import java.util.Vector;

import sintactico.tablaSimbolos.TablaSimbolo;
import utilidades.BufferedFileReader;
import utilidades.Operaciones;
import utilidades.PalabrasReservadas;
import analizadorLex.AnalizadorLexico;
import analizadorLex.Token;
import excepciones.ExcepcionSintactica;
import excepciones.Mensaje;

public class AnalizadorSintactico {
	public Token tokens[]; 
	public int pos_token;
	public ExcepcionSintactica excepcion = new ExcepcionSintactica();
	public TablaSimbolo tablaDeSimbolos= new TablaSimbolo();
	public BufferedFileReader ficheroEntrada;
	public Vector <Object> operaciones;
	
	public void run (){
		
	}
	public void finish() {
		
	}
	public AnalizadorSintactico(BufferedFileReader file){
		operaciones = new Vector<Object>();
		ficheroEntrada = file;
		pos_token = 0;
		AnalizadorLexico aLex = new AnalizadorLexico();
		//tokens = aLex.getTokens();
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
		Token t = rec();
		BLOQUE();
		t = rec();
		if (t.codigo != Token.PUNTO) {
			excepcion.addMensaje(Mensaje.ERROR_TOKEN_INCORRECTO,Token.PUNTO,t);
		}
		
		
		
	}

	private void BLOQUE() {
		Token t= rec();
		if (t.codigo != Token.BEGIN) {
			excepcion.addMensaje(Mensaje.ERROR_TOKEN_INCORRECTO,Token.BEGIN,t);
		}
		SENTS();
		t = rec();
		if (t.codigo != Token.END) {
			excepcion.addMensaje(Mensaje.ERROR_TOKEN_INCORRECTO,Token.END,t);
		}
	}
	private void SENTS() {
		SENT();
		RSENTS();
		// TODO Auto-generated method stub
		
	}
	private void DECLARACIONES() {
		if (this.tokenSiguiente() == Token.VAR)
			VARIABLES1();
		else
			VARIABLES2();
		if (this.tokenSiguiente() == Token.CONST)
			CONSTANTES1();
		
		// no es necesario 
		/*
		 * else
		 * 	CONSTANTES2();
		 */
			
		
		
	}
	/*
	private void CONSTANTES2() {
		
		
	}*/
	private void CONSTANTES1() {
		rec();
		// si entra en esta funcino es porque el token es CONST
		
		DECS_CONST();
		
		
	}
	private void DECS_CONST() {
		Token lex_de_DEC_CONST = new Token();
		Token tipo_de_DEC_CONST = new Token();
		DEC_CONST(lex_de_DEC_CONST,tipo_de_DEC_CONST);
		// err = err v existeID();
		if (this.tablaDeSimbolos.existeID(lex_de_DEC_CONST.lexema))
			this.excepcion.addMensaje(Mensaje.ERROR_ID_DUPLICADO,Token.ID,lex_de_DEC_CONST);
		else
			
			this.tablaDeSimbolos.añadeID(lex_de_DEC_CONST.lexema, tipo_de_DEC_CONST.codigo, false);
		if (this.tokenSiguiente() == Token.PUNTO_Y_COMA);
			RDECS_CONST();
		
		// RDECS_CONST2(); no es necseario porque no hace nada
		
		
	}
	private void DEC_CONST(Token lex, Token tipo) {
		VARIABLE(lex);
		Token t = rec(); //:
		if (t.codigo != Token.OP_COMPARACION)
			excepcion.addMensaje(Mensaje.ERROR_TOKEN_INCORRECTO,Token.OP_COMPARACION,t);
		VALOR(tipo);
	}
	private void VALOR(Token tipo) {
		Token t = rec();
		Operandos oper;
		switch (t.codigo) {
		case Token.NUM :
			tipo.codigo = Token.INTEGER;
			emit(TokenMaquina.APILA,oper);
			break;
		case Token.NUMREAL :
			tipo.codigo = Token.REAL;
			emit(TokenMaquina.APILA,oper);
			break;
		case Token.VALORCHAR :
			tipo.codigo = Token.CHAR;
			emit(TokenMaquina.APILA,oper);
			break;
		case Token.VALORBOOLEAN :
			tipo.codigo = Token.BOOLEAN;
			emit(TokenMaquina.APILA,oper);
			break;
		default:
				
		}
		
		
		
		
		// TODO Auto-generated method stub
		
	}

	private void emit(Vector<Object> opers) {
		// TODO Auto-generated method stub
		
	}
	private void RDECS_CONST() {
		rec();
		// no hace falta reconocer el token porqu si se mete aqui es que es un ;
		
		Token lex_de_DEC_CONST = new Token();
		Token tipo_de_DEC_CONST = new Token();
		DEC_CONST(lex_de_DEC_CONST,tipo_de_DEC_CONST);
		
		// err = err v existeID();
		if (this.tablaDeSimbolos.existeID(lex_de_DEC_CONST.lexema))
			this.excepcion.addMensaje(Mensaje.ERROR_ID_DUPLICADO,Token.ID,lex_de_DEC_CONST);
		else
			
			this.tablaDeSimbolos.añadeID(lex_de_DEC_CONST.lexema, tipo_de_DEC_CONST.codigo, false);
		if (this.tokenSiguiente() == Token.PUNTO_Y_COMA);
			RDECS_CONST();
		
		
	}
	private void VARIABLES1() {
		rec(); //VAR 
		// it's necessary because it's cheked in DECLARACIONES
		// if (t.codigo == Token.VAR)
		DECS();
		
		
		
	}

	private void VARIABLES2() {
		this.tablaDeSimbolos.creaTS();
		
	}



	

	private void DECS() {
		Token lex_de_DEC = new Token();
		Token tipo_de_DEC= new Token();
		DEC(lex_de_DEC,tipo_de_DEC);
		tablaDeSimbolos.creaTS();
		// it's not necessary because is the first variable
		// tablaDeSimbolos.existeID();
		if (esReservada(lex_de_DEC.lexema)){
			excepcion.addMensaje(Mensaje.ERROR_ID_PALABRA_RESERVADA,Token.ID,lex_de_DEC);
		}
		else
		tablaDeSimbolos.añadeID(lex_de_DEC.lexema,tipo_de_DEC.codigo,true);
		if (tokenSiguiente() == Token.PUNTO_Y_COMA)
			RDECS1();
		//RDECS2(); no hace nada no se crea
		
	}

	private boolean esReservada(String lexema) {
		// TODO Auto-generated method stub
		return false;
	}
	private void RDECS1() {
		// TODO Auto-generated method stub
		Token t = rec();
		
		if (t.codigo != Token.PUNTO_Y_COMA)
			excepcion.addMensaje(Mensaje.ERROR_FALTA_PUNTO_Y_COMA,Token.PUNTO_Y_COMA,t);
				
		// XXX puede no funcionar referencias y mierdas varias
		Token tipo_de_DEC = new Token();
		Token lex_de_DEC = new Token();
		DEC(lex_de_DEC,tipo_de_DEC);
		if(tablaDeSimbolos.existeID(lex_de_DEC.lexema))
			excepcion.addMensaje(Mensaje.ERROR_ID_DUPLICADO,Token.ID,lex_de_DEC);
		else
		tablaDeSimbolos.añadeID(lex_de_DEC.lexema, tipo_de_DEC.codigo, true);
	}
	private void DEC(Token lex, Token tipo) {
		
		// XXX posible error
		
		VARIABLE(lex);
		Token t = rec(); //:
		if (t.codigo != Token.DOSPUNTOS)
			excepcion.addMensaje(Mensaje.ERROR_TOKEN_INCORRECTO,Token.DOSPUNTOS,t);
		TIPO(tipo);
		
	}

	private int tokenSiguiente() {
		// TODO Auto-generated method stub
		return this.tokens[this.pos_token+1].codigo;
	}

	private void TIPO(Token tipo) {
		Token t = rec();
		// estan entre los tipos? los tipos estan
		// entre -1000 y -1999
		if ((t.codigo < -1000) && (t.codigo >-1999))
			tipo = t.clon();
		else
			excepcion.addMensaje(Mensaje.ERROR_TIPOS,0,t);
	}

	private void VARIABLE(Token lex) {
		ID(lex);
	}
	private void ID(Token lex) {
		Token t = rec();
		if (PalabrasReservadas.PALABRAS_RESERVADAS.containsKey(t.lexema)){
			// this isn't good word
			excepcion.addMensaje(Mensaje.ERROR_ID_PALABRA_RESERVADA,Token.ID,t);
			
		}
		lex = t.clon();
		
		
	}

}
