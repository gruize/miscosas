package interprete;

public class OperandoNum implements Operandos{

	private int valor;
	private int tipo = Operandos.NUM;
	
	@Override
	public int dameTipo() {
		return tipo;
	}

	@Override
	public Object dameValor() {
		return (int)this.valor;
	}

}
