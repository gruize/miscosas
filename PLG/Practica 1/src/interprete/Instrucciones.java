package interprete;

/** 
 * @author GabiPC
 * Para que la m�quina de ejecuci�n de Pascal puede interpretar una instrucci�n, esta debe 
 * estar definida. Para cada instrucci�n debemos definir un c�digo de operaci�n, que se 
 * corresponde con el Token,su nombre y el operando necesario para ejecutar dicha 
 * instrucci�n.
 */
public class Instrucciones {

	public String nombre;
	public int codigo;
	public int op1;
	
	public Instrucciones(String nombre, int codigo, int numLinea) {
		this.nombre = nombre;
		this.codigo = codigo;
	}
	public Instrucciones(String nombre, int codigo, int numLinea, int op1) {
		this.nombre = nombre;
		this.codigo = codigo;
		this.op1 = op1;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getOp1() {
		return op1;
	}
	public void setOp1(int op1) {
		this.op1 = op1;
	}
}
