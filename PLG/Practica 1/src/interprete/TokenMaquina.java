package interprete;

import java.util.Hashtable;
import java.util.Vector;

public class TokenMaquina extends Object{
	
	/** Instruccion de parada */
	public static final int STOP = 0;
	
	/** Instrucciones de la pila */
	public static final int APILA = 1;
	public static final int APILA_DIR = 2;
	public static final int DESAPILA_DIR = 3;
	
	/** Instrucciones aritmenticas */
	public static final int NEGATIVO = 4;
	public static final int SUMA = 5;
	public static final int RESTA = 6;
	public static final int MULTIPLICACION = 7;
	public static final int DIVISION = 8;
	public static final int DIV = 9;
	public static final int MOD = 10;
	
	/** Instrucciones relacionales */
	public static final int MAYOR = 11;
	public static final int MENOR = 12;
	public static final int IGUAL = 13;
	public static final int MAYOR_IGUAL = 14;
	public static final int MENOR_IGUAL = 15;
	public static final int DISTINTO = 16;
	
	/** Instrucciones lógicas */
	public static final int OR = 17;
	public static final int AND = 18;
	public static final int NOT = 19;
	
	/** Lectura y escritura */
	public static final int LECTURA = 20;
	public static final int ESCRITURA = 21;
	
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
		LEXEMAS.put(LECTURA, "read");
		LEXEMAS.put(ESCRITURA, "write");

	}
	
	public final static Vector<String> nombreOp = new Vector<String>();
	
	static
	{
		nombreOp.add("STOP");		
		nombreOp.add("APILA");
		nombreOp.add("APILA_DIR");
		nombreOp.add("DESAPILA_DIR");
		nombreOp.add("NEGATIVO");
		nombreOp.add("SUMA");
		nombreOp.add("RESTA");
		nombreOp.add("MULTIPLICACION");
		nombreOp.add("DIVISION");
		nombreOp.add("DIV");
		nombreOp.add("MOD");
		nombreOp.add("MAYOR");
		nombreOp.add("MENOR");
		nombreOp.add("IGUAL");
		nombreOp.add("MAYOR_IGUAL");
		nombreOp.add("MENOR_IGUAL");
		nombreOp.add("DISTINTO");
		nombreOp.add("OR");
		nombreOp.add("AND");
		nombreOp.add("NOT");
		nombreOp.add("LECTURA");
		nombreOp.add("ESCRITURA");
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