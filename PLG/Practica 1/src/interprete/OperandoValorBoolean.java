package interprete;

import java.io.Serializable;

public class OperandoValorBoolean implements Operandos, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -168514517948142901L;
	public Boolean valor;
	private int tipo = Operandos.VALORBOOLEAN;
	
	public OperandoValorBoolean(Boolean valor) {
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

