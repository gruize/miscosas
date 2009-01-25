package interprete;

public class OperandoValorChar implements Operandos {

	public OperandoValorChar(Character valor) {
		super();
		this.valor = valor;
	}

	public Character valor;
	private int tipo = Operandos.VALORCHAR;
	
	@Override
	public int dameTipo() {
		return this.tipo;
	}

	@Override
	public Object dameValor() {
		return this.valor;
	}

}
