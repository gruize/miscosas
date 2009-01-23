package analizadorLex;

import java.io.*;

import excepciones.ExcepcionLexica;
import utilidades.*;
import utilidades.Reader;


public class AnalizadorLexico {
	
	private int numLinea;
	private Character ultimoCharLeido;
	private Reader reader = null;
	private Token tokens[]=null;
	private boolean fin=false;
	
	  
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
		        for( t = nextToken(); esfin()t.codigo != Token.EOF; t = nextToken()){
		        	tokens[i]=t;
		            
		        }
		        //imprimo eof
		        System.out.println(t);
		        cerrarLector();
		    }
		    	 
		  
		  public Token nextToken() throws IOException, LexicException{
		        
		        while(true){
		            //guardo la ultima linea
		            int lastLine = numLinea;
		            Character ch = ultimoCharLeido;
		            
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
		                return new Token(Token.OP_MULT,"", lastLine);
		            }else if (ch.charValue()=='=') {
		                leerCaracter();
		                return new Token(Token.IGUAL,"", lastLine);
		            }
		            //LEYO UN . PERO PUEDE TAMBIEN SER ..
		            else if (ch.charValue()=='.') {
		                return leerPuntoPunto();
		            } else if (ch.charValue()==';') {
		                leerCaracter();
		                return new Token(Token.PUNTO_Y_COMA,"", lastLine);
		            } else if (ch.charValue()==',') {
		                leerCaracter();
		                return new Token(Token.COMA,"", lastLine);
		            } else if (ch.charValue()==')') {
		                leerCaracter();
		                return new Token(Token.PAR_CIERRA,"", lastLine);
		            } else if (ch.charValue()=='[') {
		                leerCaracter();
		                return new Token(Token.COR_ABRE,"", lastLine);
		            } else if (ch.charValue()==']') {
		                leerCaracter();
		                return new Token(Token.COR_CIERRA,"", lastLine);
		            } else if (ch.charValue()=='>') {
		                return leerMayorIgual();
		            } else if (ch.charValue()=='<') {
		                return  leerMenorIgualDist();
		            } else if (ch.charValue()==':') {
		                return  leerDosPuntosOAsignacion();
		            } else if (ch.charValue()=='}') {
		                throw new LexicException(3, lastLine);
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
		                throw new LexicException(4, ch.toString(),lastLine);
		            }
		        }
		    }
		 
		 
	private boolean esSeparador(Character ch) {
			// TODO Auto-generated method stub
			return false;
		}

	public Token[] getTokens(){
		
		return tokens;
	}

}
