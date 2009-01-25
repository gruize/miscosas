package interprete;

import java.io.Serializable;

public class OperandoValorBoolean implements Operandos, Serializable{

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

