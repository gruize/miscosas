package analizadorLex;

import java.io.*;
import java.util.*;


import excepciones.ExcepcionLexica;
import excepciones.ExcepcionSintactica;
import excepciones.MensajeLex;
import utilidades.*;
import utilidades.Reader;


public class AnalizadorLexico {
	
	private int numLinea;
	private int numColumna;
	private Character ultimoCharLeido;
	private Reader reader = null;
	public Vector <Token> tokens=null;
	private boolean fin= false;
	public ExcepcionLexica excepcion = new ExcepcionLexica();
	PalabrasReservadas palabrasR=new PalabrasReservadas();
	

	public static final HashSet<Character> digitos = new HashSet<Character>(10);
    static
    {
        for(char d = '0'; d<='9';d++) digitos.add(new Character(d));
    }
   
    public static final char[] listLetras={'a','b', 'c', 'd' ,'e', 'f', 'g' ,'h' ,'i', 'j' ,'k', 'l', 'm','n', 'o', 'p', 'q', 'r', 's','t','u','v','w','x','y','z',
    'A','B','C','D','E','F','G','H','I','J','K','L', 'M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z' };
    public static final HashSet<Character> letras  = new HashSet<Character>(listLetras.length);
    static{
        for(char ch:listLetras) letras.add(new Character(ch));
    }
    
    private static final HashSet<Character> separadores = new HashSet<Character>(4);
    static{
        separadores.add(new Character(' '));
        separadores.add(new Character('\n'));
        separadores.add(new Character('\t'));
        separadores.add(new Character('\r'));
    }
    
    /**
     * Constructora de la clase sin parametros  
     */
	public AnalizadorLexico(){
		numLinea=1;
		numColumna=0;
		tokens= new Vector <Token> () ;	
	}
	

	/**
	 * Constructora de la clase
	 * @param Fichero Fichero de entrada a analizar
	 * @throws IOException si existe algun error con el fichero
	 */
	public AnalizadorLexico(String Fichero) throws IOException {
		numLinea=1;
		numColumna=0;
		reader = new BufferedFileReader(Fichero);
		tokens= new Vector <Token> () ;
	
	}



		 
	/**
	 * Procedimiento principal de la clase :
	 * Ejecuta el bucle de reconocimiento de tokens y los almacena en un vector.
	 * @throws IOException
	 */
		 public void run() throws IOException {
		        //Obtengo los tokens
		        Token t= null;
		       
		        leerCaracter();
		        
		       
		       
		        for( t = nextToken(); !esFin(); t = nextToken())
				{	if (!(esFin())){
					if(!(t==null)){
						// XXX Hay que borrarlo
						tokens.add(t);
						System.out.println(t.lexema);
					}
					
				}
		        //imprimo eof
		        
		        
		    }
		        System.out.println("Fin  del Analizador Léxico");
		 } 	 
		  
		
		 /**
		  * Reconoce caracter a caracter el fichero de entrada para realizar el reconocimiento
		  * @return devuelve un objeto clase token
		  * @throws IOException
		  */
		public Token nextToken() throws IOException{
		        
		        while(true){
		            //guardo la ultima linea
		            int lastLine = numLinea;
		            Character ch = ultimoCharLeido;
		            try{ 
		            if(ch == Reader.EOF){
		            	fin=true;
		                return null;
		            }
		            //RECONOCIMIENTO DE CARACTERES SEPARADORES ' ','\t','\n','\r'
		            else if (esSeparador(ch)) {
		                do{
		                    ch = leerCaracter();
		                   
		                }
		                while(ch!=Reader.EOF && esSeparador(ch));
		                //continue;
		            }
		            //RECONOCIMIENTO DE COMENTARIOS DEL ESTILO { ... }
		    /*        else if(ch.charValue() == '{'){
		                do{
		                    ch = leerCaracter();
		                   
		                }
		                while(ch!=Reader.EOF && ch.charValue()!='}');
		                if(ch!=Reader.EOF) 
		                {
		                	leerCaracter();
		                	
		                	}
		                else{
		                    this.excepcion.addMensaje("error Lexico se esperaba }",numLinea,numColumna);
		                    leerCaracter();
		                }
		                //continue;
		            }
		            
		          //RECONOCIMIENTO DE CHAR
		            else if (ch.charValue()=='\'') {
		                //avanza al proximo carater antes de retornar
		                
		            	Character c=leerCaracter();
		            	
		                return caracter(c);
		            } 
		            
		            //RECONOCIMIENTO DE CARACTERES SIMPLES */
		            else if (ch.charValue()=='+') {
		                //avanza al proximo carater antes de retornar
		                leerCaracter();
		              
		                return new Token(Token.OP_SUMA ,"",lastLine, numColumna);
		            } else if (ch.charValue()=='-') {
		                leerCaracter();
		           
		                return new Token(Token.OP_RESTA ,"",lastLine, numColumna);
		            } else if (ch.charValue()=='*') {
		                leerCaracter();
		               
		                return new Token(Token.OP_MUL,"", lastLine, numColumna);
		            }else if (ch.charValue()=='=') {
		                leerCaracter();
		                
		                return new Token(Token.OP_COMPARACION,"", lastLine, numColumna);
		            }
		            else if (ch.charValue()=='/') {
		                leerCaracter();
		                
		                return new Token(Token.OP_DIV,"", lastLine, numColumna);
		            }
		            
		            else if (ch.charValue()=='\'') {
		            	ch=leerCaracter();
		                return caracter(ch);
		            }
		            else if (ch.charValue()=='.') {
		                return leerPunto();
		            } else if (ch.charValue()==';') {
		                leerCaracter();
		              
		                return new Token(Token.PUNTO_Y_COMA,"", lastLine, numColumna);
		            } else if (ch.charValue()==')') {
		                leerCaracter();
		              
		                return new Token(Token.C_PARENTESIS,"", lastLine, numColumna);
		            } else if (ch.charValue()=='(') {
		                leerCaracter();
		           
		                return new Token(Token.A_PARENTESIS,"", lastLine, numColumna);    
		          
		            } else if (ch.charValue()=='>') {
		                return leerMayorIgual();
		            } else if (ch.charValue()=='<') {
		                return  leerMenorIgualDist();
		            } else if (ch.charValue()==':') {
		                return  leerDosPuntosOAsignacion();
		            } //else if (ch.charValue()=='}') {
		            	//this.excepcion.addMensaje(" } no esperada ", numLinea, numColumna);
		            	//leerCaracter();
		           // }
		            //RECONOCIMIENTO DE IDENTIFICADORES Y PALABRAS RESERVADAS
		            else  if (esLetra(ch)){
		                return getTokenID();
		            }
		            //RECONOCIMIENTO DE NUMEROS
		            else if (esDigito(ch)){
		                return getTokenNumero();
		            } else {
		                //caracter no perteneciente al alfabeto
		            	this.excepcion.addMensaje("caracter no perteneciente al alfabeto ",numLinea,numColumna);
		            	leerCaracter();
		            }
		            }catch(ExcepcionLexica e){};
		        }
		    
		}
		
		/**
		 * Funcion auxiliar para recorrer el fichero
		 * actualiza las variables numLinea y numColumna
		 * @return Devuelve un caracter
		 * @throws IOException
		 */
	    private Character leerCaracter()throws IOException {
	        if(reader==null) throw new IOException("Reader no inizializado");
	        
	        ultimoCharLeido = reader.readCharacter();
	        numColumna++;
	        if(esFinalDeLinea(ultimoCharLeido)) {
	        	numColumna=0;
	            numLinea++; //incrementa la linea
	        }
	        return ultimoCharLeido;
	    }
		 
	    /**
	     * Funcion que reconoce un token del tipo CHAR 
	     * @param c
	     * @return	Devuelve u token
	     * @throws IOException
	     */
		private Token caracter(Character c) throws IOException  {
			Character ch=null;
			if (esLetra(c))
				try {
					ch= leerCaracter();
			} catch (IOException e) {
				this.excepcion.addMensaje("simbolo no perteneciente al alfabeto ",numLinea,numColumna);
				throw new IOException("Se esperaba un caracter");
				
			}if (!(ch==null))
			{
			if (ch.charValue()=='\'') {	ultimoCharLeido=leerCaracter();
			return new Token(Token.VALORCHAR,c.toString(),numLinea, numColumna);}
			}
			else {this.excepcion.addMensaje("error lexico ",numLinea,numColumna);
			leerCaracter();}
			
			return null;
	}




		/**
		 * Funcion que devuelve un token de tipo ID
		 * @return Devuelve un token 
		 */
	private Token getTokenID() {
		Character c;
		StringBuffer buff = new StringBuffer();
		c=ultimoCharLeido; //asume que es una letra
		int ultimaLinea = numLinea; //salva la linea actual

		do{
			buff.append(c.charValue());
			try {
				c = leerCaracter();
			} catch (IOException e) {}

		}while(esDigito(c)||esLetra(c));

		String lex =  buff.toString();
		String lexema = lex.toLowerCase();

		Integer cod = PalabrasReservadas.PALABRAS_RESERVADAS.get(lexema);

		if(cod==null){

			return new Token(Token.ID, lex, ultimaLinea, numColumna);
		} else
			return new Token(cod.intValue(),lex,ultimaLinea, numColumna);

		}

	
	/**
	 * Devuelve un token de tipo NUM o NUMREAL
	 * @return Devuelve un token
	 * @throws ExcepcionLexica en caso de que sea erroneo
	 */
	private Token getTokenNumero() throws ExcepcionLexica {
		Character c;
		StringBuffer buff = new StringBuffer();
		c = ultimoCharLeido; //asume que es un digito
		int lineaUlt = numLinea;//salva la liena actual
		int colAct= numColumna;
		boolean esreal=false;
		boolean error=false;
		do {
			buff.append(c.charValue());
			try {
				c = leerCaracter();
			} catch (IOException e) 
			{	
				return null;
			}
		}
		while (esDigito(c));

		if (c.charValue()=='.') {
			try {
				buff.append(c.charValue());
				c = leerCaracter();

			} catch (IOException e1) {
				return null;
			}
			while (esDigito(c))
			{  
				buff.append(c.charValue());
				try {
					c = leerCaracter();
					numColumna++;
				} catch (IOException e) 
				{	numColumna--;
				return null;
				}
			}
			esreal=true;}

		if (esLetra(c)){
			while(esDigito(c)||esLetra(c)||c.charValue()=='.')
			{
				try {
					c = leerCaracter();
				} catch (IOException e) {}

			}
			error=true;
			this.excepcion.addMensaje("un Id no puede empezar por un numero ",numLinea,colAct);
			

		}

		if (error)
			return null; 
		else 
			if (esreal) return new Token(Token.NUMREAL,buff.toString(),lineaUlt, colAct);
			else
				return new Token(Token.NUM,buff.toString(),lineaUlt, colAct);


	}
	



	/**
	 * 	Reconoce el token : || :=
	 * @return Token
	 * @throws IOException
	 */
	private Token leerDosPuntosOAsignacion() throws IOException {
		   // asume que ultimoCharLeido :
        int lineaUlt = numLinea; //salva la linea actual
        
        Character ch = leerCaracter();
        if ((ch==Reader.EOF) || (ch.charValue()!= '=')) {
            return new Token( Token.DOSPUNTOS,"",lineaUlt,numColumna);
        }
        //el caracter actual es =
        leerCaracter(); //avanza al proximo carater antes de retornar
        return new Token(Token.OP_ASIGNACION,"",lineaUlt,numColumna);
		}
	/**
	 * 	Reconoce el token < || <= || <>
	 * @return Token
	 * @throws IOException
	 */
	private Token leerMenorIgualDist() throws IOException 
	
	{int lineaUlt = numLinea; //salva la linea actual
    
    Character proxi = leerCaracter();
    if ((proxi==Reader.EOF) || (proxi.charValue()!= '='  && proxi.charValue()!='>')) {
        return new Token(Token.OP_MENOR_QUE,"<",lineaUlt,numColumna);}
    
    leerCaracter();   //avanza al proximo carater antes de retornar
    
    if (proxi.charValue()=='='){ //debe ser <=            
        return new Token(Token.OP_MENOR_IGUAL,"<=",lineaUlt,numColumna);
    }
    //el caracter leido proxi debe ser >
    return new Token(Token.OP_DISTINTO,"<>",lineaUlt,numColumna);
		
		
		}
	/**
	 * 	Reconoce el token > || >=
	 * @return Token
	 * @throws IOException
	 */
	private Token leerMayorIgual() throws IOException
	{
		int lineaUlt = numLinea; //salva la linea actual
        
        Character proxi=leerCaracter();
        if ((proxi==Reader.EOF) || (proxi.charValue()!= '=')) {
            return new Token(Token.OP_MAYOR_QUE,">",lineaUlt,numColumna);}
        //el caracter actual es =
        leerCaracter(); //avanza al proximo carater antes de retouna
        return new Token(Token.OP_MAYOR_IGUAL,">=",lineaUlt,numColumna);
	}
	
	
	/**
	 * 	Reconoce el token .
	 * @return Token
	 * @throws IOException
	 */
	private Token leerPunto() throws IOException 
	
	{
		 int lineaUlt = numLinea;
	        
	        Character proxi = leerCaracter();
	    //  if ((proxi==Reader.EOF) ) {
	            return new Token(Token.PUNTO,"",lineaUlt,numColumna);
			//}
	      
	      //else return null; 
	        
	        
	}

	/**
	 * Indica si el caracter es letra
	 * @param ch caracter a analizar
	 * @return true si es letra false eoc
	 */
	private boolean esLetra(Character ch) {
			return letras.contains(ch);
		}
	/**
	 * Indica si el caracter es digito
	 * @param ch caracter a analizar
	 * @return true si es digito false eoc
	 */
	private boolean esDigito(Character ch) {
			return digitos.contains(ch);
		}
	/**
	 * Indica si el caracter es separador
	 * @param ch caracter a analizar
	 * @return true si es separador false eoc
	 */
	private boolean esSeparador(Character ch) {
		return separadores.contains(ch);
		}
	/**
	 * Indica si el caracter es \n
	 * @param ch caracter a analizar
	 * @return true si es \n false eoc
	 */
	private  boolean esFinalDeLinea(Character ch){
        return ((ch!=null) && (ch.charValue()=='\n'));
    }
	/**
	 * Es final de archivo
	 * @return true si es final de archivo falso eoc
	 */
	public boolean esFin(){
		return fin;	
	}
	
	/**
	 * Termina la ejecucion del traductor imrpimiendo los errores si existen
	 * y cerrando el archivo
	 */
	public void finish() {
		excepcion.printAll();
		reader.close();
	}

}
	


	
