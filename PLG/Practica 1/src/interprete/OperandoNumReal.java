package interprete;

import java.io.Serializable;

public class OperandoNumReal implements Operandos, Serializable{

	public double valor;
	private int tipo = Operandos.NUMREAL;
	
	@Override
	public int dameTipo() {
		return tipo;
	}

	@Override
	public Object dameValor() {
		return (double)this.valor;
	}

	
	
}