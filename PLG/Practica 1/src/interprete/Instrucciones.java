package interprete;

/** 
 * @author GabiPC
 * Para que la máquina de ejecución de Pascal puede interpretar una instrucción, esta debe 
 * estar definida. Para cada instrucción debemos definir un código de operación, que se 
 * corresponde con el Token,su nombre y el operando necesario para ejecutar dicha 
 * instrucción.
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
