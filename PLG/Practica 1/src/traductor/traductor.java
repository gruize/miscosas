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
import java.util.Vector;

import utilidades.*;
import sintactico.AnalizadorSintactico;
import sintactico.AnalizadorSintactico;

public class traductor {
	
	private FileOutputStream fileOut = null;
	//Variable que determina si generará código intermedio o no, en éste caso xa Máquina A no.
    private boolean genCodigo = true;
    private ObjectOutputStream salida;
    private Operaciones objetoSalida;
    private Vector<Operaciones> ArrayOperaciones;
    

public traductor(AnalizadorSintactico as) {
	ArrayOperaciones=as.operaciones;
	}

public FileOutputStream crearFileOut() throws IOException{
	salida= new ObjectOutputStream(fileOut);
	for(int i=0; i<ArrayOperaciones.size(); i++)
	{
		objetoSalida=ArrayOperaciones.elementAt(i);
		salida.writeObject(objetoSalida);
	}
	return fileOut;
}

public FileOutputStream giveFile()
{
	return fileOut;
}

}