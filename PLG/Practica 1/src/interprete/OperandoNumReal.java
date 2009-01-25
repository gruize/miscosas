package interprete;

public class OperandoNumReal implements Operandos{

	public OperandoNumReal(Double valor) {
		super();
		this.valor = valor;
	}

	public Double valor;
	public int tipo = Operandos.NUMREAL;
	
	@Override
	public int dameTipo() {
		return tipo;
	}

	@Override
	public Object dameValor() {
		return this.valor;
	}

	
	
}
