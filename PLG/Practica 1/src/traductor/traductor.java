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
	
	private FileOutputStream fileOut;
	//Variable que determina si generará código intermedio o no, en éste caso xa Máquina A no.
    private boolean genCodigo = true;
    private ObjectOutputStream salida;
    private Object objetoSalida;
    private Vector<Object> ArrayOperaciones;
    private File fichero;
    private String nombreFichero="src/interprete/instrucciones.mp";
    

public traductor(AnalizadorSintactico as) {
	ArrayOperaciones=as.operaciones;
	
	}

//para porbar el main
/*public traductor(Vector<Object> o) throws IOException {
	
	//fichero=new File(nombreFichero);
	
	fileOut= new FileOutputStream(nombreFichero);
	
	salida = new ObjectOutputStream(fileOut);
	for(int i=0; i<o.size(); i++)
	{
		objetoSalida=o.elementAt(i);
		salida.writeObject(objetoSalida);
	}
	salida.close();
	//return nombreFichero;
}*/

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