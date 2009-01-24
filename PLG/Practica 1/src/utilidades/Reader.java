package utilidades;

import java.io.IOException;

public interface Reader {
	
	public static final Character EOF = null;	
	
	public Character readCharacter()throws IOException;	
	
	public void close();
}
