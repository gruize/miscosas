package tablaSimbolos;

import java.util.*;


public class TablaSimbolo {
    
  public Hashtable<String, DatosTabla> ht;
  public int dirAct;

 
  public void creaTS()
	{
		ht= new Hashtable<String, DatosTabla>();
		dirAct=0;
	}
  
  public void añadeID (String id, Tipo tipo, boolean modificable)
	{
	  	
		DatosTabla datos = new DatosTabla();
		datos.modificable = modificable;
		datos.tipo = tipo;
		datos.dir=dirAct;
		ht.put(id, datos);
		dirAct++;
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
  
  public boolean existeID(String ID){
	  return ht.containsKey(ID);
  }

}