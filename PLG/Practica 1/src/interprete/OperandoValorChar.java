package interprete;

import java.io.Serializable;

public class OperandoValorChar implements Operandos, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3291903449547182775L;
	public Character valor;
	private int tipo = Operandos.VALORCHAR;
	
	public OperandoValorChar(Character valor) {
		super();
		this.valor = valor;
	}

	@Override
	public int dameTipo() {
		return this.tipo;
	}

	@Override
	public Object dameValor() {
		return this.valor;
	}

}
