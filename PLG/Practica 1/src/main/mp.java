package main;

public class mp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String nombre= "src/pruebas/Prueba2.pas";
		String salida= "src/interprete/instrucciones.mp";
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


	}

}