package interprete;

public class OperandoNum implements Operandos{

	public Integer valor;
	private int tipo = Operandos.NUM;
	
	@Override
	public int dameTipo() {
		return tipo;
	}

	@Override
	public Object dameValor() {
		return this.valor;
	}

}
