package main;

import interprete.MaquinaEjecucion;

public class mp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String nombre= "a.mp";
		try {
			for (int i = 0; args[i] != null;i++)
			{
				if (args[i].equals("-f"))
				{
					i++;
					nombre = args[i];
				}
				if (nombre == null)
					throw new Exception();
			}
		}
		catch (Exception e){
			if (nombre == null){
			System.out.println("El formato de la maquina de Pacal : ");
			System.out.println("java mp -f [nombre_fichero] ");
			return;
			}
		}
		MaquinaEjecucion me;
		try {
			me = new MaquinaEjecucion(nombre);
		    me.run();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}                       


	}

}
