package tablaSimbolos;

import java.util.*;


public class TablaSimbolo {
    
  public Hashtable<String, DatosTabla> ht;

 
  public TablaSimbolo creaTS()
	{
		ht= new Hashtable<String, DatosTabla>();
		return this;
	}
  
  public TablaSimbolo añadeID (String id, DatosTabla datos)
	{
		ht.put(id, datos);
		return this;
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