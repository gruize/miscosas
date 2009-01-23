package tablaSimbolos;

import java.util.*;


public class TablaSimbolo {
    
  public Hashtable<String, DatosTabla> ht;

 
  public void creaTS()
	{
		ht= new Hashtable<String, DatosTabla>();
	}
  
  public void añadeID (String id, DatosTabla datos)
	{
		ht.put(id, datos);
	}
  
  public int dameDir(String id)  {
	  DatosTabla datos=(DatosTabla) ht.get(id);
	  return datos.dameDir();
	  
	  
  }
  
  public Tipo dameTipo(String id){
	  DatosTabla datos=(DatosTabla) ht.get(id);
	  return datos.dameTipo();
	  
  }
  
  public boolean dameModificable(String id){
	  DatosTabla datos=(DatosTabla) ht.get(id);
	  return datos.dameModificable();
	  
  }

}