package interprete;

import java.io.Serializable;


public class OperandoNum implements Operandos, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1049057336647199605L;
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
