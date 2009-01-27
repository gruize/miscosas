package excepciones;

public class MensajeMaquina {

	public int error;
	public String descripcion;

	/**
	 * Construccion parametrizado
	 * @param error Tipo de error que se genera
	 */
	public MensajeMaquina(int error) {
		super();
		this.error = error;
		switch(this.error){
			case 1:	this.descripcion = "No se contruyo la maquina de ejecucion";
			case 2: this.descripcion = "Problema al ejecutar la operacion";
			case 3: this.descripcion = "No se pudo leer correctamente el objeto desde el fichero";
		}
	}

	/**
	 * Accedente
	 * @return
	 */
	public int getError() {
		return error;
	}

	/** 
	 * Mutador
	 * @param error Modifica el tipo de error
	 */
	public void setError(int error) {
		this.error = error;
	}

	/**
	 * Accedente
	 * @return
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Mutadores
	 * @param descripcion Nueva descripcion del error
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Muestra por pantallla la descripcion del error
	 */
	public void imprimirError(){
		System.out.println("Error: "+this.getError()+" , "+this.getDescripcion());
	}
	
}
