package interprete;

public class OperandosValorBoolean implements Operandos{

	public boolean valor;
	private int tipo = Operandos.VALORBOOLEAN;
	
	@Override
	public int dameTipo() {
		return this.tipo;
	}
	@Override
	public Object dameValor() {
		return (boolean)this.valor;
	}
	
	
}
