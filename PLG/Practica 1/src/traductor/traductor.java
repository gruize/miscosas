package traductor;

import sintactico.AnalizadorSintactico;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Vector;

import utilidades.*;
import sintactico.AnalizadorSintactico;
import sintactico.AnalizadorSintactico;

public class traductor implements Serializable {
	

	private static final long serialVersionUID = -5687060520503269110L;
	private FileOutputStream fileOut;
	//Variable que determina si generará código intermedio o no, en éste caso xa Máquina A no.
    private boolean genCodigo = true;
    private ObjectOutputStream salida;
    private Object objetoSalida;
    private Vector<Object> ArrayOperaciones;
    private File fichero;
    private String nombreFichero;
    
/**
 * Constructor parametrizado 
 * @param as Analizador sintactico que genera el array de instrucciones para traducir a un archivo *.mp
 * @param name nombre archivo salida
 */
public traductor(AnalizadorSintactico as,String name) {
	ArrayOperaciones=as.operaciones;
	this.nombreFichero = name;
	
	}


/**
 * Constructor parametrizado
 * @param o vector de objetos (operaciones u operandos)
 * @throws IOException
 */
public traductor(Vector<Object> o) throws IOException {
	
	//fichero=new File(nombreFichero);
	
	fileOut= new FileOutputStream(nombreFichero);
	
	salida = new ObjectOutputStream(fileOut);
	for(int i=0; i < o.size(); i++)
	{
		objetoSalida=o.elementAt(i);
		salida.writeObject(objetoSalida);
	}
	salida.close();
	
	//return nombreFichero;
}
/**
 *  Devuelve el nombre del archivo
 * @return nombre archivo
 */
public String dameNombre(){
	return nombreFichero;
}
/**
 * Genera escribe y cierra el archivo *.mp
 * @return nombre Fichero salida
 * @throws IOException
 */
public Object crearFileOut() throws IOException{
	
	fileOut = new FileOutputStream(nombreFichero);
	salida = new ObjectOutputStream(fileOut);
	for(int i=0; i<ArrayOperaciones.size(); i++)
	{
		objetoSalida=ArrayOperaciones.elementAt(i);
		salida.writeObject(objetoSalida);
	}
	
	
	salida.close();
	return nombreFichero;
}

}