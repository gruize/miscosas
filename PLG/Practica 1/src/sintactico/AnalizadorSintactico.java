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
import utilidades.Operaciones;
import analizadorLex.AnalizadorLexico;
import analizadorLex.Token;
import excepciones.ExcepcionSintactica;
import excepciones.Mensaje;
/**
 * Esta clase realiza el analisis sintactico 
 * @author Francisco Huertas Ferrer
 * @author Sara Guerrero Garcia
 * @author Gabriela Ruiz Escobar
 * @author Jose Ignacio Perez Solis
 */
public class AnalizadorSintactico {
	/**
	 * Array de tokens que se van a analizar devueltas por
	 * el analizador lexico
	 */
	public Vector<Token> tokens;
	
	/**
	 * Posicion del token actual
	 */
	public int pos_token;
	
	/**
	 * Excecpcion que nos ayuda a tratar los posibles errores de 
	 * ejecucion
	 */
	public ExcepcionSintactica excepcion = new ExcepcionSintactica();
	
	/**
	 * Tabla de simbolos 
	 */
	public TablaSimbolo tablaDeSimbolos= new TablaSimbolo();
	
	/**
	 * Vector con todas las operaciones y operandos que posterioermente se van a escribir 
	 */
	public Vector <Object> operaciones;
	
	/**
	 * Nos indica si la compilacion se ha realizado correctamente
	 */
	public boolean compilacion;
	
	/**
	 * nombre del fichero a generar
	 */
	private String fichero; 
	
	/**
	 * Procedimiento que ejecuta el analizador
	 */
	public void run (){

		
		operaciones = new Vector<Object>();
		pos_token = 0;
		AnalizadorLexico aLex;
		try {
			// LEXICO
			aLex = new AnalizadorLexico(this.fichero);
			aLex.run();
			// Si hay más de un error es que algo ha ido mal
			if (aLex.excepcion.errores.size() > 0)
			{
				excepcion.addMensaje(Mensaje.ERROR_ERRORES_SINTACTICOS,0,new Token());
				aLex.excepcion.printAll();
			}
			tokens = aLex.tokens;
			// SINTACTICO
			this.PROG();
			if (excepcion.errores.size()>0)
			{
				excepcion.printAll();
				compilacion = false;
				operaciones.clear();
			}
			else
				compilacion = true;
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		catch (ExcepcionSintactica e) {

			excepcion.printAll();
			compilacion = false;
			operaciones.clear();
		}

		
	}

	public AnalizadorSintactico(String nameFile){
		this.fichero = nameFile;
		
	}
	/**
	 * Reconoce y consume un token
	 * @return El token
	 * @throws ExcepcionSintactica
	 */
	private Token rec() throws ExcepcionSintactica{
		// SI EL POSTOKEN ESTA COMO EL TAMAÑO ES QUE YA NO HAY MAS TOKENS PARA LEER
		if (this.pos_token == tokens.size())
		{
			excepcion.addMensaje(Mensaje.ERROR_FIN_DE_FICHERO_INCORRECTO,0,tokens.elementAt(pos_token-1));
			throw excepcion;
		}
		Token t = this.tokens.elementAt(this.pos_token);
		this.pos_token++;
		return t; 
	}
	/**
	 * Funcion de generacion de arbol
	 * @throws ExcepcionSintactica
	 */
	private void PROG() throws ExcepcionSintactica{
		CABECERA();
		CUERPO();
		emit(new Operaciones(TokenMaquina.STOP));

		
	}

	/**
	 * Funcion de generacion de arbol
	 * @throws ExcepcionSintactica
	 */
	private void CABECERA () throws ExcepcionSintactica {
		Token t = rec(); // PROGRAM
		if (t.codigo != Token.PROGRAM){
			this.excepcion.addMensaje(Mensaje.ERROR_TOKEN_INCORRECTO,Token.PROGRAM,t);
		}
		t = rec(); // ID
		if (t.codigo != Token.ID){
			this.excepcion.addMensaje(Mensaje.ERROR_TOKEN_INCORRECTO,Token.ID,t);
		}
		t = rec(); // ;
		if (t.codigo != Token.PUNTO_Y_COMA){
			this.excepcion.addMensaje(Mensaje.ERROR_TOKEN_INCORRECTO,Token.PUNTO_Y_COMA,t);
		}
	}

	/**
	 * Funcion de generacion de arbol
	 * @throws ExcepcionSintactica
	 */
	private void CUERPO() throws ExcepcionSintactica {
		DECLARACIONES();
		INSTRUCCIONES();
		
	}
	/**
	 * Funcion de generacion de arbol
	 * @throws ExcepcionSintactica
	 */
	private void DECLARACIONES() throws ExcepcionSintactica {
		if (this.tokenSiguiente().codigo == Token.VAR)
			VARIABLES1();
		else
			VARIABLES2();
		if (this.tokenSiguiente().codigo == Token.CONST)
			CONSTANTES1();
	}


	/**
	 * Funcion de generacion de arbol
	 * @throws ExcepcionSintactica
	 */
	private void VARIABLES1()throws ExcepcionSintactica  {
		rec(); //VAR 
		DECS();
	}
	/**
	 * Funcion de generacion de arbol
	 * @throws ExcepcionSintactica
	 */
	private void DECS() throws ExcepcionSintactica {
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
	/**
	 * Funcion de generacion de arbol
	 * @throws ExcepcionSintactica
	 */

	private void RDECS1()throws ExcepcionSintactica {
		rec(); // ;
		
		// SI EL SIGUIENTE TIPO NO ES UN ID ES UN ERROR
		if (this.tokenSiguiente().codigo != Token.ID)
			excepcion.addMensaje(Mensaje.ERROR_TIPOS,Token.ID,this.tokenSiguiente());
				

		Token tipo_de_DEC = new Token();
		Token lex_de_DEC = new Token();
		DEC(lex_de_DEC,tipo_de_DEC);
		if(tablaDeSimbolos.existeID(lex_de_DEC.lexema))
			excepcion.addMensaje(Mensaje.ERROR_ID_DUPLICADO,Token.ID,lex_de_DEC);
		else
			tablaDeSimbolos.anadeID(lex_de_DEC.lexema, tipo_de_DEC.codigo, true);
		if (tokenSiguiente().codigo == Token.PUNTO_Y_COMA)
			RDECS1();

	}
	private void VARIABLES2() {
		this.tablaDeSimbolos.creaTS();
	}

	/**
	 * Funcion de generacion de arbol
	 * @throws ExcepcionSintactica
	 */
	
	private void CONSTANTES1()throws ExcepcionSintactica  {
		rec();
		// si entra en esta funcino es porque el token es CONST
		
		DECS_CONST();
	}
	/**
	 * Funcion de generacion de arbol
	 * @throws ExcepcionSintactica
	 */

	private void DECS_CONST() throws ExcepcionSintactica {
		Token lex_de_DEC_CONST = new Token();
		Token tipo_de_DEC_CONST = new Token();
		DEC_CONST(lex_de_DEC_CONST,tipo_de_DEC_CONST);
		if (this.tablaDeSimbolos.existeID(lex_de_DEC_CONST.lexema)) // EXISTIA YA?
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
	/**
	 * Funcion de generacion de arbol
	 * @throws ExcepcionSintactica
	 */

	private void RDECS_CONST() throws ExcepcionSintactica {
		rec(); // ;
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
		if (this.tokenSiguiente().codigo == Token.PUNTO_Y_COMA) // SI HAY UN ; NO 
			RDECS_CONST();
		
		
	}
	/**
	 * Funcion de generacion de arbol
	 * @throws ExcepcionSintactica
	 */
	private void DEC_CONST(Token lex, Token tipo) throws ExcepcionSintactica {
		VARIABLE(lex);
		Token t = rec(); //=
		if (t.codigo != Token.OP_COMPARACION)
			excepcion.addMensaje(Mensaje.ERROR_TOKEN_INCORRECTO,Token.OP_COMPARACION,t);
		FACTOR(tipo);
	}
	/**
	 * Funcion de generacion de arbol
	 * @throws ExcepcionSintactica
	 */
	private void INSTRUCCIONES() throws ExcepcionSintactica {
		//Token t = rec();
		BLOQUE();
		Token t = rec();// .
		if (t.codigo != Token.PUNTO) {
			excepcion.addMensaje(Mensaje.ERROR_TOKEN_INCORRECTO,Token.PUNTO,t);
		}
		if (this.pos_token != tokens.size())
			excepcion.addMensaje(Mensaje.ERROR_FIN_PROGRAMA,0,this.tokenSiguiente());
		
		
		
	}
	/**
	 * Funcion de generacion de arbol
	 * @throws ExcepcionSintactica
	 */

	private void BLOQUE() throws ExcepcionSintactica {
		Token t= rec(); // BEGIN
		if (t.codigo != Token.BEGIN) {
			excepcion.addMensaje(Mensaje.ERROR_TOKEN_INCORRECTO,Token.BEGIN,t);
		}
		SENTS();
		t = rec(); // END
		if (t.codigo != Token.END) {
			excepcion.addMensaje(Mensaje.ERROR_TOKEN_INCORRECTO,Token.END,t);
		}
	}
	/**
	 * Funcion de generacion de arbol
	 * @throws ExcepcionSintactica
	 */
	private void SENTS() throws ExcepcionSintactica {
		SENT(); // ;
		if (this.tokenSiguiente().codigo == Token.PUNTO_Y_COMA)
			RSENTS();
	// RSENTS2(); NO ES NECESARIO OPRQUE NO HACE NADA
	}
	/**
	 * Funcion de generacion de arbol
	 * @throws ExcepcionSintactica
	 */
	private void RSENTS() throws ExcepcionSintactica {
		rec(); // ;
		if (this.tokenSiguiente().codigo == Token.END)
			excepcion.addMensaje(Mensaje.ERROR_TIPOS,Token.END,this.tokenSiguiente());

		SENT();
		if (this.tokenSiguiente().codigo == Token.PUNTO_Y_COMA) // SI NO ES ; NO HACE FALTA SEGUIR
			RSENTS();
		
	}
	/**
	 * Funcion de generacion de arbol
	 * @throws ExcepcionSintactica
	 */
	private void SENT() throws ExcepcionSintactica {
		// PARA SABER CUAL SENTS PRODUCIMOS HACEMSO UN SWITCH
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

	/**
	 * Funcion de generacion de arbol
	 * @throws ExcepcionSintactica
	 */
	private void SASIGN() throws ExcepcionSintactica {
		Token lex_de_VARIABLE = new Token();
		VARIABLE(lex_de_VARIABLE);
		Token t = rec(); // :=
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

	/**
	 * Funcion de generacion de arbol
	 * @throws ExcepcionSintactica
	 */
	private void SWRITE() throws ExcepcionSintactica {
		rec(); // WRITE

		Token t = rec(); // (
		if (t.codigo != Token.A_PARENTESIS)
			excepcion.addMensaje(Mensaje.ERROR_TOKEN_INCORRECTO,Token.A_PARENTESIS,t);
		else{
			Token tipo_de_EXP = new Token();
			EXP(tipo_de_EXP);
			emit(new Operaciones(TokenMaquina.ESCRITURA));
			t = rec(); // )
			if (t.codigo != Token.C_PARENTESIS)
				excepcion.addMensaje(Mensaje.ERROR_TOKEN_INCORRECTO, Token.C_PARENTESIS, t);
		}
			
		

	}
	/**
	 * Funcion de generacion de arbol
	 * @throws ExcepcionSintactica
	 */
	private void EXP(Token tipo) throws ExcepcionSintactica {
		Token tipo_de_EXPSIMPLE = new Token();
		EXPSIMPLE(tipo_de_EXPSIMPLE);
		// es un token de operacion?
		/*
		 * NECSITAMOS SABER EL NIVEL DE LA OPERACION, PUEDE OCURRIR QUE
		 * HALLA UNA OPERACION DE UN NIVEL DISTINTO Y TENGAMOS QUE ELEGIR LA SEGUNDA
		 * PRODUCCION DE REXP 
		 */
		if (Operacion.getPrioridad(this.tokenSiguiente(), 2) ==3) 														

			REXP1(tipo_de_EXPSIMPLE,tipo);
		else 
			REXP2(tipo_de_EXPSIMPLE,tipo);
		
	}
	/**
	 * Funcion de generacion de arbol
	 * @throws ExcepcionSintactica
	 */
	private void REXP1(Token tipo_companero, Token tipo) throws ExcepcionSintactica {
		Token t = rec();// OPERACOIN TIPO 3
		Operacion o = new Operacion(t.codigo,2);
		Token tipo_de_EXPSIMPLE = new Token();
		EXPSIMPLE(tipo_de_EXPSIMPLE);
		
		if (!o.compruebaTipos(tipo_companero.codigo, tipo_de_EXPSIMPLE.codigo))
			excepcion.addMensaje(Mensaje.ERROR_TIPOS, t.codigo,tipo_companero);
		else{
			tipo_companero.codigo = o.dameTipo(tipo_companero.codigo, tipo_de_EXPSIMPLE.codigo);
			emit(new Operaciones(o.getCodigoMaquinaDoble()));
		}
		// IGUAL QUE EN EXP
		if (Operacion.getPrioridad(this.tokenSiguiente(), 2) ==3)

			REXP1(tipo_companero,tipo);
		else 
			REXP2(tipo_companero,tipo);

		

		
	}
	/**
	 * Funcion de generacion de arbol
	 * @throws ExcepcionSintactica
	 */

	private void REXP2(Token tipo_companero, Token tipo) throws ExcepcionSintactica {
		tipo.codigo = tipo_companero.codigo;
		
	}

	/**
	 * Funcion de generacion de arbol
	 * @throws ExcepcionSintactica
	 */
	private void EXPSIMPLE(Token tipo) throws ExcepcionSintactica {
		Token tipo_de_TERMINO = new Token();
		TERMINO(tipo_de_TERMINO);
		// IGUAL QUE EN EXP
		if (Operacion.getPrioridad(this.tokenSiguiente(), 2) ==2)
			REXPSIMPLE1(tipo_de_TERMINO,tipo);
		else 
			REXPSIMPLE2(tipo_de_TERMINO,tipo);		
	}
	/**
	 * Funcion de generacion de arbol
	 * @throws ExcepcionSintactica
	 */
	private void REXPSIMPLE1(Token tipo_companero, Token tipo) throws ExcepcionSintactica {
		Token t = rec();// OPERACOIN TIPO 2
		Operacion o = new Operacion(t.codigo,2);
		Token tipo_de_TERMINO = new Token();
		TERMINO(tipo_de_TERMINO);
		
		if (o.compruebaTipos(tipo_companero.codigo, tipo_de_TERMINO.codigo))
			excepcion.addMensaje(Mensaje.ERROR_TIPOS, t.codigo,tipo_companero);
		else{
			
			tipo.codigo = o.dameTipo(tipo_companero.codigo,tipo_de_TERMINO.codigo);
			emit(new Operaciones(o.getCodigoMaquinaDoble()));
		}
		// IGUAL QUE EN EXP
		if (Operacion.getPrioridad(this.tokenSiguiente(), 2) ==2)
			REXPSIMPLE1(tipo_de_TERMINO,tipo);
		else 
			REXPSIMPLE2(tipo_de_TERMINO,tipo);		
	}
	/**
	 * Funcion de generacion de arbol
	 * @throws ExcepcionSintactica
	 */
	private void REXPSIMPLE2(Token tipo_companero, Token tipo) {
		tipo.codigo = tipo_companero.codigo;
		
	}

	/**
	 * Funcion de generacion de arbol
	 * @throws ExcepcionSintactica
	 */
	private void TERMINO(Token tipo) throws ExcepcionSintactica {
		Token tipo_de_FACTOR = new Token();

		FACTOR(tipo_de_FACTOR);
		// IGUAL QUE EN EXP
		
		if (Operacion.getPrioridad(this.tokenSiguiente(), 2) ==1)
			RTERMINO1(tipo_de_FACTOR,tipo);
		else 
			RTERMINO2(tipo_de_FACTOR,tipo);
	
	}

	/**
	 * Funcion de generacion de arbol
	 * @throws ExcepcionSintactica
	 */
	private void RTERMINO1(Token tipo_companero, Token tipo) throws ExcepcionSintactica {
		Token t = rec();// OPERACOIN TIPO 1
		Token tipo_de_FACTOR = new Token();
		Operacion o = new Operacion(t.codigo,2);

		FACTOR(tipo_de_FACTOR);
		
		if (o.compruebaTipos(tipo_companero.codigo, tipo_de_FACTOR.codigo))
			excepcion.addMensaje(Mensaje.ERROR_TIPOS, t.codigo,tipo_companero);
		else{
			
			tipo.codigo = o.dameTipo(tipo_companero.codigo,tipo_de_FACTOR.codigo);
			emit(new Operaciones(o.getCodigoMaquinaDoble()));
		}

		if (Operacion.getPrioridad(this.tokenSiguiente(), 2) ==1)
			RTERMINO1(tipo_de_FACTOR,tipo);
		else 
			RTERMINO2(tipo_de_FACTOR,tipo);

	}
	/**
	 * Funcion de generacion de arbol
	 * @throws ExcepcionSintactica
	 */
	private void RTERMINO2(Token tipo_companero, Token tipo) {
		tipo.codigo = tipo_companero.codigo;
		
		
	}
	
	
	/**
	 * Funcion de generacion de arbol
	 * @throws ExcepcionSintactica
	 */
	private void FACTOR(Token tipo)throws ExcepcionSintactica  {
		
		Operacion oper = null;
		// IGUAL QUE EN EXP
		if (Operacion.getPrioridad(tokenSiguiente(), 1)==0)
			 oper = new Operacion(rec().codigo,1);

		
		COMPONENTE(tipo);
		if (oper != null)
		{
			if (!oper.compruebaTipos(tipo.codigo))
				excepcion.addMensaje(Mensaje.ERROR_TIPOS,0,tipo);
			else
				emit(new Operaciones(oper.getCodigoMaquinaSimple()));
		}
		// NO TIENE ASOCIATIVIDAD, NO MAS ABAJO
	}


	

	
	/**
	 * Funcion de generacion de arbol
	 * @throws ExcepcionSintactica
	 */
	private void COMPONENTE(Token tipo) throws ExcepcionSintactica {
		Token t = rec(); // ( Ó UNA VARIABLE Ó UN VALOR
		//PARA SELECCIONAR LA DIFERENTES PRODUCCIONES LO MIRAMOS AQUI
		// (EXP)
		if (t.codigo == Token.A_PARENTESIS)
		{
			EXP(tipo);
			if ((t = rec()).codigo != Token.C_PARENTESIS)
				excepcion.addMensaje(Mensaje.ERROR_TOKEN_INCORRECTO, Token.C_PARENTESIS, t);
		}
		else
		{

			Operandos o = null;
			// 12 
			if (t.codigo == Token.NUM)
			{
				tipo.codigo = Token.INTEGER;
				o = new OperandoNum(Integer.valueOf(t.lexema));
			}
			// 1.2
			else if (t.codigo == Token.NUMREAL)
			{
				tipo.codigo = Token.REAL;
				o = new OperandoNumReal(Double.valueOf(t.lexema));
			}
			// TRUE | FALSE
			else if (t.codigo == Token.VALORBOOLEAN)
			{
				tipo.codigo = Token.BOOLEAN;
				o = new OperandoValorBoolean(Boolean.valueOf(t.lexema));
				
			}
			// S
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
			// ID
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
					tipo.codigo = tablaDeSimbolos.dameTipo(t.lexema);
				}
			}
		}
	}
	

	
	/**
	 * Funcion de generacion de arbol
	 * @throws ExcepcionSintactica
	 */
	private void SREAD()throws ExcepcionSintactica  {
		rec(); // READ

		Token t = rec(); // (
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
			//emit(new Operaciones(TokenMaquina.DESAPILA_DIR));
			emit(new OperandoNum(tablaDeSimbolos.dameDir(lex_de_VARIABLE.lexema)));
			t = rec(); // )
			if (t.codigo != Token.C_PARENTESIS)
				excepcion.addMensaje(Mensaje.ERROR_TOKEN_INCORRECTO, Token.C_PARENTESIS, t);
		}
	}


	/**
	 * Emite un objeto al vector de operaciones
	 * @param oper
	 */
	private void emit(Object oper) {
			operaciones.add(oper);
		
	}




	




	private void DEC(Token lex, Token tipo) throws ExcepcionSintactica {
		
		
		VARIABLE(lex);
		Token t = rec(); //:
		if (t.codigo != Token.DOSPUNTOS)
			excepcion.addMensaje(Mensaje.ERROR_TOKEN_INCORRECTO,Token.DOSPUNTOS,t);
		TIPO(tipo);
		
	}

	private Token tokenSiguiente() throws ExcepcionSintactica {
		if (this.pos_token == tokens.size())
			throw excepcion;
		return this.tokens.elementAt(this.pos_token);
	}

	private void TIPO(Token tipo) throws ExcepcionSintactica {
		Token t = rec();
		// estan entre los tipos? los tipos estan
		// entre -1000 y -1999
		if ((t.codigo < -1000) && (t.codigo >-1999))
			tipo.copia(t);
		else
			excepcion.addMensaje(Mensaje.ERROR_TIPOS,0,t);
	}

	private void VARIABLE(Token lex) throws ExcepcionSintactica {
		ID(lex);
	}
	private void ID(Token lex)throws ExcepcionSintactica  {
		Token t = rec();
		if (t.codigo != Token.ID){
			// this isn't good word
			excepcion.addMensaje(Mensaje.ERROR_TOKEN_INCORRECTO,Token.ID,t);
			
		}
		lex.copia(t);
		
		
	}

}
