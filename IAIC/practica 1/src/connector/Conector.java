package connector;

import universo.Tipo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import java.util.Properties;

import universo.Enlace;
import universo.Nodo;
import universo.Universo;

public class Conector {

	/**
	 * @param args
	 * @throws IOException 
	 */
	
	private Hashtable<Integer, Nodo> nodosH;
	private String archivo;
	private Nodo origen;
	private Hashtable<Integer, Nodo> destinos;
	
	public Conector(String archivo){
		this.setArchivo(archivo);
		Universo.planetas = new	Hashtable<Integer, Nodo>();
		origen = null;
		destinos = new Hashtable<Integer, Nodo>();
		//nodosH = new Hashtable<Integer, Nodo>();
		
	}
	public Vector<String> Conectar() throws NumberFormatException, IOException {
		File f = new File(this.archivo);
		FileReader file = new FileReader(f);
	    BufferedReader entrada = new BufferedReader( file );
	    String linea;
	    Enlace enlaceTmp;
	    
	    Vector<String> errores = new Vector<String>();
	    Properties propiedades;
	    Vector<String> lineas = new Vector<String>();
	    while (( linea = entrada.readLine() ) != null){
	    	lineas.add(linea);
	    }
	    
	    // ESTA VARIABLE NOS VALE PARA QUE SI HAY TIPO DE PLANETA 
	    // LA LECTURA DE LOS DESPLAZAMIENTOS UNA POSICION MAS
	    
	    for ( String planeta : lineas){
	    	int i = 0;
	    	String [] campos = planeta.split("\\|");
	    	Nodo nodo = new Nodo();
	    	
	    	
	    	String tipo = campos[i].substring(1, campos[i].length()-1);
	    	if (tipo.length()<2) {
	    		if ( tipo.equals("O")){
	    			nodo.setTipo(Tipo.ORIGEN);
					if ( origen == null )
						origen = nodo;
					else 
						errores.add("Ya hay un planeta origen.");
	    		}
				else if (tipo.equals("D")) {
					nodo.setTipo(Tipo.DESTINO);
				}
				else 
					nodo.setTipo(Tipo.NORMAL);
	    		i++;
	    	}
	    	else
	    		nodo.setTipo(Tipo.NORMAL);
	    	nodo.setNombre(campos[i]);i++;
	    	nodo.setId(Integer.valueOf(campos[i]));i++;
	    	if (nodo.getTipo() == Tipo.DESTINO)
	    		destinos.put(nodo.getId(), nodo);
	    	Universo.planetas.put(nodo.getId(), nodo);
	    		
	    }
	    lineas.firstElement();
	    for ( String planeta : lineas){
	    	
	    	int i = 2;
	    	
	    	String [] campos = planeta.split("\\|");
	    	if (campos[0].length() <= 3)
	    		i++;
	    	// si no contiene la clave es que tiene [o] o [d]
	    	int id_planeta = i-1;
	       	String[] enlaces = campos[i].split("\\]");
			for (String enlace : enlaces) {
				enlace = enlace.substring(1,enlace.length());
				String[] infoEnlace = enlace.split(",");
				enlaceTmp= new Enlace();
				if ( !infoEnlace[0].isEmpty() ){
					enlaceTmp.setDestino(Integer.valueOf(infoEnlace[0]));
				}else{
					enlaceTmp.setDestino(null);
				}
				if ( !infoEnlace[1].isEmpty() ){
					enlaceTmp.setProblema(Integer.valueOf(infoEnlace[1]));
				}else{
					enlaceTmp.setProblema(null);
				}
				if ( !infoEnlace[2].isEmpty() ){
					enlaceTmp.setDistancia(Long.valueOf(infoEnlace[2]));
				}else{
					enlaceTmp.setDistancia(null);
				}
				Universo.planetas.get(id_planeta).addEnlaces(enlaceTmp);
			}
	    }
	    
	    // TODO: Control de errores    
	    //Cargamos el fichero de configuracion
	    try {
	    	FileInputStream prop = new FileInputStream("propiedades.conf");
	  
	        propiedades = new Properties();
	        propiedades.load(prop);
	        prop.close();
	     
	        Integer numplanetas = Integer.valueOf( propiedades.getProperty("numPlanetas"));
	        Integer numdestinos = Integer.valueOf( propiedades.getProperty("numDestinos"));

		    if ( origen == null ){
		    	errores.add("No hay planeta origen.");
		    }
		    if ( destinos.size() < numdestinos ){
		    	errores.add("No hay al menos 4 planetas destino.");
		    }
		    if ( Universo.planetas.size() < numplanetas ) {
		    	errores.add("No hay suficientes planetas.");
		    }
	    
		} catch (Exception e) {
			System.out.print(e);
		}

	    return errores;
	}
	public String getArchivo() {
		return archivo;
	}
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	public Hashtable<Integer, Nodo> getNodosH() {
		return Universo.planetas;
	}
	public void setNodosH(Hashtable<Integer, Nodo> nodosH) {
		Universo.planetas = nodosH;
	}
	public Nodo getOrigen() {
		return origen;
	}
	public void setOrigen(Nodo origen) {
		this.origen = origen;
	}
	public Hashtable<Integer, Nodo> getDestinos() {
		return destinos;
	}
	public void setDestinos(Hashtable<Integer, Nodo> destinos) {
		this.destinos = destinos;
	}	
}