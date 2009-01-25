package sintactico;
import analizadorLex.Token;

public class CompruebaTipos {

	public static boolean comprueba(int op, int tipo1, int tipo2)
	{
		if(op==Token.OP_ASIGNACION)
		{
			if ((tipo1==tipo2) || ((tipo1==Token.NUM && tipo2==Token.NUMREAL) || (tipo2==Token.NUM && tipo1==Token.NUMREAL)))
				return true;
			else return false;
		}
		else if (op==Token.OP_COMPARACION)
		{
			if ((tipo1==tipo2) || ((tipo1==Token.NUM && tipo2==Token.NUMREAL) || (tipo2==Token.NUM && tipo1==Token.NUMREAL)))
				return true;
			else return false;
		}
		else if(op==Token.OP_DISTINTO)
		{
			if ((tipo1==tipo2) || ((tipo1==Token.NUM && tipo2==Token.NUMREAL) || (tipo2==Token.NUM && tipo1==Token.NUMREAL)))
				return true;
			else return false;
		}
		else if(op==Token.OP_MAYOR_IGUAL)
		{
			if ((tipo1==tipo2) || ((tipo1==Token.NUM && tipo2==Token.NUMREAL) || (tipo2==Token.NUM && tipo1==Token.NUMREAL)))
				return true;
			else return false;
		}
		else if(op==Token.OP_MAYOR_QUE)
		{
			if ((tipo1==tipo2) || ((tipo1==Token.NUM && tipo2==Token.NUMREAL) || (tipo2==Token.NUM && tipo1==Token.NUMREAL)))
				return true;
			else return false;
		}
		else if(op==Token.OP_MENOR_IGUAL)
		{
			if ((tipo1==tipo2) || ((tipo1==Token.NUM && tipo2==Token.NUMREAL) || (tipo2==Token.NUM && tipo1==Token.NUMREAL)))
				return true;
			else return false;
		}
		else if(op==Token.OP_MENOR_QUE)
		{
			if ((tipo1==tipo2) || ((tipo1==Token.NUM && tipo2==Token.NUMREAL) || (tipo2==Token.NUM && tipo1==Token.NUMREAL)))
				return true;
			else return false;
		}
		else if(op==Token.OP_MUL)
		{
			if ((tipo1==Token.NUM && tipo2==Token.NUM)|| (tipo1==Token.NUMREAL && tipo2==Token.NUMREAL)||((tipo1==Token.NUM && tipo2==Token.NUMREAL) || (tipo2==Token.NUM && tipo1==Token.NUMREAL)))
				return true;
			else return false;
		}
		else if(op==Token.OP_RESTA)
		{
			if ((tipo1==Token.NUM && tipo2==Token.NUM)|| (tipo1==Token.NUMREAL && tipo2==Token.NUMREAL)||((tipo1==Token.NUM && tipo2==Token.NUMREAL) || (tipo2==Token.NUM && tipo1==Token.NUMREAL)))
				return true;
			else return false;
		}
		else if(op==Token.DIV)
		{
			if ((tipo1==Token.NUM && tipo2==Token.NUM)|| (tipo1==Token.NUMREAL && tipo2==Token.NUMREAL)||((tipo1==Token.NUM && tipo2==Token.NUMREAL) || (tipo2==Token.NUM && tipo1==Token.NUMREAL)))
				return true;
			else return false;
		}
		else if(op==Token.MOD)
		{
			if ((tipo1==Token.NUM && tipo2==Token.NUM)|| (tipo1==Token.NUMREAL && tipo2==Token.NUMREAL)||((tipo1==Token.NUM && tipo2==Token.NUMREAL) || (tipo2==Token.NUM && tipo1==Token.NUMREAL)))
				return true;
			else return false;
		}
		else if(op==Token.OR)
		{
			if ((tipo1==Token.BOOLEAN) && (tipo2==Token.BOOLEAN))
				return true;
			else return false;
		}
		else if(op==Token.AND)
		{
			if ((tipo1==Token.BOOLEAN) && (tipo2==Token.BOOLEAN))
				return true;
			else return false;
		}
		return false;
	}
}

