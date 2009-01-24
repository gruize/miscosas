package analizadorLex;
import java.util.Hashtable;

import excepciones.ExcepcionToken;

public class Token extends Object{
	
	/*PALABRAS RESERVADAS todas con valores negatvos*/
	 //
	public static final int END=-1; //
	public static final int PROGRAM= -2;//
	public static final int VAR=-3;//
	public static final int CONST=-4;//
	public static final int READ=-5;
	public static final int WRITE=-6;
	public static final int NOT= -9;//
	public static final int AND= -10;//
	public static final int OR= -11;//
	public static final int DIV=-12;//
	public static final int MOD=-13;
	public static final int BEGIN= -16;
	public static final int INTEGER=-1001;
	public static final int BOOLEAN=-1002;
	public static final int CHAR=-1003;
	public static final int REAL=-1004;

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
	public static final int OP_RESTA=35;
	public static final int OP_MUL=24;
	//public static final int BARRA_NORMAL/ ::= /
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

	public static final Hashtable <Integer,String>LEXICOS= new Hashtable<Integer, String>(); 
	static
	{
		
		/* PALABRAS RESERVADAS */
		LEXICOS.put(BEGIN, "begin");
		LEXICOS.put(END, "end");
		LEXICOS.put(PROGRAM, "program");
		LEXICOS.put(VAR, "var");
		LEXICOS.put(CONST, "const");
		LEXICOS.put(READ, "read");
		LEXICOS.put(WRITE, "write");
		LEXICOS.put(INTEGER, "integer");
		LEXICOS.put(BOOLEAN, "boolean");
		LEXICOS.put(REAL, "real");
		LEXICOS.put(CHAR, "char");

		
		/* id's y variables*/
		LEXICOS.put(ID, "");
		LEXICOS.put(NUM, "");
		LEXICOS.put(VALORCHAR, "");
		LEXICOS.put(NUMREAL, "");
		LEXICOS.put(VALORBOOLEAN, "");

		
		/*PUNTUACION*/
		LEXICOS.put(PUNTO, ".");
		LEXICOS.put(PUNTO_Y_COMA, ";");
		LEXICOS.put(A_PARENTESIS, "(");
		LEXICOS.put(C_PARENTESIS, ")");
		LEXICOS.put(DOSPUNTOS, ":");

		/* OPERADORES*/
		LEXICOS.put(OP_ASIGNACION, ":=");
		LEXICOS.put(OP_COMPARACION, "=");
		LEXICOS.put(OP_SUMA, "+");
		LEXICOS.put(OP_MUL, "*");
		LEXICOS.put(NOT, "not");
		LEXICOS.put(AND, "and");
		LEXICOS.put(OR, "or");
		LEXICOS.put(DIV, "div");
		LEXICOS.put(MOD, "mod");
		LEXICOS.put(OP_MENOR_QUE, "<");
		LEXICOS.put(OP_MAYOR_QUE, ">");
		LEXICOS.put(OP_DISTINTO, "");
		LEXICOS.put(OP_MENOR_IGUAL, "<=");
		LEXICOS.put(OP_MAYOR_IGUAL, ">=");

		

		/* VALORES E ID'S*/




	};
	
	


	public String lexema;
	public int codigo;
	public int linea;
	public int columna;
	public Token (){
		
	}
	
	public Token clon() {
		Token t = new Token();
		t.codigo = this.codigo;
		t.lexema = this.lexema.toLowerCase();
		t.linea = this.linea;
		return t;
	}
	/*
	 * Constructora con atributo linea
	 * */
	public Token (int codigo,String lexema, int line, int columna){ 
	
		this.codigo = codigo;
		this.lexema = lexema.toLowerCase();
		this.columna = columna;
		this.linea=line;
	}

}	




