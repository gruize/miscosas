package excepciones;

public class MensajeLex {
	private String mensaje="";

	public MensajeLex(String i, int numLinea, int columna) {
		// TODO Auto-generated constructor stub
		mensaje="/n"+mensaje+i+"en la linea: "+numLinea+"y columna: "+columna;
	}
	
	public void imprimirMensaje()
	{
		System.out.print(mensaje);
	}

}
