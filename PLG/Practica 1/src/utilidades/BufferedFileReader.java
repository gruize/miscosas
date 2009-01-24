package utilidades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public final class BufferedFileReader extends BufferedReader  implements Reader{
	
	public BufferedFileReader(String fichero)throws IOException{
		super(new FileReader(fichero));
	}
	
	public Character readCharacter()throws IOException{        
        int c = read();
        if(c==-1) return EOF;
        else return new Character((char)c);
         
    }
	
	public void close(){
		
			try {
				super.close();
			} catch (IOException e) {
			
			}
		
    }
}