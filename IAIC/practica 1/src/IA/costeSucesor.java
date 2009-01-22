package IA;

import universo.Nodo;

import aima.search.framework.StepCostFunction;

public class costeSucesor implements StepCostFunction {

	@Override
	public Double calculateStepCost(Object fromState, Object toState,
			String action) {
		Nodo origen = (Nodo) fromState;
		Nodo destino = (Nodo) toState;
		
		return origen.getEnlaces().get(destino.getId()).getDistancia().doubleValue();
		
	}

}
