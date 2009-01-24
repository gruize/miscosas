package interprete;

import java.util.Hashtable;

public class TokenMaquina extends Object{
	
	/** Instruccion de parada */
	public static int STOP = 0;
	
	/** Instrucciones de la pila */
	public static int APILA = 1;
	public static int APILA_DIR = 2;
	public static int DESAPILA_DIR = 3;
	
	/** Instrucciones aritmenticas */
	public static int NEGATIVO = 4;
	public static int SUMA = 5;
	public static int RESTA = 6;
	public static int MULTIPLICACION = 7;
	public static int DIVISION = 8;
	public static int DIV = 9;
	public static int MOD = 10;
	
	/** Instrucciones relacionales */
	public static int MAYOR = 11;
	public static int MENOR = 12;
	public static int IGUAL = 13;
	public static int MAYOR_IGUAL = 14;
	public static int MENOR_IGUAL = 15;
	public static int DISTINTO = 16;
	
	/** Instrucciones lógicas */
	public static int OR = 17;
	public static int AND = 18;
	public static int NOT = 19;
	
	public static final Hashtable <Integer,String>LEXEMAS = new Hashtable<Integer, String>(); 
	
	static
	{
		LEXEMAS.put(STOP, "stop");
		LEXEMAS.put(APILA, "apila");
		LEXEMAS.put(APILA_DIR,"apila_dir");
		LEXEMAS.put(DESAPILA_DIR, "desapila_dir");
		LEXEMAS.put(NEGATIVO, "-");
		LEXEMAS.put(SUMA, "+");
		LEXEMAS.put(RESTA, "-");
		LEXEMAS.put(MULTIPLICACION, "*");
		LEXEMAS.put(DIVISION, "/");
		LEXEMAS.put(DIV, "div");
		LEXEMAS.put(MOD, "mod");
		LEXEMAS.put(MAYOR, ">");
		LEXEMAS.put(MENOR, "<");
		LEXEMAS.put(IGUAL, "=");
		LEXEMAS.put(MAYOR_IGUAL, ">=");
		LEXEMAS.put(MENOR_IGUAL, "<=");
		LEXEMAS.put(DISTINTO, "<>");
		LEXEMAS.put(OR, "||");
		LEXEMAS.put(AND, "&&");
		LEXEMAS.put(NOT,"!");

	}
	
	public String lexema;
	
	public int codigo;
	
	public int linea;
	
	public int columna;

	public String getLexema() {
		return lexema;
	}

	public void setLexema(String lexema) {
		this.lexema = lexema;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getLinea() {
		return linea;
	}

	public void setLinea(int linea) {
		this.linea = linea;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	@Override
	protected Object clone(){
		TokenMaquina clon = new TokenMaquina();
		clon.codigo = clon.getCodigo();
		clon.columna = clon.getColumna();
		clon.lexema = clon.getLexema();
		clon.linea = clon.getLinea();
		return clon;
	}

	public TokenMaquina(String lexema, int codigo, int linea, int columna) {
		this.lexema = lexema.toLowerCase();
		this.codigo = codigo;
		this.linea = linea;
		this.columna = columna;
	}

	public TokenMaquina() {	}
	
}