package analizadorLex;
import java.util.Hashtable;

import excepciones.excepcionLexica;



public class Token {
	
	/*PALABRAS RESERVADAS*/
	public static final int BEGIN= 1; 
	public static final int END=1; 
	public static final int PROGRAM= 2;
	public static final int VAR=3;
	public static final int CONS=4;
	public static final int READ=5;
	public static final int WRITE=6;
	public static final int INTEGER=7;
	public static final int BOOLEAN=8;
	public static final int NOT= 9;
	public static final int AND= 10;
	public static final int OR= 11;
	public static final int DIV=12;
	public static final int MOD=13;
	public static final int REAL=14;
	public static final int CHAR=15;

	// VALORES E ID'S
	public static final int ID=16;
	public static final int NUM=17;
	public static final int VALORCHAR=18;
	public static final int NUMREAL=19;
	public static final int VALORBOOLEAN=20;
	

	//OPERADORES
	public static final int OP_ASIGNACION=21;
	public static final int OP_COMPARACION=22;
	public static final int OP_SUMA=23;
	public static final int OP_MUL=24;
	//public static final int BARRA_NORMAL/ ::= “/”
	public static final int OP_MENOR_QUE=25;
	public static final int OP_MAYOR_QUE=26;
	public static final int OP_DISTINTO=27;	
	public static final int OP_MENOR_IGUAL=28;	
	public static final int OP_MAYOR_IGUAL=29;
	//PUNTUACION
	public static final int PUNTO=30;	
	public static final int PUNTO_Y_COMA=31;
	public static final int A_PARENTESIS=32;
	public static final int C_PARENTESIS=33;
	public static final int DOSPUNTOS=34;

	public static final Hashtable <Integer,String>TOKENS= new Hashtable<Integer, String>(); 
	static
	{
		/* PALABRAS RESERVADAS */
		TOKENS.put(BEGIN, "begin");
		TOKENS.put(END, "end");
		TOKENS.put(PROGRAM, "program");
		TOKENS.put(VAR, "var");
		TOKENS.put(CONS, "cons");
		TOKENS.put(READ, "read");
		TOKENS.put(WRITE, "write");
		TOKENS.put(INTEGER, "integer");
		TOKENS.put(BOOLEAN, "boolean");
		TOKENS.put(REAL, "real");
		TOKENS.put(CHAR, "char");

		
		/* id's y variables*/
		TOKENS.put(ID, null);
		TOKENS.put(NUM, null);
		TOKENS.put(VALORCHAR, null);
		TOKENS.put(NUMREAL, null);
		TOKENS.put(VALORBOOLEAN, null);

		
		/*PUNTUACION*/
		TOKENS.put(PUNTO, ".");
		TOKENS.put(PUNTO_Y_COMA, ";");
		TOKENS.put(A_PARENTESIS, "(");
		TOKENS.put(C_PARENTESIS, ")");
		TOKENS.put(DOSPUNTOS, ":");

		/* OPERADORES*/
		TOKENS.put(OP_ASIGNACION, ":=");
		TOKENS.put(OP_COMPARACION, "=");
		TOKENS.put(OP_SUMA, "+");
		TOKENS.put(OP_MUL, "*");
		TOKENS.put(NOT, "not");
		TOKENS.put(AND, "and");
		TOKENS.put(OR, "or");
		TOKENS.put(DIV, "div");
		TOKENS.put(MOD, "mod");
		TOKENS.put(OP_MENOR_QUE, "<");
		TOKENS.put(OP_MAYOR_QUE, ">");
		TOKENS.put(OP_DISTINTO, "");
		TOKENS.put(OP_MENOR_IGUAL, "<=");
		TOKENS.put(OP_MAYOR_IGUAL, ">=");

		

		/* VALORES E ID'S*/




	};


	public String lexema;
	public int codigo;
	public Token (int codigo) throws excepcionLexica{
		this.codigo=codigo;
		this.lexema = TOKENS.get(codigo);
		if (this.lexema == null)
			throw new excepcionLexica();
		
	}
	public Token (int codigo,String lexema) throws excepcionLexica{
		this.codigo = codigo;
		if (TOKENS.get(codigo) != null)
			throw new excepcionLexica();
		this.lexema = lexema;
	}
	



}
