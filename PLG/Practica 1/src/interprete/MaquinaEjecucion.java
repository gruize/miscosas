/**
 * 
 */
package interprete;

import java.io.IOException;

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
    
    /** Instrucciones */
    private Instrucciones[] instrucciones;
    
    /** Para mensajes de debug */
    public static boolean debug = false;
        
    /** 
     * Constructor
     * Carga los parametros de configuracion 
     */
	public MaquinaEjecucion() {
        String prop = System.getProperty("debug");        
        if(prop!=null)                      
            debug = prop.equalsIgnoreCase("on");                        
	}

	public MaquinaEjecucion(String sourceFile)throws IOException{
		this.setArchivoLectura(sourceFile);
		
	}

	@Override
	public void finish() {
		
	}

	public void leer(Instrucciones instruccion) throws Exception{
		
	}
	
	public void ejecutar(Instrucciones instruccion) throws Exception{
	    try{
	    	switch(instruccion.codigo){	    	
	    		
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
