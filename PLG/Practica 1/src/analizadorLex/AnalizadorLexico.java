package analizadorLex;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;

import excepciones.ExcepcionLexica;
import excepciones.ExcepcionToken;
import utilidades.*;
import utilidades.Reader;


public class AnalizadorLexico {
	
	private int numLinea;
	private int numColumna;
	private Character ultimoCharLeido;
	private Reader reader = null;
	private Token tokens[]=null;
	private boolean fin=false;
	
	
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
    
	
	  
	public boolean esFin()
	{
		return fin;
	}
	
	public AnalizadorLexico() {
		numLinea=1;
		numColumna=0;
	
	}
    private Character leerCaracter()throws IOException {
        if(reader==null) throw new IOException("Reader no inizializado");
        
        ultimoCharLeido = reader.readCharacter();
        numColumna = 0;
        if(esFinalDeLinea(ultimoCharLeido)) {
        	numColumna=0;
            numLinea++; //incrementa la linea
        }
        return ultimoCharLeido;
    }


		 private  boolean esFinalDeLinea(Character ch){
		        return ((ch!=null) && (ch.charValue()=='\n'));
		    }

		 
		  public void run() throws Exception {
		        //Obtengo los tokens
		        Token t= null;
		        boolean sigue=true;
		        leerCaracter();
		        numColumna++;
		       
		        int i=0;
		        for( t = nextToken(); esFin(); t = nextToken()){
		        	tokens[i]=t;
		            
		        }
		        //imprimo eof
		        System.out.println(t);
		        cerrarLector();
		    }
		    	 
		  
		  private void cerrarLector() {
			// TODO Auto-generated method stub
			
		}

		public Token nextToken() throws IOException, ExcepcionLexica{
		        
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
		                    numColumna++;
		                }
		                while(ch!=Reader.EOF && esSeparador(ch));
		                //continue;
		            }
		            //RECONOCIMIENTO DE COMENTARIOS DEL ESTILO { ... }
		            else if(ch.charValue() == '{'){
		                do{
		                    ch = leerCaracter();
		                    numColumna++;
		                }
		                while(ch!=Reader.EOF && ch.charValue()!='}');
		                if(ch!=Reader.EOF) 
		                {
		                	leerCaracter();
		                	numColumna++;
		                	}
		                else{
		                    throw new ExcepcionLexica(2,numLinea);
		                }
		                //continue;
		            }
		            
		          
		            //RECONOCIMIENTO DE CARACTERES SIMPLES
		            else if (ch.charValue()=='+') {
		                //avanza al proximo carater antes de retornar
		                leerCaracter();
		                numColumna++;
		                return new Token(Token.OP_SUMA ,"",lastLine, numColumna);
		            } else if (ch.charValue()=='-') {
		                leerCaracter();
		                numColumna++;
		                return new Token(Token.OP_RESTA ,"",lastLine, numColumna);
		            } else if (ch.charValue()=='*') {
		                leerCaracter();
		                numColumna++;
		                return new Token(Token.OP_MUL,"", lastLine, numColumna);
		            }else if (ch.charValue()=='=') {
		                leerCaracter();
		                numColumna++;
		                return new Token(Token.OP_COMPARACION,"", lastLine, numColumna);
		            }
		            //LEYO UN . PERO PUEDE TAMBIEN SER ..
		            else if (ch.charValue()=='.') {
		                return leerPunto();
		            } else if (ch.charValue()==';') {
		                leerCaracter();
		                numColumna++;
		                return new Token(Token.PUNTO_Y_COMA,"", lastLine, numColumna);
		            } else if (ch.charValue()==')') {
		                leerCaracter();
		                numColumna++;
		                return new Token(Token.C_PARENTESIS,"", lastLine, numColumna);
		            } else if (ch.charValue()=='(') {
		                leerCaracter();
		                numColumna++;
		                return new Token(Token.A_PARENTESIS,"", lastLine, numColumna);    
		          
		            } else if (ch.charValue()=='>') {
		                return leerMayorIgual();
		            } else if (ch.charValue()=='<') {
		                return  leerMenorIgualDist();
		            } else if (ch.charValue()==':') {
		                return  leerDosPuntosOAsignacion();
		            } else if (ch.charValue()=='}') {
		                throw new ExcepcionLexica(3, lastLine);
		            }
		            //RECONOCIMIENTO DE IDENTIFICADORES Y PALABRAS RESERVADAS
		            else  if (esLetra(ch)){
		                return getTokenID();
		            }
		            //RECONOCIMIENTO DE NUMEROS
		            else if (esDigito(ch)){
		                return getTokenNumero();
		            } else {
		                //caracter no perteneciente al alfabeto
		                throw new ExcepcionLexica(4, ch.toString(),lastLine);
		            }
		            }catch(ExcepcionLexica e){};
		        }
		    
		}
		 
		 
	private boolean esLetra(Character ch) {
			// TODO Auto-generated method stub
			return letras.contains(ch);
		}



	private Token getTokenID() {
			// TODO Auto-generated method stub
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
     
        if(cod!=null){
           
			return new Token(cod.intValue(),lex,ultimaLinea, numColumna);
			 
        } else
            return new Token(Token.ID, lex, ultimaLinea, numColumna);
		}

	
	
	private Token getTokenNumero() {
			// TODO Auto-generated method stub
		Character c;
        StringBuffer buff = new StringBuffer();
        c = ultimoCharLeido; //asume que es un digito
        int lineaUlt = numLinea;//salva la liena actual
        
        do {
            buff.append(c.charValue());
            try {
				c = leerCaracter();
				numColumna++;
			} catch (IOException e) 
			{	numColumna--;
				return null;
			}
        }
        while (esDigito(c));
        
        if (esLetra(c)){
            try {
				throw new ExcepcionLexica(0,buff.toString()+c.toString(), lineaUlt);
			} catch (ExcepcionLexica e) {return null;}
			
        }
       
			return new Token(Token.NUM,buff.toString(),lineaUlt, numColumna);
	
		
		}
	
	
	

	private boolean esDigito(Character ch) {
			// TODO Auto-generated method stub
			return digitos.contains(ch);
		}




	private Token leerDosPuntosOAsignacion() throws IOException {
			// TODO Auto-generated method stub
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
	
	

	private Token leerPunto() throws IOException 
	
	{
		 int lineaUlt = numLinea;
	        
	        Character proxi = leerCaracter();
	        if ((proxi==Reader.EOF) ) {
	            return new Token(Token.PUNTO,"",lineaUlt,numColumna);}
			return null;
	        
	        
	}



	private boolean esSeparador(Character ch) {
			// TODO Auto-generated method stub
		return separadores.contains(ch);
		}

	public Token[] getTokens(){
		
		return tokens;
	}

}
	


	
