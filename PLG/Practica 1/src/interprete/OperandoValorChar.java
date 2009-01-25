package interprete;

public class OperandoValorChar implements Operandos {

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
