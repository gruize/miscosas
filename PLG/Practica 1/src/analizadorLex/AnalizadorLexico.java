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
	private Character ultimoCharLeido;
	private Reader reader = null;
	private Token tokens[]=null;
	private boolean fin=false;
	
  public static final HashSet<Character> digitos = new HashSet<Character>(10);
    static{
        for(char d = '0'; d<='9';d++) digitos.add(new Character(d));
    }
    
    private static final HashMap<String,Integer> palabrasReservadas = new HashMap<String,Integer>(25);
    static{
    	palabrasReservadas.put("program",new Integer(Token.PROGRAM));
    	palabrasReservadas.put("begin",new Integer(Token.BEGIN));
    	palabrasReservadas.put("end",new Integer(Token.END));
        palabrasReservadas.put("const",new Integer(Token.CONST));
        palabrasReservadas.put("var",new Integer(Token.VAR));
        palabrasReservadas.put(":",new Integer(Token.DOSPUNTOS));
        palabrasReservadas.put(":=",new Integer(Token.OP_ASIGNACION));      
        palabrasReservadas.put("not",new Integer(Token.NOT));
        palabrasReservadas.put("or",new Integer(Token.OR));
        palabrasReservadas.put("and",new Integer(Token.AND));
        palabrasReservadas.put("div",new Integer(Token.DIV));
      
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
		
	
	}
    private Character leerCaracter()throws IOException {
        if(reader==null) throw new IOException("Reader no inizializado");
        
        ultimoCharLeido = reader.readCharacter();
        if(esFinalDeLinea(ultimoCharLeido)) {
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
		                }
		                while(ch!=Reader.EOF && esSeparador(ch));
		                //continue;
		            }
		            //RECONOCIMIENTO DE COMENTARIOS DEL ESTILO { ... }
		            else if(ch.charValue() == '{'){
		                do{
		                    ch = leerCaracter();
		                }
		                while(ch!=Reader.EOF && ch.charValue()!='}');
		                if(ch!=Reader.EOF) leerCaracter();
		                else{
		                    throw new ExcepcionLexica(2,numLinea);
		                }
		                //continue;
		            }
		            //LEYO UN PARENTESIS QUE ABRE, PUEDE SER COMENTARIO O NO.
		            else if(ch.charValue() == '('){
		                Token token = leerComentarioPar();
		                //SI NO ES NULL ENTONCES ES UN PARENTESIS
		                if(token!=null) return token;
		                continue;
		            }
		          
		            //RECONOCIMIENTO DE CARACTERES SIMPLES
		            else if (ch.charValue()=='+') {
		                //avanza al proximo carater antes de retornar
		                leerCaracter();
		                return new Token(Token.OP_SUMA ,"",lastLine);
		            } else if (ch.charValue()=='-') {
		                leerCaracter();
		                return new Token(Token.OP_RESTA ,"",lastLine);
		            } else if (ch.charValue()=='*') {
		                leerCaracter();
		                return new Token(Token.OP_MUL,"", lastLine);
		            }else if (ch.charValue()=='=') {
		                leerCaracter();
		                return new Token(Token.OP_COMPARACION,"", lastLine);
		            }
		            //LEYO UN . PERO PUEDE TAMBIEN SER ..
		            else if (ch.charValue()=='.') {
		                return leerPuntoPunto();
		            } else if (ch.charValue()==';') {
		                leerCaracter();
		                return new Token(Token.PUNTO_Y_COMA,"", lastLine);
		            } else if (ch.charValue()==')') {
		                leerCaracter();
		                return new Token(Token.C_PARENTESIS,"", lastLine);
		          
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
		            }catch(ExcepcionToken e){};
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
        
        Integer cod = palabrasReservadas.get(lexema);
        try {
        if(cod!=null){
           
			return new Token(cod.intValue(),lex,ultimaLinea);
			 
        } else
            return new Token(Token.ID, lex, ultimaLinea);
        }catch (ExcepcionToken e) {}
		return null;
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
			} catch (IOException e) {return null;}
        }
        while (esDigito(c));
        
        if (esLetra(c)){
            try {
				throw new ExcepcionLexica(0,buff.toString()+c.toString(), lineaUlt);
			} catch (ExcepcionLexica e) {return null;}
			
        }
        try {
			return new Token(Token.NUM,buff.toString(),lineaUlt);
		} catch (ExcepcionToken e) {return null;}
		
		}

	private boolean esDigito(Character ch) {
			// TODO Auto-generated method stub
			return digitos.contains(ch);
		}




	private Token leerDosPuntosOAsignacion() {
			// TODO Auto-generated method stub
			return null;
		}

	private Token leerMenorIgualDist() {
			// TODO Auto-generated method stub
			return null;
		}

	private Token leerMayorIgual() {
			// TODO Auto-generated method stub
			return null;
		}

	private Token leerPuntoPunto() {
			// TODO Auto-generated method stub
			return null;
		}

	private Token leerComentarioPar() {
			// TODO Auto-generated method stub
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
