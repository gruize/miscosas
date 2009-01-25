/**
 * 
 */
package interprete;

import java.io.FileInputStream;
import java.io.IOException;
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
		this.memoria = new Vector<Object>();
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
			 ||(instruccion.codigoOperacion == TokenMaquina.DESAPILA_DIR)){
				valor = (Operandos) this.entrada.readObject();
			}
			this.ejecutar(instruccion,valor);
			
		}while(instruccion.codigoOperacion == TokenMaquina.STOP);
	}
	
	public void ejecutar(Operaciones instruccion,Operandos valor) throws Exception{
	    try{	    	
	    	OperandoNum direccion;
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
	    			this.memoria.add((Integer)direccion.dameValor(),this.pila.pop());
	    			break;
	    		case TokenMaquina.NEGATIVO:
	    			if(this.pila.lastElement().dameTipo() == Operandos.NUM){
	    				OperandoNum temp = new OperandoNum(0 - (Integer) this.pila.pop().dameValor());
	    				this.pila.push(temp);
	    			}
	    			if(((Operandos) (this.pila)).dameTipo() == Operandos.NUMREAL){    				    			
	    				
	    			}
	    			if(((Operandos) (this.pila[this.topePila])).dameTipo() == Operandos.NUMREAL){
	    				
	    			}
	    			break;
	    		case TokenMaquina.SUMA:
	    			this.pila[this.topePila - 1]+= this.pila[this.topePila];
	    			this.topePila--;
	    			break;
	    		case TokenMaquina.RESTA:
	    			this.pila[this.topePila - 1]-= this.pila[this.topePila];
	    			this.topePila--;
	    			break;
	    		case TokenMaquina.MULTIPLICACION:
	    			this.pila[this.topePila - 1]*= this.pila[this.topePila];
	    			this.topePila--;
	    			break;
	    		case TokenMaquina.DIVISION:
	    			this.pila[this.topePila - 1]/= this.pila[this.topePila];
	    			this.topePila--;
	    			break;
	    		case TokenMaquina.DIV:
	    			this.pila[this.topePila - 1]= this.pila[this.topePila - 1] / this.pila[this.topePila];
	    			this.topePila--;
	    			break;
	    		case TokenMaquina.MOD:
	    			this.pila[this.topePila - 1] = this.pila[this.topePila - 1] mod this.pila[this.topePila];
	    			this.topePila--;
	    			break;
	    		case TokenMaquina.MAYOR:
	    			this.pila[]
	    			break;
	    		case TokenMaquina.MENOR:
	    			
	    			break;
	    		case TokenMaquina.IGUAL:
	    			
	    			break;
	    		case TokenMaquina.MAYOR_IGUAL:
	    			
	    			break;
	    		case TokenMaquina.MENOR_IGUAL:
	    			
	    			break;
	    		case TokenMaquina.DISTINTO:
	    			
	    			break;
	    		case TokenMaquina.OR:
	    			
	    			break;
	    		case TokenMaquina.AND:
	    			
	    			break;
	    		case TokenMaquina.NOT:
	    			
	    			break;
	    	}
		}catch (IndexOutOfBoundsException e){
	        throw new ExcepcionMaquina(2,i);
	    }		
	}

	@Override
	public boolean extensionArchivoValida(String file){
		return file.endsWith(".em");
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

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		
	}
	
}
