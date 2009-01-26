package interprete;

import java.io.Serializable;


public class OperandoNum implements Operandos, Serializable{

	public Integer valor;
	private int tipo = Operandos.NUM;
	
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
