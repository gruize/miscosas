/**
 * 
 */
package interprete;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import utilidades.Operaciones;
import excepciones.ExcepcionMaquina;
import main.Testeable;

/**
 * @author GabiPC
 *
 */
public class MaquinaEjecucion implements Testeable{
            
    /** Tamaño maximo de la Pila */
    private static final int MAX_PILA = 65536;
    
    /** Tamaño maximo del Display */
    private static final int MAX_DISPLAY = 10;
    
    /** Tope de pila */
    private int topePila;
    
    /** Contador de programa */
    private int i;
    
    /** Display */
    private int[] display = null;
    
    /** Pila */
    private int[] pila = null;

    /**Lectura de objetos*/
    private ObjectInputStream entrada;
    
    
    
    /** 
     * Constructor
     * Carga los parametros de configuracion 
     */

	public MaquinaEjecucion(FileInputStream sourceFile)throws Exception{
		this.entrada = new ObjectInputStream(sourceFile);
		this.leerOperaciones();
	}

	@Override
	public void finish() {
		
	}

	public void leerOperaciones() throws Exception{
		Operaciones instruccion = null;
		do{
			instruccion = (Operaciones) this.entrada.readObject();
			this.ejecutar(instruccion);
		}while(this.entrada.read() == -1);
	}
	
	public void ejecutar(Operaciones instruccion) throws Exception{
	    try{
	    	int codigo = instruccion.codigoOperacion;
	    	switch(codigo){
	    		case TokenMaquina.APILA:
	    			this.topePila++;
	    			this.pila[this.topePila] = instruccion.operando;
	    			break;
	    		case TokenMaquina.APILA_DIR:
	    			this.topePila++;
	    			this.pila[this.topePila] ;
	    			break;
	    		case TokenMaquina.DESAPILA_DIR:
	    			this.topePila--;
	    			/**
	    			 * ¿Que se hace con lo que se desapila?
	    			 */
	    			break;
	    		case TokenMaquina.NEGATIVO:
	    			this.pila[this.topePila] = 0 - this.pila[this.topePila];
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
	public void run() throws Exception{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setArchivoLectura(String source) {
		this.display = new int[this.MAX_DISPLAY];
		this.pila = new int[this.MAX_PILA];
		// TODO Auto-generated method stub		
	}
	
	@Override
	public boolean extensionArchivoValida(String file){
		return file.endsWith(".em");
	}
	
}
