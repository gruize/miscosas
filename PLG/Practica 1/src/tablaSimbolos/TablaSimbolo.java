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
  
  public void a�adeID (String id, int tipo_de_DEC, boolean modificable)
	{
	  	
		DatosTabla datos = new DatosTabla();
		datos.modificable = modificable;
		datos.tipo = tipo_de_DEC;
		datos.dir=dirAct;
		ht.put(id, datos);
		dirAct++;
	}
  
  public int dameDir(String id)  {
	  DatosTabla datos=(DatosTabla) ht.get(id);
	  return datos.dameDir();
	  
	  
  }
  
  public int dameTipo(String id){
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