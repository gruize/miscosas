package interprete;

import java.io.Serializable;

public class OperandoNumReal implements Operandos, Serializable{

	public Double valor;
	private int tipo = Operandos.NUMREAL;
	
	public OperandoNumReal(Double valor) {
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