/**
 * 
 */
package interprete;

import java.util.*;
/**
 * @author GabiPC
 *
 */
public class TokenMaquina {

	/** Identificador del token */
	public int codigo;	
	/** Lexema del token */
	public String lexema;
	/** Número de líneas en las que interviene dicho token */
	public int numeroLineas;
	/** Identificador y lexema de cada token */
	public static Hashtable<Integer,String> lexemas = new Hashtable<Integer,String>();
	
	/** Fin del archivo */
	public static final int EOF = 0;
	
	/** Apilar */
	/** Apilar el valor de una variable */
	public static final int APILAVAR = 1;
	/** Apilar el valor de una constante */
	public static final int APILACONST = 2;
	
	/** Operadores logicos */
	/** NOT */
	public static final int NEGACION = 3;
	/** AND */
	public static final int CONJUNCION = 4;
	/** OR */
	public static final int DISYUNCION = 5;
	
	/** Operadores relacionales */
	/** Mayor que > */
	public static final int MAYORQUE = 6;
	/** Menor que < */
	public static final int MENORQUE = 7;
	/** Mayor o igual que >= */
	public static final int MAYORIGUALQUE = 8;
	/** Menor o igual que <= */
	public static final int MENORIGUALQUE = 9;
	/** Distinto que */
	public static final int DISTINTOQUE = 10;
	/** Igual que */
	public static final int IGUALQUE = 11;
	
	/** Operadores aritmeticos */
	/** Menos unario */
	public static final int MENOSUNARIO = 12;
	/** Multiplicación */
	public static final int MULT = 13;
	/** Division */
	public static final int DIV = 14;
	/** Division entera */
	public static final int DIVENTERA = 15;
	/** Modulo */
	public static final int MOD = 16;
	/** Suma */
	public static final int SUMA = 17;
	/** Resta */
	public static final int RESTA = 18;
	
	
	static{
		lexemas.put(EOF, "EOF");
		lexemas.put(APILAVAR, "APILAVAR");
		lexemas.put(APILACONST, "APILACONST");
		lexemas.put(NEGACION, "NEGACION");
		lexemas.put(CONJUNCION, "CONJUNCION");
		lexemas.put(DISYUNCION, "DISYUNCION");
		lexemas.put(MAYORQUE, "MAYORQUE");
		lexemas.put(MENORQUE, "MENORQUE");
		lexemas.put(MAYORIGUALQUE, "MAYORIGUALQUE");
		lexemas.put(MENORIGUALQUE, "MENORIGUALQUE");
		lexemas.put(DISTINTOQUE, "DISTINTOQUE");
		lexemas.put(IGUALQUE, "IGUALQUE");
		lexemas.put(MENOSUNARIO, "MENOSUNARIO");
		lexemas.put(MULT, "MULT");
		lexemas.put(DIV, "DIV");
		lexemas.put(DIVENTERA, "DIVENTERA");
		lexemas.put(MOD, "MOD");
		lexemas.put(SUMA, "SUMA");
		lexemas.put(RESTA, "RESTA");
	}
	
	public TokenMaquina(int codigoToken, int numLine){
		this.codigo = codigoToken;
		this.lexema = lexemas.get(codigoToken);
		if(this.lexema != null)
			this.lexema = "";
		this.numeroLineas = numLine;
	}

	public TokenMaquina(int codigo, String lexema, int numeroLineas) {
		this.codigo = codigo;
		this.lexema = lexema;
		this.numeroLineas = numeroLineas;
	}
	
	public static Integer getCodigoInstruccion(String instruccion){
		Integer codigo = null;
		for(int i = 0; i < lexemas.size(); i++)
			if(lexemas.get(i) == instruccion)
				codigo = i;
		return codigo;
	}
	
	public boolean instruccionValida(){
		return (getCodigoInstruccion(this.lexema.toUpperCase()) != null);
	}
	
    /** 
     * @return la representación del token como string
     */
    public String toString(){
        String n="000"+ this.numeroLineas;
        n = n.substring(n.length()-3);
        return "[linea " + n + ", " + lexema + ", " + lexemas.get(codigo) + "]";
    }
    
    
/**
	//para case (duplicar tope)
    public static final int DUPT = 16;
//saltos
    public static final int DSVS = 17;
    public static final int DSVF = 18;
    
//entrada - salida
    public static final int LEER = 19;
    public static final int IMPR = 20;
    public static final int LELN = 21;
    public static final int IMLN = 22;
    
//variables
    public static final int APVL = 23;
    public static final int ALVL = 24;
    
//programas y procedimientos
    public static final int INPP = 25;
    public static final int ENPR = 26;
    public static final int LLPR = 27;
    public static final int RMEM = 28;
    public static final int LMEM = 29;
    public static final int RTPR = 30;
    
//pasaje de param por ref
    public static final int APDR = 31;
    public static final int APVI = 32;
    public static final int ALVI = 33;
    
//Arreglos
    public static final int APAR = 34;
    public static final int ALAR = 35;
    public static final int PUAR = 36;
    public static final int POAR = 37;
//Arreglos por ref
    public static final int APAI = 38;
    public static final int ALAI = 39;
    public static final int PUAI = 40;
    public static final int POAI = 41;
    public static final int APDC = 42;
//Arreglos: control de indice inferior y superior
    public static final int CONT = 43;
    
//Otras
    public static final int DIVC = 44;
    public static final int NADA = 45;
    public static final int PARA = 46;

    //Instrucciones nuevas:
    public static final int DSVV = 47;
    public static final int ALDR = 48;
    public static final int IMLV = 49;
    
    public static final int NUMERO = 50;
    public static final int MENOS = 51;
    public static final int ETIQ = 52;
    public static final int COMA = 53;	
	*/
}
