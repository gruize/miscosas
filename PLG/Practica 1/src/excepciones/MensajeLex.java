package excepciones;

public class MensajeLex {
	
	String mensajeE;
	int fila ;
	int columna;
	

	public MensajeLex(String i, int numLinea, int col) {
		
		mensajeE=i;
		fila=numLinea;
		columna=col;
	}
	
	public String toString(){
		
		return this.mensajeE + "en la linea " +this.fila+ " columna "+this.columna;
	}

}
