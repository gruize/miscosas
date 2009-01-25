package interprete;

public class OperandoValorBoolean implements Operandos{

	public OperandoValorBoolean(Boolean valor) {
		super();
		this.valor = valor;
	}
	public Boolean valor;
	public int tipo = Operandos.VALORBOOLEAN;
	
	@Override
	public int dameTipo() {
		return this.tipo;
	}
	@Override
	public Object dameValor() {
		return this.valor;
	}
	
	
}
