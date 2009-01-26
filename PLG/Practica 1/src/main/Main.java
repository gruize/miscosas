
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
		//String nombre = args[0];
		String nombre= "src\\pruebas\\PruebaErronea1.pas";
    	//BufferedFileReader file= new BufferedFileReader(args[0]);
    	
    	//ANALIZADOR
    	as = new AnalizadorSintactico(nombre);
    	as.run();
    	
    	//TRADUCTOR
    	traductor t= new traductor(as);
    	t.crearFileOut();
     
     
        //INTERPRETE      
        me=new MaquinaEjecucion(nombre+".obj");                       
        me.run();
        
	}
}
