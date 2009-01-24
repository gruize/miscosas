
package main;

import interprete.MaquinaEjecucion;

import java.io.IOException;

import analizadorLex.AnalizadorLexico;
import sintactico.AnalizadorSintactico;
import traductor.traductor;
import utilidades.BufferedFileReader;


public class Main {
    
	private static AnalizadorSintactico as = null;
	//private String fichero;
	private static MaquinaEjecucion me;
	
	

	public static void main(String[] args) throws IOException {
    	BufferedFileReader file= new BufferedFileReader(args[0]);
    	as = new AnalizadorSintactico(file);
    	
    	traductor t= new traductor();
        java.io.File f = t.getOutputFile();
        
        //PUEDE SER NULL SI COMPILO SIN GENERACION DE CODIGO
        if(f==null) return;
                    
        //CREO LA MEPA Y EJECUTO EL ARCHIVO            
        me = new MaquinaEjecucion();                        
        me.setArchivoLectura(f.getAbsolutePath());                        
        me.run();            
	}
}
