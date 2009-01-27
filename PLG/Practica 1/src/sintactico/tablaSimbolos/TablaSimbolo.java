package sintactico.tablaSimbolos;

import java.util.*;


public class TablaSimbolo {
    
  public Hashtable<String, DatosTabla> ht;
  public int dirAct;

  /**
   * crea una tabla de simbolos
   */
  public void creaTS()
	{
		ht= new Hashtable<String, DatosTabla>();
		dirAct=0;
	}
  /**
   * Añade un id a la tabla
   * @param id identificador del elemento
   * @param tipo_de_DEC tipo del identificador
   * @param modificable indica si es variable o constante
   */
  public void anadeID (String id, int tipo_de_DEC, boolean modificable)
	{
	  	
		DatosTabla datos = new DatosTabla();
		datos.modificable = modificable;
		datos.tipo = tipo_de_DEC;
		datos.dir=dirAct;
		ht.put(id, datos);
		dirAct++;
	}
  /**
   * Devuelve la direccion en la que esta almacenada el id
   * @param id 
   * @return direccion donde almacenada id
   */
  public int dameDir(String id)  {
	  DatosTabla datos=(DatosTabla) ht.get(id);
	  return datos.dameDir();
	  
	  
  }
  
  /**
   * Devuelve el tipo de id
   * @param id 
   * @return tipo id
   */
  public int dameTipo(String id){
	  DatosTabla datos=(DatosTabla) ht.get(id);
	  return datos.dameTipo();
	  
  }
  /**
   * Devuelve si el ide es variable o constante id
   * @param id 
   * @return true si variable false si constante
   */
  public boolean dameModificable(String id){
	  DatosTabla datos=(DatosTabla) ht.get(id);
	  return datos.dameModificable();
	  
  }
  /**
   * Devuelve si existe ya el id en la tabla
   * @param id 
   * @return true si existe el id false eoc
   */
  public boolean existeID(String ID){
	  return ht.containsKey(ID);
  }

}