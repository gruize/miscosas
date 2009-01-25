package interprete;

import java.io.Serializable;


public class OperandoNum implements Operandos, Serializable{

	public int valor;
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
