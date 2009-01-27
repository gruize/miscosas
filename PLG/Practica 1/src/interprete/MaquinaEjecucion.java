/**
 * 
 */
package interprete;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.Stack;
import java.util.Vector;

import utilidades.Operaciones;
import excepciones.ExcepcionMaquina;
import main.Testeable;

/**
 * @author GabiPC
 *
 */
public class MaquinaEjecucion implements Testeable{
                   
    /** Tope de pila */
    private int topePila;
    
    /** Contador de programa */
    private int i;
    
    /** Direcciones de memoria */
    private Vector<Operandos> memoria;
    
    /** Pila */
    private Stack<Operandos> pila;

    /**Lectura de objetos*/
    private ObjectInputStream entrada;
    
    /** 
     * Constructor
     */
	public MaquinaEjecucion(String sourceFile)throws Exception{
		this.memoria = new Vector<Operandos>();
		this.pila = new Stack<Operandos>();
		this.entrada = new ObjectInputStream(new FileInputStream(sourceFile));
	}

	@Override
	public void run()throws Exception{
		this.leerOperaciones();		
	}

	public void leerOperaciones() throws Exception{
		Operaciones instruccion = null;
		
		do{
			instruccion = (Operaciones) this.entrada.readObject();
			Operandos valor = null;
			if((instruccion.codigoOperacion == TokenMaquina.APILA)
			 ||(instruccion.codigoOperacion == TokenMaquina.APILA_DIR)
			 ||(instruccion.codigoOperacion == TokenMaquina.DESAPILA_DIR)
			 ||(instruccion.codigoOperacion == TokenMaquina.LECTURA)){
				valor = (Operandos) this.entrada.readObject();
			}			
			this.ejecutar(instruccion,valor);
			
		}while(instruccion.codigoOperacion != TokenMaquina.STOP);
	}
	
	public void ejecutar(Operaciones instruccion,Operandos valor) throws Exception{
	    try{	    	
	    	OperandoNum direccion;
	    	Operandos temp1, temp2;
	    	int codigo = instruccion.codigoOperacion;
	    	switch(codigo){
	    		case TokenMaquina.APILA:
	    			this.pila.push(valor);
	    			break;
	    		case TokenMaquina.APILA_DIR:
	    			direccion = (OperandoNum)valor;
	    			this.pila.push((Operandos)(this.memoria.elementAt((Integer)direccion.dameValor())));
	    			break;
	    		case TokenMaquina.DESAPILA_DIR:
	    			direccion = (OperandoNum)valor;
	    			if((Integer)direccion.dameValor() >= this.memoria.size()){
	    				for(int i = this.memoria.size(); i <= (Integer)direccion.dameValor(); i++)
	    					this.memoria.add(null);
	    			}
	    			this.memoria.add((Integer)direccion.dameValor(),this.pila.pop());
	    			break;
	    		case TokenMaquina.NEGATIVO:
	    			Operandos temp3 = this.pila.pop();
	    			if(temp3.dameTipo() == Operandos.NUM){
	    				OperandoNum temp = new OperandoNum((Integer)(0 - (Integer)((OperandoNum) temp3).dameValor()));
	    				this.pila.push(temp);
	    			}
	    			if(temp3.dameTipo() == Operandos.NUMREAL){    				    			
	    				OperandoNumReal temp = new OperandoNumReal((Double)(0.0 - (Double)((OperandoNum) temp3).dameValor()));
	    				this.pila.push(temp);
	    			}
	    			break;
	    		case TokenMaquina.SUMA:
	    			temp1 = this.pila.pop();
	    			temp2 = this.pila.pop();	    		
	    			if(temp1.dameTipo() == Operandos.NUM)
	    				((OperandoNum)temp1).valor = ((OperandoNum)temp1).valor + ((OperandoNum)temp2).valor;	  
	    			if(temp1.dameTipo() == Operandos.NUMREAL)
	    				((OperandoNumReal)temp1).valor = ((OperandoNumReal)temp1).valor + ((OperandoNumReal)temp2).valor;	    			
	    			this.pila.push(temp1);
	    			break;
	    		case TokenMaquina.RESTA:
	    			temp1 = this.pila.pop();
	    			temp2 = this.pila.pop();	    		
	    			if(temp1.dameTipo() == Operandos.NUM)
	    				((OperandoNum)temp1).valor = ((OperandoNum)temp1).valor - ((OperandoNum)temp2).valor;	  
	    			if(temp1.dameTipo() == Operandos.NUMREAL)
	    				((OperandoNumReal)temp1).valor = ((OperandoNumReal)temp1).valor - ((OperandoNumReal)temp2).valor;	    			
	    			this.pila.push(temp1);
	    			break;
	    		case TokenMaquina.MULTIPLICACION:
	    			temp1 = this.pila.pop();
	    			temp2 = this.pila.pop();	    		
	    			if(temp1.dameTipo() == Operandos.NUM)
	    				((OperandoNum)temp1).valor = ((OperandoNum)temp1).valor * ((OperandoNum)temp2).valor;	  
	    			if(temp1.dameTipo() == Operandos.NUMREAL)
	    				((OperandoNumReal)temp1).valor = ((OperandoNumReal)temp1).valor * ((OperandoNumReal)temp2).valor;	    			
	    			this.pila.push(temp1);
	    			break;
	    		case TokenMaquina.DIVISION:
	    			temp1 = this.pila.pop();
	    			temp2 = this.pila.pop();	    		
	    			if(temp1.dameTipo() == Operandos.NUM)
	    				((OperandoNum)temp1).valor = (((OperandoNum)temp1).valor / ((OperandoNum)temp2).valor);	  
	    			if(temp1.dameTipo() == Operandos.NUMREAL){
	    				((OperandoNumReal)temp1).valor = ((OperandoNumReal)temp1).valor / ((OperandoNumReal)temp2).valor;
	    			}
	    			this.pila.push(temp1);
	    			break;	    			
	    		case TokenMaquina.DIV:
	    			temp1 = this.pila.pop();
	    			temp2 = this.pila.pop();	    		
	    			if(temp1.dameTipo() == Operandos.NUM)
	    				((OperandoNum)temp1).valor = ((OperandoNum)temp1).valor / ((OperandoNum)temp2).valor;	  
	    			if(temp1.dameTipo() == Operandos.NUMREAL)
	    				((OperandoNumReal)temp1).valor = ((OperandoNumReal)temp1).valor / ((OperandoNumReal)temp2).valor;
	    			this.pila.push(new OperandoNum((Integer)temp1.dameValor()));
	    			break;
	    		case TokenMaquina.MOD:
	    			temp1 = this.pila.pop();
	    			temp2 = this.pila.pop();	    		
	    			if(temp1.dameTipo() == Operandos.NUM)
	    				((OperandoNum)temp1).valor = (((OperandoNum)temp1).valor % ((OperandoNum)temp2).valor);	  
	    			if(temp1.dameTipo() == Operandos.NUMREAL)
	    				((OperandoNumReal)temp1).valor = ((OperandoNumReal)temp1).valor % ((OperandoNumReal)temp2).valor;	    			
	    			this.pila.push(temp1);
	    			break;
	    		case TokenMaquina.MAYOR:
	    			temp1 = this.pila.pop();
	    			temp2 = this.pila.pop();	    	
	    			if(temp1.dameTipo() == Operandos.NUM)
	    				this.pila.push(new OperandoValorBoolean((Boolean)(((Integer)temp1.dameValor()) > ((Integer)temp2.dameValor()))));
	    			if(temp1.dameTipo() == Operandos.NUMREAL)
	    				this.pila.push(new OperandoValorBoolean((Boolean)(((Double)temp1.dameValor()) > ((Double)temp2.dameValor()))));
	    			break;			
	    		case TokenMaquina.MENOR:
	    			temp1 = this.pila.pop();
	    			temp2 = this.pila.pop();	    		
	    			if(temp1.dameTipo() == Operandos.NUM)
	    				this.pila.push(new OperandoValorBoolean((Boolean)(((Integer)temp1.dameValor()) < ((Integer)temp2.dameValor()))));
	    			if(temp1.dameTipo() == Operandos.NUMREAL)
	    				this.pila.push(new OperandoValorBoolean((Boolean)(((Double)temp1.dameValor()) < ((Double)temp2.dameValor()))));
	    			break;	    		
	    		case TokenMaquina.IGUAL:
	    			temp1 = this.pila.pop();
	    			temp2 = this.pila.pop();	    	
	    			if(temp1.dameTipo() == Operandos.NUM)
	    				this.pila.push(new OperandoValorBoolean((Boolean)((temp1.dameValor()) == (temp2.dameValor()))));
	    			if(temp1.dameTipo() == Operandos.NUMREAL)
	    				this.pila.push(new OperandoValorBoolean((Boolean)((temp1.dameValor()) == (temp2.dameValor()))));
	    			if(temp1.dameTipo() == Operandos.VALORCHAR)
	    				this.pila.push(new OperandoValorBoolean((Boolean)((temp1.dameValor()) == (temp2.dameValor()))));
	    			if(temp1.dameTipo() == Operandos.VALORBOOLEAN)    				
	    				this.pila.push(new OperandoValorBoolean((Boolean)((temp1.dameValor()) == (temp2.dameValor()))));
	    			break;	
	    		case TokenMaquina.MAYOR_IGUAL:
	    			temp1 = this.pila.pop();
	    			temp2 = this.pila.pop();	    		
	    			if(temp1.dameTipo() == Operandos.NUM)
	    				this.pila.push(new OperandoValorBoolean((Boolean)(((Integer)temp1.dameValor()) >= ((Integer)temp2.dameValor()))));
	    			if(temp1.dameTipo() == Operandos.NUMREAL)
	    				this.pila.push(new OperandoValorBoolean((Boolean)(((Double)temp1.dameValor()) >= ((Double)temp2.dameValor()))));
	    			break;	
	    		case TokenMaquina.MENOR_IGUAL:
	    			temp1 = this.pila.pop();
	    			temp2 = this.pila.pop();	    		
	    			if(temp1.dameTipo() == Operandos.NUM)
	    				this.pila.push(new OperandoValorBoolean((Boolean)(((Integer)temp1.dameValor()) <= ((Integer)temp2.dameValor()))));
	    			if(temp1.dameTipo() == Operandos.NUMREAL)
	    				this.pila.push(new OperandoValorBoolean((Boolean)(((Double)temp1.dameValor()) <= ((Double)temp2.dameValor()))));
	    			break;	
	    		case TokenMaquina.DISTINTO:
	    			temp1 = this.pila.pop();
	    			temp2 = this.pila.pop();	    	
	    			if(temp1.dameTipo() == Operandos.NUM)
	    				this.pila.push(new OperandoValorBoolean((Boolean)((temp1.dameValor()) != (temp2.dameValor()))));
	    			if(temp1.dameTipo() == Operandos.NUMREAL)
	    				this.pila.push(new OperandoValorBoolean((Boolean)((temp1.dameValor()) != (temp2.dameValor()))));
	    			if(temp1.dameTipo() == Operandos.VALORCHAR)
	    				this.pila.push(new OperandoValorBoolean((Boolean)((temp1.dameValor()) != (temp2.dameValor()))));
	    			if(temp1.dameTipo() == Operandos.VALORBOOLEAN)    				
	    				this.pila.push(new OperandoValorBoolean((Boolean)((temp1.dameValor()) != (temp2.dameValor()))));
	    			break;	
	    		case TokenMaquina.OR:
	    			temp1 = this.pila.pop();
	    			temp2 = this.pila.pop();	    			
	    			this.pila.push(new OperandoValorBoolean((Boolean)temp1.dameValor() || (Boolean)temp2.dameValor()));
	    			break;
	    		case TokenMaquina.AND:
	    			temp1 = this.pila.pop();
	    			temp2 = this.pila.pop();
	    			this.pila.push(new OperandoValorBoolean((Boolean)temp1.dameValor() && (Boolean)temp2.dameValor()));
	    			break;
	    		case TokenMaquina.NOT:
	    			temp1 = this.pila.pop();
	    			this.pila.push(new OperandoValorBoolean(!(Boolean)temp1.dameValor()));
	    			break;
	    		case TokenMaquina.LECTURA:
	    			temp1 = (OperandoNum)valor;
	    			temp2 = this.memoria.get(((Integer)temp1.dameValor()).intValue());
	    			if(temp2.dameTipo() == Operandos.NUM){	
	    				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	    				System.out.println("Escribe un numero entero: ");
	    				Integer valorInteger = Integer.parseInt(in.readLine());
	    				this.memoria.add(((Integer)temp1.dameValor()).intValue(), new OperandoNum(valorInteger));	    				
	    			}
	    			if(temp2.dameTipo() == Operandos.NUMREAL){
	    				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	    				System.out.println("Escribe un numero real: ");
	    				Double valorDouble = Double.parseDouble(in.readLine());
	    				this.memoria.add(((Integer)temp1.dameValor()).intValue(), new OperandoNumReal(valorDouble));
	    			}
	    			if(temp2.dameTipo() == Operandos.VALORCHAR){
	    				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	    				System.out.println("Escribe un caracter: ");
	    				Character valorChar = in.readLine().charAt(0);
	    				this.memoria.add(((Integer)temp1.dameValor()).intValue(), new OperandoValorChar(valorChar));
	    			}
	    			break;
	    		case TokenMaquina.ESCRITURA:
	    			temp1 = this.pila.pop();
	    			if(temp1.dameTipo() == Operandos.NUM){
	    				System.out.println((Integer)temp1.dameValor());
	    			}
	    			if(temp1.dameTipo() == Operandos.NUMREAL){
	    				System.out.println(((Double)temp1.dameValor()).toString());	    				
	    			}
	    			if(temp1.dameTipo() == Operandos.VALORCHAR){
	    				System.out.println((Character)temp1.dameValor());
	    			}
	    			if(temp1.dameTipo() == Operandos.VALORBOOLEAN){
	    				System.out.println((Boolean)temp1.dameValor());
	    			}	    				
	    			break;
	    		case TokenMaquina.STOP:
	    			System.out.println("FIN");
	    	}
		}catch (IndexOutOfBoundsException e){
	        throw new ExcepcionMaquina(2,i);
	    }		
	}

	@Override
	public boolean extensionArchivoValida(String file){
		return file.endsWith(".mp");
	}
	
	@Override
	public void setArchivoLectura(String source) {
		try {
			this.entrada.close();
			this.entrada = new ObjectInputStream(new FileInputStream(source));			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
}
