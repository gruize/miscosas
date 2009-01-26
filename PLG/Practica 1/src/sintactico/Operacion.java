package sintactico;

import interprete.TokenMaquina;
import analizadorLex.Token;

public class Operacion {
	/**
	 * Las prioridades van de + prioritarias 0 a menos prioritaria 3
	 */

	public int codigo;
	
	static final public int OP_ASIGNACION = 0;
	static final public int OP_COMPARACION= 1;
	static final public int OP_DISTINTO= 2;
	static final public int OP_MAYOR_IGUAL=3; 
	static final public int OP_MAYOR_QUE=4;
	static final public int OP_MENOR_IGUAL=5; 
	static final public int OP_MENOR_QUE=6;
	static final public int OP_MUL=7;
	static final public int OP_RESTA=8;
	static final public int OP_SUMA=9;
	static final public int DIV=10;
	static final public int MOD=11;
	static final public int OR=12;
	static final public int AND=13;
	static final public int OP_DIV = 14;
	static final public int NEGACION=15;
	static final public int NOT=16;

	public Operacion(int codigo) {
		super();
		this.codigo = codigo;
	}	
	public Operacion(int codigoToken,int aridad) {
		super();
		this.codigoFromToken(codigoToken, aridad);
	}
	public int getCodigoMaquinaDoble() {
		switch (codigo){
			case OP_COMPARACION : 		return TokenMaquina.IGUAL;
			case OP_SUMA:				return TokenMaquina.SUMA;
			case OP_RESTA:				return TokenMaquina.RESTA;
			case OP_MUL:				return TokenMaquina.MULTIPLICACION;
			case OP_DIV:				return TokenMaquina.DIVISION;
			case OP_MENOR_QUE:			return TokenMaquina.MENOR;
			case OP_MAYOR_QUE:			return TokenMaquina.MAYOR;
			case OP_DISTINTO:			return TokenMaquina.DISTINTO;
			case OP_MENOR_IGUAL:		return TokenMaquina.MENOR_IGUAL;
			case OP_MAYOR_IGUAL:		return TokenMaquina.MAYOR_IGUAL;
			case AND:					return TokenMaquina.AND;
			case OR:					return TokenMaquina.OR;
			case DIV:					return TokenMaquina.DIV;
			case MOD:					return TokenMaquina.MOD;


		}
		return -1;
	}
	public int DameCodigoMaquinaSimple(int codigo) {
		switch (codigo){
			case NOT : return TokenMaquina.NOT;
			case OP_RESTA: return TokenMaquina.RESTA;
		}
		return 666;
	}
	
	public int getCodigoToken ()
	{
		switch (codigo){

			case OP_COMPARACION : 		return Token.OP_COMPARACION;
			case OP_SUMA:				return Token.OP_SUMA;
			case OP_RESTA:				return Token.OP_RESTA;
			case OP_MUL:				return Token.OP_MUL;
			case OP_DIV:				return Token.OP_DIV;
			case OP_MENOR_QUE:			return Token.OP_MENOR_QUE;
			case OP_MAYOR_QUE:			return Token.OP_MAYOR_QUE;
			case OP_DISTINTO:			return Token.OP_DISTINTO;
			case OP_MENOR_IGUAL:		return Token.OP_MENOR_IGUAL;
			case OP_MAYOR_IGUAL:		return Token.OP_MAYOR_IGUAL;
			case AND:					return Token.AND;
			case OR:					return Token.AND;
			case DIV:					return Token.DIV;
			case MOD:					return Token.MOD;
			case NOT : 					return Token.NOT;
			case NEGACION :				return Token.OP_RESTA;

		}
		return -1;
	}
	public int getAridad(){
		switch (codigo){

			case OP_COMPARACION : 		return 2;
			case OP_SUMA:				return 2;
			case OP_RESTA:				return 2;
			case OP_MUL:				return 2;
			case OP_DIV:				return 2;
			case OP_MENOR_QUE:			return 2;
			case OP_MAYOR_QUE:			return 2;
			case OP_DISTINTO:			return 2;
			case OP_MENOR_IGUAL:		return 2;
			case OP_MAYOR_IGUAL:		return 2;
			case AND:					return 2;
			case OR:					return 2;
			case DIV:					return 2;
			case MOD:					return 2;
			case NOT : 					return 1;
			case NEGACION :				return 1;
		}

		return -1;
	}
	static public int getPrioridad(Token token, int aridad)
	{
		switch (token.codigo){
		
		case Token.OP_MENOR_QUE		: 	return 3;			
		case Token.OP_MAYOR_QUE		:	return 3;
		case Token.OP_DISTINTO		:	return 3;		 
		case Token.OP_MENOR_IGUAL	:	return 3;		 
		case Token.OP_COMPARACION	: 	return 3;
		case Token.OP_MAYOR_IGUAL	:	return 3;		 
		case Token.OP_SUMA			: 	return 2;				
		case Token.OR				:	return 2;
		case Token.OP_DIV			:	return 1;				 
		case Token.DIV				:	return 1;
		case Token.MOD				:	return 1;
		case Token.AND				:	return 1;					
		case Token.OP_MUL			:	return 1;	 
		case Token.NOT				:	return 0;
		case Token.OP_RESTA			:
			if (aridad == 1)
				return 0;
			else if (aridad == 2)
				return 2;
		}
		return -1;
	}

	public int getPrioridad()
	{
		switch (codigo){
		
			case OP_COMPARACION : 		return 3;
			case OP_MENOR_QUE:			return 3;
			case OP_MAYOR_QUE:			return 3;
			case OP_DISTINTO:			return 3;
			case OP_MENOR_IGUAL:		return 3;
			case OP_MAYOR_IGUAL:		return 3;
			case OP_SUMA:				return 2;
			case OP_RESTA:				return 2;
			case OR:					return 2;
			case OP_MUL:				return 1;
			case OP_DIV:				return 1;
			case AND:					return 1;
			case DIV:					return 1;
			case MOD:					return 1;
			case NOT : 					return 0;
			case NEGACION :				return 0;
		}
		return -1;
	}
	public void codigoFromToken(int codigo, int aridad)
	{
		switch (codigo){

			case Token.OP_COMPARACION	: 	codigo = OP_COMPARACION;
											break;
			case Token.OP_SUMA			: 	codigo = OP_SUMA;				
											break;
			case Token.OP_MUL			:	codigo = OP_MUL;		 
											break;
			case Token.OP_DIV			:	codigo = OP_DIV;				 
											break;
			case Token.OP_MENOR_QUE		: 	codigo = OP_MENOR_QUE;			
											break;
			case Token.OP_MAYOR_QUE		:	codigo = OP_MAYOR_QUE;
											break;
			case Token.OP_DISTINTO		:	codigo = OP_DISTINTO;			 
											break;
			case Token.OP_MENOR_IGUAL	:	codigo = OP_MENOR_IGUAL;		 
											break;
			case Token.OP_MAYOR_IGUAL	:	codigo = OP_MAYOR_IGUAL;		 
											break;
			case Token.AND				:	codigo = AND;					
											break;
			case Token.OR				:	codigo = OR;
											break;
			case Token.DIV				:	codigo = DIV;
											break;
			case Token.MOD				:	codigo = MOD;
											break;
			case Token.NOT				:	codigo = NOT;
				break;
			case Token.OP_RESTA			:
				if (aridad == 1)
					codigo = NEGACION;
				else if (aridad == 2)
					codigo = OP_RESTA;
				break;
			default:
			codigo = -1;
		}
		
			
	
	}
	public void codigoFromToken(Token token, int aridad) {
		codigoFromToken(token.codigo, aridad);
	}
	public static boolean tiposAsignacion(int tipo1,int tipo2){
		if ((tipo1==tipo2) || ((tipo1==Token.NUM && tipo2==Token.NUMREAL) || (tipo2==Token.NUM && tipo1==Token.NUMREAL)))
			return true;
		else return false;
		
	}
	public boolean compruebaTipos(int tipo1,int tipo2){
		if (codigo ==OP_COMPARACION)
		{
			if ((tipo1==tipo2) || ((tipo1==Token.NUM && tipo2==Token.NUMREAL) || (tipo2==Token.NUM && tipo1==Token.NUMREAL)))
				return true;
			else return false;
		}
		else if(codigo==OP_DISTINTO)
		{
			if ((tipo1==tipo2) || ((tipo1==Token.NUM && tipo2==Token.NUMREAL) || (tipo2==Token.NUM && tipo1==Token.NUMREAL)))
				return true;
			else return false;
		}
		else if(codigo==OP_MAYOR_IGUAL)
		{
			if ((tipo1==tipo2) || ((tipo1==Token.NUM && tipo2==Token.NUMREAL) || (tipo2==Token.NUM && tipo1==Token.NUMREAL)))
				return true;
			else return false;
		}
		else if(codigo==OP_MAYOR_QUE)
		{
			if ((tipo1==tipo2) || ((tipo1==Token.NUM && tipo2==Token.NUMREAL) || (tipo2==Token.NUM && tipo1==Token.NUMREAL)))
				return true;
			else return false;
		}
		else if(codigo==OP_MENOR_IGUAL)
		{
			if ((tipo1==tipo2) || ((tipo1==Token.NUM && tipo2==Token.NUMREAL) || (tipo2==Token.NUM && tipo1==Token.NUMREAL)))
				return true;
			else return false;
		}
		else if(codigo==OP_MENOR_QUE)
		{
			if ((tipo1==tipo2) || ((tipo1==Token.NUM && tipo2==Token.NUMREAL) || (tipo2==Token.NUM && tipo1==Token.NUMREAL)))
				return true;
			else return false;
		}
		else if(codigo==OP_MUL)
		{
			if ((tipo1==Token.NUM && tipo2==Token.NUM)|| (tipo1==Token.NUMREAL && tipo2==Token.NUMREAL)||((tipo1==Token.NUM && tipo2==Token.NUMREAL) || (tipo2==Token.NUM && tipo1==Token.NUMREAL)))
				return true;
			else return false;
		}
		else if(codigo==OP_RESTA)
		{
			if ((tipo1==Token.NUM && tipo2==Token.NUM)|| (tipo1==Token.NUMREAL && tipo2==Token.NUMREAL)||((tipo1==Token.NUM && tipo2==Token.NUMREAL) || (tipo2==Token.NUM && tipo1==Token.NUMREAL)))
				return true;
			else return false;
		}
		else if(codigo==DIV)
		{
			if ((tipo1==Token.NUM && tipo2==Token.NUM)|| (tipo1==Token.NUMREAL && tipo2==Token.NUMREAL)||((tipo1==Token.NUM && tipo2==Token.NUMREAL) || (tipo2==Token.NUM && tipo1==Token.NUMREAL)))
				return true;
			else return false;
		}
		else if(codigo==MOD)
		{
			if ((tipo1==Token.NUM && tipo2==Token.NUM)|| (tipo1==Token.NUMREAL && tipo2==Token.NUMREAL)||((tipo1==Token.NUM && tipo2==Token.NUMREAL) || (tipo2==Token.NUM && tipo1==Token.NUMREAL)))
				return true;
			else return false;
		}
		else if(codigo==OR)
		{
			if ((tipo1==Token.BOOLEAN) && (tipo2==Token.BOOLEAN))
				return true;
			else return false;
		}
		else if(codigo==AND)
		{
			if ((tipo1==Token.BOOLEAN) && (tipo2==Token.BOOLEAN))
				return true;
			else return false;
		}
		return false;
	}
	public boolean compruebaTipos(int tipo1){
		if(codigo ==NOT)
		{
			if (tipo1==Token.BOOLEAN)
				return true;
			else return false;
		}
		else if (codigo ==NEGACION)
		{
			if ((tipo1==Token.NUM) || (tipo1==Token.NUMREAL))
				return true;
			else return false;
		}
		return false;
	
	}
	public int dameTipo (int tipo1, int tipo2){
		if (codigo == Operacion.DIV)
			return Token.INTEGER;
		if (tipo1 == tipo2)
			return tipo1;
		if (tipo1 == Token.INTEGER && tipo2 == Token.REAL)
			return Token.REAL;
		if (tipo2 == Token.INTEGER && tipo1 == Token.REAL)
			return Token.REAL;
		return -1;
	}
	public int dameTipo(int tipo){
		return tipo;
	}
}

/*
	public int getAridad(){
		switch (codigo){
	
			case OP_COMPARACION : 		return 
			case OP_SUMA:				return 
			case OP_RESTA:				return 
			case OP_MUL:				return 
			case OP_DIV:				return 
			case OP_MENOR_QUE:			return 
			case OP_MAYOR_QUE:			return 
			case OP_DISTINTO:			return 
			case OP_MENOR_IGUAL:		return 
			case OP_MAYOR_IGUAL:		return 
			case AND:					return 
			case OR:					return 
			case DIV:					return 
			case MOD:					return 
			case NOT : 					return 
			case NEGACION :				return 

	}*/
