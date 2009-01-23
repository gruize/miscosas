package excepciones;

public class Mensaje {
	public String texto;
	public int linea;
	public Mensaje(String texto, int linea) {
		super();
		this.texto = texto;
		this.linea = linea;
	}
	public String toString(){
		
		return texto;
		
	}
}
