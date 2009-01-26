package interprete;

public class OperandoDirMemoria implements Operandos {

	public Integer dir;
	private int tipo;		
	
	public OperandoDirMemoria(Integer dir, int tipo) {
		super();
		this.dir = dir;
		this.tipo = tipo;
	}

	@Override
	public int dameTipo() {
		return this.tipo;
	}

	@Override
	public Object dameValor() {
		return this.dir;
	}

}
