package interprete;

public interface Operandos{

	public static int NUM = 0;
	public static int NUMREAL = 1;
	public static int VALORCHAR = 2;
	public static int VALORBOOLEAN = 3;
	
	public abstract int dameTipo();
	public abstract Object dameValor();
	
}
