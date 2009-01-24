package traductor;

import java.io.File;
import java.io.IOException;

import utilidades.*;


public class traductor {
	
	private BufferedFileWriter fileIn = null;
	//Variable que determina si generará código intermedio o no, en éste caso apra Máquina A no.
    private boolean genCodigo = true;

public void crearFileIn(String fileIn) throws IOException{
    //Lee datos de la variable devuelta por el analizador sintáctico y las almacena en un
	//fichero
}


public File getOutputFile(){
    if(fileIn!=null) return fileIn.getFile();
    return null;
}
}