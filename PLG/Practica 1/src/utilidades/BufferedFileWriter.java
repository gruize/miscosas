package utilidades;

import java.io.*;

public class BufferedFileWriter extends BufferedWriter {
    
    private String nombreFichero = null;
    
   public BufferedFileWriter(String fichero)throws IOException {
        super(new FileWriter(fichero));
        nombreFichero = fichero;
    }
    
    public File getFile(){
        return new File(nombreFichero);
    }
}
