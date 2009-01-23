package analizadorLex;

import java.io.*;
import utilidades.*;

public class AnalizadorLexico {
	
	private int numLinea;
	private Character ultimoCharLeido;
	private Reader reader = null;
	
	
	
	  
	  
	public AnalizadorLexico() {
		numLinea=1;
	
	}
    private Character leerCaracter()throws IOException {
        if(reader==null) throw new IOException("Reader no inizializado");
        
        ultimoCharLeido = reader.readCharacter();
        if(esFinalDeLinea(ultimoCharLeido)) {
            numLinea++; //incrementa la linea
        }
        return ultimoCharLeido;
    }




	public Token[] getTokens(){
		
		return null;
	}

}
