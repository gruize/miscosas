package traductor;

import java.io.File;
import java.io.IOException;

import utilidades.*;


public class traductor {
	
	private BufferedFileWriter fileIn = null;
	//Variable que determina si generar� c�digo intermedio o no, en �ste caso apra M�quina A no.
    private boolean genCodigo = true;

public void crearFileIn(String fileIn) throws IOException{
    //Lee datos de la variable devuelta por el analizador sint�ctico y las almacena en un
	//fichero
}


public File getOutputFile(){
    if(fileIn!=null) return fileIn.getFile();
    return null;
}
}