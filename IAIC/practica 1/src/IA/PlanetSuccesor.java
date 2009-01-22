package IA;

import java.util.Enumeration;
import java.util.List;
import java.util.Vector;


import universo.Nodo;
import universo.Universo;



import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

public class PlanetSuccesor implements SuccessorFunction {

	
	
	@Override
	public List<Successor> getSuccessors(Object state) {
		
		//Universo universo = new Universo();
		Vector<Successor> sucesores = new Vector<Successor>();
		/*if (Universo.planetas.get(((Nodo)state).getId())== null)
			System.out.println("cacacab");*/
		Enumeration<Successor> e = Universo.planetas.get(((Nodo)state).getId()).getSuccesores().elements();
		
		for(Successor n = e.nextElement();e.hasMoreElements();n = e.nextElement())
			sucesores.add(n);
		
		return sucesores; 
		
		
		// TODO Auto-generated method stub
		
	}

}
