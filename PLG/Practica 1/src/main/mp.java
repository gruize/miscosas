package main;

import interprete.MaquinaEjecucion;

public class mp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String nombre= "a.mp";
		boolean debug=false;
		try {
			for (int i = 0; i<args.length;i++)
			{
				if (args[i].equals("-f"))
				{
					i++;
					nombre = args[i];
					debug = false;
				}
				else if (args[i].equals("-g"))
				{
					i++;
					nombre = args[i];
					debug = true;
				}
				else if (nombre == null)
						throw new Exception();
			}
		}
		catch (Exception e){
			if (nombre == null){
			System.out.println("El formato de la maquina de Pascal : ");
			System.out.println("java mp -f [nombre_fichero] ");
			System.out.println("java mp -g [nombre_fichero] ");
			return;
			}
		}
		MaquinaEjecucion me;
		try {
			me = new MaquinaEjecucion(nombre);
		    if (debug) me.runDebug();
		    	else me.run();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}                       


	}

}
