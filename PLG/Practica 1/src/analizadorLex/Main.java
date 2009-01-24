package analizadorLex;

import java.io.IOException;

public class Main {

	/**
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception 
	{
		AnalizadorLexico aLex=new AnalizadorLexico (args[0]);
		aLex.run();
		aLex.finish();

	}

}
