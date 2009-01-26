package utilidades;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;

import analizadorLex.Token;

public class PalabrasReservadas {
    public static final Hashtable<String,Integer> PALABRAS_RESERVADAS = new Hashtable<String,Integer>(25);
    static
    {
    	Enumeration<Integer> claves = Token.LEXICOS.keys();
    	Integer i=claves.nextElement();
    	for (;claves.hasMoreElements();){
    		i=claves.nextElement();
    		if (i < 0){
    			PALABRAS_RESERVADAS.put(Token.LEXICOS.get(i).toLowerCase(),i);
    		}
    			
    	}
    	
    }
	
}
