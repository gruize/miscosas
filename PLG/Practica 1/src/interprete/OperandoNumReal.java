package interprete;

public class OperandoNumReal implements Operandos{

	public Double valor;
	private int tipo = Operandos.NUMREAL;
	
	@Override
	public int dameTipo() {
		return tipo;
	}

	@Override
	public Object dameValor() {
		return this.valor;
	}

	
	
}
