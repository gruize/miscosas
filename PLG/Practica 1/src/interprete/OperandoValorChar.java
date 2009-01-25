package interprete;

public class OperandoValorChar implements Operandos {

	public char valor;
	private int tipo = Operandos.VALORCHAR;
	
	@Override
	public int dameTipo() {
		return this.tipo;
	}

	@Override
	public Object dameValor() {
		return (char)this.valor;
	}

}
