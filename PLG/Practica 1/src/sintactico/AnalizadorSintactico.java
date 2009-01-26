package sintactico;

import interprete.OperandoNum;
import interprete.OperandoNumReal;
import interprete.OperandoValorBoolean;
import interprete.OperandoValorChar;
import interprete.Operandos;
import interprete.TokenMaquina;

import java.io.IOException;
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
	public Vector<Token> tokens; 
	public int pos_token;
	public ExcepcionSintactica excepcion = new ExcepcionSintactica();
	public TablaSimbolo tablaDeSimbolos= new TablaSimbolo();
	public BufferedFileReader ficheroEntrada;
	public Vector <Object> operaciones;
	
	public void run (){
		try {
			this.PROG();
		} catch (ExcepcionSintactica e) {

			e.printAll();
		}
		if (excepcion.errores.size()>0)
			excepcion.printAll();
	}
	public void finish() {
		
	}
	public AnalizadorSintactico(String nameFile){
		operaciones = new Vector<Object>();
		pos_token = 0;
		AnalizadorLexico aLex;
		try {
			aLex = new AnalizadorLexico(nameFile);
			aLex.run();
			tokens = aLex.tokens;
			if (aLex.excepcion.errores.size()>0)
				aLex.excepcion.printAll();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
	
	private Token rec(){
		Token t = this.tokens.elementAt(this.pos_token);
		if (t == null)
		{
			excepcion.addMensaje(0,0,t);
			return null;
		}		
		this.pos_token++;
		return t; 
	}
	
	
	private void PROG() throws ExcepcionSintactica{
		CABECERA();
		CUERPO();
		emit(new Operaciones(TokenMaquina.STOP));

		
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
		//Token t = rec();
		BLOQUE();
		Token t = rec();
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
		if (this.tokenSiguiente().codigo == Token.PUNTO_Y_COMA)
			RSENTS();
		// inecesario
		// else
		// RSENTS2();
	}
	private void RSENTS() {
		rec(); // ;

		SENT();
		if (this.tokenSiguiente().codigo == Token.PUNTO_Y_COMA)
			RSENTS();
		
	}
	private void SENT() {
		
		switch (this.tokenSiguiente().codigo) {
			case Token.READ :
				SREAD();
				break;
			case Token.WRITE :
				SWRITE();
				break;
			case Token.ID :
				SASIGN();
				break;
		}
		
	}
	private void SASIGN() {
		
		
		Token lex_de_VARIABLE = new Token();
		VARIABLE(lex_de_VARIABLE);
		Token t = rec();
		if (t.codigo != Token.OP_ASIGNACION)
			excepcion.addMensaje(Mensaje.ERROR_TOKEN_INCORRECTO, Token.OP_ASIGNACION,t);
		else if (!tablaDeSimbolos.existeID(lex_de_VARIABLE.lexema))
			excepcion.addMensaje(Mensaje.ERROR_N0_EXISTE_ID, Token.ID,t);
		else if (!tablaDeSimbolos.dameModificable(lex_de_VARIABLE.lexema))
			excepcion.addMensaje(Mensaje.ERROR_NO_MODIFICABLE, Token.ID,t);
		else
		{
			Token tipo_de_EXP = new Token();
			EXP(tipo_de_EXP);
			if (!Operacion.tiposAsignacion(
					tablaDeSimbolos.dameTipo(lex_de_VARIABLE.lexema),
					tipo_de_EXP.codigo)) {
				excepcion.addMensaje(Mensaje.ERROR_TIPOS,Token.ID,t);
				
				
			}
			else {
				emit(new Operaciones(TokenMaquina.DESAPILA_DIR));
				emit(new OperandoNum(tablaDeSimbolos.dameDir(lex_de_VARIABLE.lexema)));
			}
			
			
			
		}
	}

	private void SWRITE() {
		rec(); // si esta aqui es que es un WRITE

		Token t = rec();
		if (t.codigo != Token.A_PARENTESIS)
			excepcion.addMensaje(Mensaje.ERROR_TOKEN_INCORRECTO,Token.A_PARENTESIS,t);
		else{
			Token tipo_de_EXP = new Token();
			EXP(tipo_de_EXP);
			emit(new Operaciones(TokenMaquina.ESCRITURA));
			t = rec();
			if (t.codigo != Token.C_PARENTESIS)
				excepcion.addMensaje(Mensaje.ERROR_TOKEN_INCORRECTO, Token.C_PARENTESIS, t);
		}
			
		

	}
	private void EXP(Token tipo) {
		Token tipo_de_EXPSIMPLE = new Token();
		EXPSIMPLE(tipo_de_EXPSIMPLE);
		// es un token de operacion?
		if (Operacion.getPrioridad(this.tokenSiguiente(), 2) ==3)

			REXP1(tipo_de_EXPSIMPLE,tipo);
		else 
			REXP2(tipo_de_EXPSIMPLE,tipo);
		
	}
	private void REXP1(Token tipo_companero, Token tipo) {
		Token t = rec();// OPERACOIN TIPO 3
		Operacion o = new Operacion(t.codigo,2);
		Token tipo_de_EXPSIMPLE = new Token();
		EXPSIMPLE(tipo_de_EXPSIMPLE);
		
		if (o.compruebaTipos(tipo_companero.codigo, tipo_de_EXPSIMPLE.codigo))
			excepcion.addMensaje(Mensaje.ERROR_TIPOS, t.codigo,tipo_companero);
		else{
			tipo_companero.codigo = o.dameTipo(tipo_companero.codigo, tipo_de_EXPSIMPLE.codigo);
			emit(new Operaciones(o.codigo));
		}
		if (Operacion.getPrioridad(this.tokenSiguiente(), 2) ==3)

			REXP1(tipo_companero,tipo);
		else 
			REXP2(tipo_companero,tipo);

		

		
	}
	private void REXP2(Token tipo_companero, Token tipo) {
		tipo.codigo = tipo_companero.codigo;
		
	}

	private void EXPSIMPLE(Token tipo) {
		Token tipo_de_TERMINO = new Token();
		TERMINO(tipo_de_TERMINO);
		// es un token de operacion de mi nivel?
		if (Operacion.getPrioridad(this.tokenSiguiente(), 2) ==2)
			REXPSIMPLE1(tipo_de_TERMINO,tipo);
		else 
			REXPSIMPLE2(tipo_de_TERMINO,tipo);		
	}
	private void REXPSIMPLE1(Token tipo_companero, Token tipo) {
		Token t = rec();// OPERACOIN TIPO 2
		Operacion o = new Operacion(t.codigo,2);
		Token tipo_de_TERMINO = new Token();
		TERMINO(tipo_de_TERMINO);
		
		if (o.compruebaTipos(tipo_companero.codigo, tipo_de_TERMINO.codigo))
			excepcion.addMensaje(Mensaje.ERROR_TIPOS, t.codigo,tipo_companero);
		else{
			
			tipo.codigo = o.dameTipo(tipo_companero.codigo,tipo_de_TERMINO.codigo);
			emit(new Operaciones(o.codigo));
		}
		if (Operacion.getPrioridad(this.tokenSiguiente(), 2) ==2)
			REXPSIMPLE1(tipo_de_TERMINO,tipo);
		else 
			REXPSIMPLE2(tipo_de_TERMINO,tipo);		
	}
	private void REXPSIMPLE2(Token tipo_companero, Token tipo) {
		tipo.codigo = tipo_companero.codigo;
		
	}

	private void TERMINO(Token tipo) {
		Token tipo_de_FACTOR = new Token();

		FACTOR(tipo_de_FACTOR);
		if (Operacion.getPrioridad(this.tokenSiguiente(), 2) ==1)
			RTERMINO1(tipo_de_FACTOR,tipo);
		else 
			RTERMINO2(tipo_de_FACTOR,tipo);
	
	}

		


	private void RTERMINO1(Token tipo_companero, Token tipo) {
		Token t = rec();// OPERACOIN TIPO 1
		Token tipo_de_FACTOR = new Token();
		Operacion o = new Operacion(t.codigo,2);

		FACTOR(tipo_de_FACTOR);
		
		if (o.compruebaTipos(tipo_companero.codigo, tipo_de_FACTOR.codigo))
			excepcion.addMensaje(Mensaje.ERROR_TIPOS, t.codigo,tipo_companero);
		else{
			
			tipo.codigo = o.dameTipo(tipo_companero.codigo,tipo_de_FACTOR.codigo);
			emit(new Operaciones(o.codigo));
		}

		if (Operacion.getPrioridad(this.tokenSiguiente(), 2) ==1)
			RTERMINO1(tipo_de_FACTOR,tipo);
		else 
			RTERMINO2(tipo_de_FACTOR,tipo);

	}
	private void RTERMINO2(Token tipo_companero, Token tipo) {
		tipo.codigo = tipo_companero.codigo;
		
		
	}
	
	
	private void FACTOR(Token tipo) {
		
		Operacion oper = null;
		if (Operacion.getPrioridad(tokenSiguiente(), 1)==0)
			 oper = new Operacion(rec().codigo,1);

		
		COMPONENTE(tipo);
		if (oper != null)
		{
			if (!oper.compruebaTipos(tipo.codigo))
				excepcion.addMensaje(Mensaje.ERROR_TIPOS,0,tipo);
			else
				emit(new Operaciones(oper.codigo));
		}
		// NO TIENE ASOCIATIVIDAD, NO MAS ABAJO
	}


	

	
	private void COMPONENTE(Token tipo) {
		Token t = rec();
		if (t.codigo == Token.A_PARENTESIS)
		{
			EXP(tipo);
			if ((t = rec()).codigo != Token.C_PARENTESIS)
				excepcion.addMensaje(Mensaje.ERROR_TOKEN_INCORRECTO, Token.C_PARENTESIS, t);
		}
		else
		{
			Operandos o = null;
			if (t.codigo == Token.NUM)
			{
				tipo.codigo = Token.INTEGER;
				o = new OperandoNum(Integer.valueOf(t.lexema));
			}
			else if (t.codigo == Token.NUMREAL)
			{
				tipo.codigo = Token.REAL;
				o = new OperandoNumReal(Double.valueOf(t.lexema));
			}
			else if (t.codigo == Token.VALORBOOLEAN)
			{
				tipo.codigo = Token.BOOLEAN;
				o = new OperandoValorBoolean(Boolean.valueOf(t.lexema));
				
			}
			else if (t.codigo == Token.VALORCHAR)
			{
				tipo.codigo = Token.CHAR;
				o = new OperandoValorChar(t.lexema.charAt(0));
			}
			if (o != null)
			{
				emit(new Operaciones(TokenMaquina.APILA));
				emit(o);
			}
			else if (t.codigo == Token.ID)
			{
				if (!tablaDeSimbolos.existeID(t.lexema))
				{
					excepcion.addMensaje(Mensaje.ERROR_N0_EXISTE_ID, Token.ID, t);
				}
				else
				{
					emit(new Operaciones(TokenMaquina.APILA_DIR));
					int dir = tablaDeSimbolos.dameDir(t.lexema);
					emit(new OperandoNum(dir));
				}
			}
		}
	}
	

	
	private void SREAD() {
		rec(); // si esta aqui es que es un READ

		Token t = rec();
		if (t.codigo != Token.A_PARENTESIS)
			excepcion.addMensaje(Mensaje.ERROR_TOKEN_INCORRECTO,Token.A_PARENTESIS,t);
		else{
			Token lex_de_VARIABLE = new Token();
			VARIABLE(lex_de_VARIABLE);
			if (!tablaDeSimbolos.existeID(lex_de_VARIABLE.lexema))
					
				excepcion.addMensaje(Mensaje.ERROR_N0_EXISTE_ID,Token.ID,t);
			else if (!tablaDeSimbolos.dameModificable(lex_de_VARIABLE.lexema))
				excepcion.addMensaje(Mensaje.ERROR_NO_MODIFICABLE,Token.ID,t);
			emit(new Operaciones(TokenMaquina.LECTURA));
			emit(new Operaciones(TokenMaquina.DESAPILA_DIR));
			emit(new OperandoNum(tablaDeSimbolos.dameDir(lex_de_VARIABLE.lexema)));
			t = rec();
			if (t.codigo != Token.C_PARENTESIS)
				excepcion.addMensaje(Mensaje.ERROR_TOKEN_INCORRECTO, Token.C_PARENTESIS, t);
		}
			
		

		
	}
	private void DECLARACIONES() {
		if (this.tokenSiguiente().codigo == Token.VAR)
			VARIABLES1();
		else
			VARIABLES2();
		if (this.tokenSiguiente().codigo == Token.CONST)
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
		else{
			this.tablaDeSimbolos.anadeID(lex_de_DEC_CONST.lexema, tipo_de_DEC_CONST.codigo, false);
			
			emit(new Operaciones(TokenMaquina.DESAPILA_DIR));
			emit(new OperandoNum(tablaDeSimbolos.dameDir(lex_de_DEC_CONST.lexema)));
		}
		if (this.tokenSiguiente().codigo == Token.PUNTO_Y_COMA)
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
		switch (t.codigo) {
		case Token.NUM :
			emit(new Operaciones(TokenMaquina.APILA));
			emit(new OperandoNum(Integer.valueOf(t.lexema)));
			tipo.codigo = Token.NUM;
			break;
		case Token.NUMREAL : 
			emit(new Operaciones(TokenMaquina.APILA));
			emit(new OperandoNumReal(Double.valueOf(t.lexema)));
			tipo.codigo = Token.REAL;
			break;
		case Token.VALORCHAR :
			emit(new Operaciones(TokenMaquina.APILA));
			emit(new OperandoValorChar(t.lexema.charAt(0)));
			tipo.codigo = Token.CHAR;
			break;
		case Token.VALORBOOLEAN : 
			emit(new Operaciones(TokenMaquina.APILA));
			emit(new OperandoValorBoolean(Boolean.valueOf(t.lexema)));
			tipo.codigo = Token.BOOLEAN;
			break;
		default:
			excepcion.addMensaje(Mensaje.ERROR_EXPRESION_INCORRECTA,0,t);	
		}
	}

	private void emit(Object oper) {
			operaciones.add(oper);
		
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
		else {
			
			this.tablaDeSimbolos.anadeID(lex_de_DEC_CONST.lexema, tipo_de_DEC_CONST.codigo, false);
			emit(new Operaciones(TokenMaquina.DESAPILA_DIR));
			emit(new OperandoNum(tablaDeSimbolos.dameDir(lex_de_DEC_CONST.lexema)));

		}
		if (this.tokenSiguiente().codigo == Token.PUNTO_Y_COMA);
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
		tablaDeSimbolos.anadeID(lex_de_DEC.lexema,tipo_de_DEC.codigo,true);
		if (tokenSiguiente().codigo == Token.PUNTO_Y_COMA)
			RDECS1();
		//RDECS2(); no hace nada no se crea
		
	}


	private void RDECS1() {
		Token t = rec();
		
		if (t.codigo != Token.PUNTO_Y_COMA)
			excepcion.addMensaje(Mensaje.ERROR_FALTA_PUNTO_Y_COMA,Token.PUNTO_Y_COMA,t);
				

		Token tipo_de_DEC = new Token();
		Token lex_de_DEC = new Token();
		DEC(lex_de_DEC,tipo_de_DEC);
		if(tablaDeSimbolos.existeID(lex_de_DEC.lexema))
			excepcion.addMensaje(Mensaje.ERROR_ID_DUPLICADO,Token.ID,lex_de_DEC);
		else
		tablaDeSimbolos.anadeID(lex_de_DEC.lexema, tipo_de_DEC.codigo, true);
	}
	private void DEC(Token lex, Token tipo) {
		
		
		VARIABLE(lex);
		Token t = rec(); //:
		if (t.codigo != Token.DOSPUNTOS)
			excepcion.addMensaje(Mensaje.ERROR_TOKEN_INCORRECTO,Token.DOSPUNTOS,t);
		TIPO(tipo);
		
	}

	private Token tokenSiguiente() {
		return this.tokens.elementAt(this.pos_token);
	}

	private void TIPO(Token tipo) {
		Token t = rec();
		// estan entre los tipos? los tipos estan
		// entre -1000 y -1999
		if ((t.codigo < -1000) && (t.codigo >-1999))
			tipo.codigo = t.codigo;
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
		lex.lexema = t.lexema;
		
		
	}

}
