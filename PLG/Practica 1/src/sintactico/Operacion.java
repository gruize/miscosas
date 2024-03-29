package sintactico;

import interprete.TokenMaquina;
import analizadorLex.Token;

/**
 * Esta clase realiza el analisis sintactico 
 * @author Francisco Huertas Ferrer
 * @author Sara Guerrero Garcia
 * @author Gabriela Ruiz Escobar
 * @author Jose Ignacio Perez Solis
 */
public class Operacion {
	/**
	 * Las prioridades van de + prioritarias 0 a menos prioritaria 3
	 */
	/**
	 * codigo de la operacion almacenada
	 */
	public int codigo;
	/**
	 * Constantes con las diferentes opciones
	 */
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
	/**
	 * constructor parametrizado
	 * @param codigo
	 */
	public Operacion(int codigo) {
		super();
		this.codigo = codigo;
	}	
	/**
	 * Constructor desde un token
	 * @param codigoToken Token desde el que va a crear (+ - etc...)
	 * @param aridad Aridad del operador necesaria para diferenciar los distintos operandos
	 */
	public Operacion(int codigoToken,int aridad) {
		super();
		this.codigoFromToken(codigoToken, aridad);
	}
	/**
	 * Te devuelve el codigo maquian de la operacion de aridad dos
	 * @return el codigo maquina
	 */
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
	/**
	 * Devuelve el codigo maqina de la operacion con aridad 1
	 * @return el codigo
	 */
	public int getCodigoMaquinaSimple() {
		switch (this.codigo){
			case NOT : return TokenMaquina.NOT;
			case NEGACION: return TokenMaquina.NEGATIVO;
		}
		return 666;
	}
	/**
	 * Devuelve el token representativo de la operacion
	 * @return el codigo del token
	 */
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
	/**
	 * Aridad de la operacion
	 * @return aridad
	 */
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
	/**
	 * Te devuelve staticamente la prioridad
	 * @param token el token que pregunta la proridad
	 * @param aridad aridad del operando
	 * @return la prioridad
	 */
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
	/**
	 * Te da la prioridad de la operacion almacenada
	 * @return la prioridad
	 */
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
	
	/**
	 * nos coloca el codigo dado un token y su aridad
	 * @param codigo codigo del toekn
	 * @param aridad aridad
	 */
	public void codigoFromToken(int codigo, int aridad)
	{
		switch (codigo){

			case Token.OP_COMPARACION	: 	this.codigo = OP_COMPARACION;
											break;
			case Token.OP_SUMA			: 	this.codigo = OP_SUMA;				
											break;
			case Token.OP_MUL			:	this.codigo = OP_MUL;		 
											break;
			case Token.OP_DIV			:	this.codigo = OP_DIV;				 
											break;
			case Token.OP_MENOR_QUE		: 	this.codigo = OP_MENOR_QUE;			
											break;
			case Token.OP_MAYOR_QUE		:	this.codigo = OP_MAYOR_QUE;
											break;
			case Token.OP_DISTINTO		:	this.codigo = OP_DISTINTO;			 
											break;
			case Token.OP_MENOR_IGUAL	:	this.codigo = OP_MENOR_IGUAL;		 
											break;
			case Token.OP_MAYOR_IGUAL	:	this.codigo = OP_MAYOR_IGUAL;		 
											break;
			case Token.AND				:	this.codigo = AND;					
											break;
			case Token.OR				:	this.codigo = OR;
											break;
			case Token.DIV				:	this.codigo = DIV;
											break;
			case Token.MOD				:	this.codigo = MOD;
											break;
			case Token.NOT				:	this.codigo = NOT;
				break;
			case Token.OP_RESTA			:
				if (aridad == 1)
					this.codigo = NEGACION;
				else if (aridad == 2)
					this.codigo = OP_RESTA;
				break;
			default:
				this.codigo = -1;
		}
		
			
	
	}
	
	/**
	 * Te modifica el codigo de la clase dado un token y su aridad
	 * @param token el token dado 
	 * @param aridad la aridad
	 */
	public void codigoFromToken(Token token, int aridad) {
		codigoFromToken(token.codigo, aridad);
	}
	
	/**
	 * Procedimiento estatico que Dados dos operandos te dice si es correcta su asignacion
	 * @param tipo1
	 * @param tipo2
	 * @return el booleano que lo indica
	 */
	public static boolean tiposAsignacion(int tipo1,int tipo2){
		if ((tipo1==tipo2) || ((tipo1==Token.INTEGER && tipo2==Token.REAL) || (tipo2==Token.INTEGER && tipo1==Token.REAL)))
			return true;
		else return false;
		
	}
	
	/**
	 * Te dice si la operacion esta soportada para los operadores pasados
	 * @param tipo1 primer operador
	 * @param tipo2 segundo operador
	 * @return la comprobacion del tipo
	 */
	public boolean compruebaTipos(int tipo1,int tipo2){
		if (codigo ==OP_COMPARACION)
		{
			if ((tipo1==tipo2) || ((tipo1==Token.INTEGER && tipo2==Token.REAL) || (tipo2==Token.INTEGER && tipo1==Token.REAL)))
				return true;
			else return false;
		}
		else if(codigo==OP_DISTINTO)
		{
			if ((tipo1==tipo2) || ((tipo1==Token.INTEGER && tipo2==Token.REAL) || (tipo2==Token.INTEGER && tipo1==Token.REAL)))
				return true;
			else return false;
		}
		else if(codigo==OP_MAYOR_IGUAL)
		{
			if ((tipo1==tipo2) || ((tipo1==Token.INTEGER && tipo2==Token.REAL) || (tipo2==Token.INTEGER && tipo1==Token.REAL)))
				return true;
			else return false;
		}
		else if(codigo==OP_MAYOR_QUE)
		{
			if ((tipo1==tipo2) || ((tipo1==Token.INTEGER && tipo2==Token.REAL) || (tipo2==Token.INTEGER && tipo1==Token.REAL)))
				return true;
			else return false;
		}
		else if(codigo==OP_MENOR_IGUAL)
		{
			if ((tipo1==tipo2) || ((tipo1==Token.INTEGER && tipo2==Token.REAL) || (tipo2==Token.INTEGER && tipo1==Token.REAL)))
				return true;
			else return false;
		}
		else if(codigo==OP_MENOR_QUE)
		{
			if ((tipo1==tipo2) || ((tipo1==Token.INTEGER && tipo2==Token.REAL) || (tipo2==Token.INTEGER && tipo1==Token.REAL)))
				return true;
			else return false;
		}
		else if(codigo==OP_MUL)
		{
			if ((tipo1==Token.INTEGER && tipo2==Token.INTEGER)|| (tipo1==Token.REAL && tipo2==Token.REAL)||((tipo1==Token.INTEGER && tipo2==Token.REAL) || (tipo2==Token.INTEGER && tipo1==Token.REAL)))
				return true;
			else return false;
		}
		else if(codigo==OP_RESTA)
		{
			if ((tipo1==Token.INTEGER && tipo2==Token.INTEGER)|| (tipo1==Token.REAL && tipo2==Token.REAL)||((tipo1==Token.INTEGER && tipo2==Token.REAL) || (tipo2==Token.INTEGER && tipo1==Token.REAL)))
				return true;
			else return false;
		}
		else if(codigo==DIV)
		{
			if ((tipo1==Token.INTEGER && tipo2==Token.INTEGER)|| (tipo1==Token.REAL && tipo2==Token.REAL)||((tipo1==Token.INTEGER && tipo2==Token.REAL) || (tipo2==Token.INTEGER && tipo1==Token.REAL)))
				return true;
			else return false;
		}
		else if(codigo==MOD)
		{
			if ((tipo1==Token.INTEGER && tipo2==Token.INTEGER)|| (tipo1==Token.REAL && tipo2==Token.REAL)||((tipo1==Token.INTEGER && tipo2==Token.REAL) || (tipo2==Token.INTEGER && tipo1==Token.REAL)))
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
	/**
	 * dado un operando te dice si es correcta para esa operacion
	 * @param tipo1 el tipo
	 * @return si es correcta o no
	 */
	public boolean compruebaTipos(int tipo1){
		if(codigo ==NOT)
		{
			if (tipo1==Token.BOOLEAN)
				return true;
			else return false;
		}
		else if (codigo ==NEGACION)
		{
			if ((tipo1==Token.INTEGER) || (tipo1==Token.REAL))
				return true;
			else return false;
		}
		return false;
	
	}
	/**
	 * Devuelve un tipo de una operacion dada, con unos argumentos
	 * @param tipo1 tipo primero
	 * @param tipo2 segundo tipo
	 * @return el tipo resultante
	 */
	public int dameTipo (int tipo1, int tipo2){
		if (codigo == Operacion.OP_COMPARACION)
			return Token.BOOLEAN;
		else if(codigo==OP_DISTINTO)
			return Token.BOOLEAN;
		else if(codigo==OP_MAYOR_IGUAL)
			return Token.BOOLEAN;
		else if(codigo==OP_MAYOR_QUE)
			return Token.BOOLEAN;
		else if(codigo==OP_MENOR_IGUAL)
			return Token.BOOLEAN;
		else if(codigo==OP_MENOR_QUE)
			return Token.BOOLEAN;

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
	/**
	 * Devuelve el tipo de una operacion unaria
	 * @param tipo el tipo
	 * @return el tipo de la operacion resuelta
	 */
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
