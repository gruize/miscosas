package IA;

import java.util.Hashtable;

import universo.Nodo;



import aima.search.framework.GoalTest;


public class Goals implements GoalTest{
	Hashtable<Integer, Nodo> Metas;
	
	
	public Goals(Hashtable<Integer, Nodo> ht){
		this.Metas = (Hashtable<Integer, Nodo>) ht.clone();
	}
	
	@Override
	public boolean isGoalState(Object state) {
		if ( this.Metas.get(((Nodo)state).getId()) == null)
			return true;
		// TODO Auto-generated method stub
		return false;
	}

}
