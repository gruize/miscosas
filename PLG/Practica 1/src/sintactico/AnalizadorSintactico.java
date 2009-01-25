package sintactico;

import interprete.OperandoNum;
import interprete.OperandoNumReal;
import interprete.OperandoValorBoolean;
import interprete.OperandoValorChar;
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
		if (this.tokenSiguiente() == Token.PUNTO_Y_COMA)
			RSENTS();
		// inecesario
		// else
		// RSENTS2();
	}
	private void RSENTS() {
		rec(); // ;

		SENT();
		if (this.tokenSiguiente() == Token.PUNTO_Y_COMA)
			RSENTS();
		
	}
	private void SENT() {
		
		switch (this.tokenSiguiente()) {
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
		
		Token t = rec();
		Token lex_de_VARIABLE = new Token();
		VARIABLE(lex_de_VARIABLE);
		t = rec();
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
			if (!compruebaTipos(Token.OP_ASIGNACION,
					tablaDeSimbolos.dameTipo(lex_de_VARIABLE.lexema),tipo_de_EXP.codigo)) {
				excepcion.addMensaje(Mensaje.ERROR_TIPOS,Token.ID,t);
				
				
			}
			else {
				emit(new Operaciones(TokenMaquina.DESAPILA_DIR));
				emit(new OperandoNum(tablaDeSimbolos.dameDir(lex_de_VARIABLE.lexema)));
			}
			
			
			
		}
	}
	private boolean compruebaTipos(int op, int dameDir,
			int tipo_de_EXP) {
		// TODO Auto-generated method stub
		return false;
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
		if (esOP3(tokenSiguiente()))
			REXP1(tipo_de_EXPSIMPLE,tipo);
		else 
			REXP2(tipo_de_EXPSIMPLE,tipo);
		
	}
	private void EXPSIMPLE(Token tipo_de_EXPSIMPLE) {
		// TODO Auto-generated method stub
		
	}
	private void REXP2(Token tipo_de_EXPSIMPLE, Token tipo) {
		tipo.codigo = tipo_de_EXPSIMPLE.codigo;
		
	}
	private boolean esOP3(int t) {
		if (t == Token.OP_MAYOR_IGUAL)
			return true;
		if (t == Token.OP_MAYOR_QUE)
			return true;
		if (t == Token.OP_MENOR_IGUAL)
			return true;
		if (t == Token.OP_MAYOR_QUE)
			return true;
		if (t == Token.OP_COMPARACION)
			return true;
		if (t == Token.OP_DISTINTO)
			return true;
		return false;
	}
	private boolean esOP2(int t) {
		if (t == Token.OR)
			return true;
		if (t == Token.OP_SUMA)
			return true;
		if (t == Token.OP_RESTA)
			return true;
		return false;
	}
	
	private boolean esOP1(int t) {
		if (t == Token.OP_MUL)
			return true;
		if (t == Token.DIV)
			return true;
		if (t == Token.MOD)
			return true;
		if (t == Token.OP_DIV)
			return true;
		if (t == Token.AND)
			return true;
		if (t == Token.OP_DISTINTO)
			return true;
		return false;
	}
		
	private void REXP1(Token tipo_companero, Token tipo) {
		Token t = rec();// OPERACOIN
		Token tipo_de_EXPSIMPLE = new Token();
		EXPSIMPLE(tipo_de_EXPSIMPLE);
		
		if (compruebaTipos(t.codigo, tipo_companero.codigo, tipo_de_EXPSIMPLE.codigo))
			excepcion.addMensaje(Mensaje.ERROR_TIPOS, t.codigo,tipo_companero);
		else{
			
			tipo = dameTipo(t.codigo, tipo_companero.codigo,tipo_de_EXPSIMPLE.codigo);
			emit(new Operaciones(DameCodigoMaquinaDoble(t.codigo)));
		}
		// TODO Auto-generated method stub
		
		
	}
	
	private int DameCodigoMaquinaDoble(int codigo) {
		switch (codigo){
			case Token.OP_COMPARACION : 	return TokenMaquina.IGUAL;
			case Token.OP_SUMA:				return TokenMaquina.SUMA;
			case Token.OP_RESTA:			return TokenMaquina.RESTA;
			case Token.OP_MUL:				return TokenMaquina.MULTIPLICACION;
			case Token.OP_DIV:				return TokenMaquina.DIVISION;
			case Token.OP_MENOR_QUE:		return TokenMaquina.MENOR;
			case Token.OP_MAYOR_QUE:		return TokenMaquina.MAYOR;
			case Token.OP_DISTINTO:			return TokenMaquina.DISTINTO;
			case Token.OP_MENOR_IGUAL:		return TokenMaquina.MENOR_IGUAL;
			case Token.OP_MAYOR_IGUAL:		return TokenMaquina.MAYOR_IGUAL;
			case Token.AND:					return TokenMaquina.AND;
			case Token.OR:					return TokenMaquina.OR;
			case Token.DIV:					return TokenMaquina.DIV;
			case Token.MOD:					return TokenMaquina.MOD;


		}
		return 0;
	}
	private int DameCodigoMaquinaSimple(int codigo) {
		switch (codigo){
			case Token.NOT : return TokenMaquina.NOT;
			case Token.OP_RESTA: return TokenMaquina.RESTA;
		}
		return 666;
	}

	private Token dameTipo(int codigo, int codigo2, int codigo3) {
		// TODO Auto-generated method stub
		return null;
	}
	private void REXP2() {
		
		// TODO Auto-generated method stub
		
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
		else{
			this.tablaDeSimbolos.añadeID(lex_de_DEC_CONST.lexema, tipo_de_DEC_CONST.codigo, false);
			
			emit(new Operaciones(TokenMaquina.DESAPILA_DIR));
			emit(new OperandoNum(tablaDeSimbolos.dameDir(lex_de_DEC_CONST.lexema)));
		}
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
			
			this.tablaDeSimbolos.añadeID(lex_de_DEC_CONST.lexema, tipo_de_DEC_CONST.codigo, false);
			emit(new Operaciones(TokenMaquina.DESAPILA_DIR));
			emit(new OperandoNum(tablaDeSimbolos.dameDir(lex_de_DEC_CONST.lexema)));

		}
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
		
		
		VARIABLE(lex);
		Token t = rec(); //:
		if (t.codigo != Token.DOSPUNTOS)
			excepcion.addMensaje(Mensaje.ERROR_TOKEN_INCORRECTO,Token.DOSPUNTOS,t);
		TIPO(tipo);
		
	}

	private int tokenSiguiente() {
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
