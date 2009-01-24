package analizadorLex;

import java.io.IOException;

public class Main {

	/**
	 * 
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException 
	{
		AnalizadorLexico aLex=new AnalizadorLexico (args[0]);
		aLex.run();

	}

}
