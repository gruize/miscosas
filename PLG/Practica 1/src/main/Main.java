
package main;

import interprete.MaquinaEjecucion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import analizadorLex.AnalizadorLexico;
import sintactico.AnalizadorSintactico;
import traductor.traductor;
import utilidades.BufferedFileReader;


public class Main {
    
	public static AnalizadorSintactico as = null;
	public static MaquinaEjecucion me;
	
	

	public static void main(String[] args) throws Exception {
		
    	BufferedFileReader file= new BufferedFileReader(args[0]);
    	
    	//ANALIZADOR
    	as = new AnalizadorSintactico(file);
    	as.run();
    	
    	//TRADUCTOR
    	traductor t= new traductor(as);
    	String nombre= (String) t.crearFileOut();
     
     
        //INTERPRETE      
        me=new MaquinaEjecucion(nombre);                       
        me.run();
        
	}
}
