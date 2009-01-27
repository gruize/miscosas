
package main;

import interprete.MaquinaEjecucion;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

import analizadorLex.AnalizadorLexico;
import sintactico.AnalizadorSintactico;
import traductor.traductor;
import utilidades.BufferedFileReader;


public class Main {
    
	public static AnalizadorSintactico as = null;
	public static MaquinaEjecucion me;
	
	

	public static void main(String[] args) throws Exception {
		//String nombre = args[0];
		String nombre= "src/pruebas/Prueba2.pas";
		String salida= "src/interprete/instrucciones.mp";
		try {
			String opcion = args[0];
			for (int i = 0; args[i] != null;i++)
			{
				if (args[i].equals("-f"))
				{
					i++;
					nombre = args[i];
				}
				if (args[i].equals("-o"))
				{
					i++;
					salida = args[i];

				}
				if (nombre == null)
					throw new Exception();
			}
		}
		catch (Exception e){
			if (nombre == null){
			System.out.println("El formato del compilador es el siguiente: ");
			System.out.println("mp -f [nombre_fichero] -o [nombre_salida]");
			System.out.println("mp -f [nombre_fichero] -o [nombre_salida]");
			System.out.println("-o es opcional, si no se escribe la salida se hara sobre el fichero a.mp");
			return;
			}
		}
    	//BufferedFileReader file= new BufferedFileReader(args[0]);
    	
    	//ANALIZADOR
    	as = new AnalizadorSintactico(nombre);
    	as.run();
    	
    	//TRADUCTOR
    	traductor t= new traductor(as);
    	t.crearFileOut();
     
     
        //INTERPRETE      
        me=new MaquinaEjecucion(salida);                       
        me.run();
        
	}
}
