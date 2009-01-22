package universo;

import java.util.Hashtable;


import aima.search.framework.Successor;


public class Nodo {
	private Integer id;
	private String nombre;
	private Integer tipo;
	private Hashtable<Integer, Enlace> enlaces;
	private Hashtable<Integer, Successor> succesores;
	public Nodo (){
		this.enlaces = new Hashtable<Integer, Enlace>();
		this.succesores = new Hashtable<Integer, Successor>();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Hashtable<Integer, Enlace> getEnlaces() {
		return enlaces;
	}
	public void setEnlaces(Hashtable<Integer, Enlace> enlaces) {
		this.enlaces = enlaces;
	}
	public void addEnlaces(Enlace enlacetmp){
		String accion = "Vuelo desde: " + this.nombre + "\thasta: " +Universo.planetas.get(enlacetmp.getDestino());
		Nodo destino = Universo.planetas.get(enlacetmp.getDestino());
		// 
		this.succesores.put(enlacetmp.getDestino(),new Successor(accion,destino));
		this.enlaces.put(enlacetmp.getDestino(),enlacetmp);
	}
	
	public Hashtable<Integer, Successor> getSuccesores(){
		return this.succesores;
		
	}
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
}
