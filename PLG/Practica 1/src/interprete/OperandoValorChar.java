package interprete;

import java.io.Serializable;

public class OperandoValorChar implements Operandos, Serializable {

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
