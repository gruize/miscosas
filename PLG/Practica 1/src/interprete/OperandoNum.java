package interprete;

public class OperandoNum implements Operandos{

	public Integer valor;
	public int tipo = Operandos.NUM;
	
	public OperandoNum(Integer valor) {
		super();
		this.valor = valor;
	}

	@Override
	public int dameTipo() {
		return tipo;
	}

	@Override
	public Object dameValor() {
		return this.valor;
	}

}
