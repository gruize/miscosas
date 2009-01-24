package interprete;

/** 
 * @author GabiPC
 * Para que la máquina de ejecución de Pascal puede interpretar una instrucción, esta debe 
 * estar definida. Para cada instrucción debemos definir un código de operación, que se 
 * corresponde con el Token,su nombre y los operandos necesarios para ejecutar dicha 
 * instrucción.
 */
public class Instrucciones {

	/** Nombre de la instrucción */
	private String nombre;

	/** Codigo de la instrucción */
	public int codigo;
	
	/** Numero de lineas */
	public int numeroLineas;
	
	/** Primer operando */
	public int op1 = -1;
	
	/** Segundo operando */
	public int op2;
	
	/** Tercer operando */
	public int op3 = -1;	

	public Instrucciones(String nombre, int codigo, int numeroLineas) {
		this.nombre = nombre;
		this.codigo = codigo;
		this.numeroLineas = numeroLineas;
	}

	public Instrucciones(String nombre, int codigo, int numeroLineas, int op1) {
		this.nombre = nombre;
		this.codigo = codigo;
		this.numeroLineas = numeroLineas;
		this.op1 = op1;
	}

	public Instrucciones(String nombre, int codigo, int numeroLineas, int op1,
			int op2) {
		this.nombre = nombre;
		this.codigo = codigo;
		this.numeroLineas = numeroLineas;
		this.op1 = op1;
		this.op2 = op2;
	}

	public Instrucciones(String nombre, int codigo, int numeroLineas, int op1,
			int op2, int op3) {
		this.nombre = nombre;
		this.codigo = codigo;
		this.numeroLineas = numeroLineas;
		this.op1 = op1;
		this.op2 = op2;
		this.op3 = op3;
	}

	public Instrucciones(String nombre, int codigo, int numeroLineas, int op1,
			int op2, int op3, String etiqueta) {
		this.nombre = nombre;
		this.codigo = codigo;
		this.numeroLineas = numeroLineas;
		this.op1 = op1;
		this.op2 = op2;
		this.op3 = op3;
		this.etiqueta = etiqueta;
	}

	@Override
	public String toString() {
        String n = "000" + this.numeroLineas;
        n = n.substring(n.length()-3);
        String txt = "[" + n + "]" + this.nombre;
        if(this.etiqueta != null && etiqueta.length()>0)
            txt += " " + etiqueta + "(" + op1 + ")";
        else{
            if(op1 >= 0){
            	txt += " " + op1;
            	txt += ", " + op2;
            	if(op3>=0) 
            		txt+= ", " + op3;
            }
        }
        return txt;		
	}
	
	
	
}
