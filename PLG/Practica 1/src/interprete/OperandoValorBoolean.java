package interprete;

public class OperandoValorBoolean implements Operandos{

	public Boolean valor;
	private int tipo = Operandos.VALORBOOLEAN;
	
	@Override
	public int dameTipo() {
		return this.tipo;
	}
	@Override
	public Object dameValor() {
		return this.valor;
	}
	
	
}
