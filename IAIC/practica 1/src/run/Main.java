package run;

import java.util.Random;

import connector.Conector;
import IA.Goals;
import IA.PlanetSuccesor;
import IA.costeSucesor;
import aima.probability.Randomizer;
import aima.search.framework.GoalTest;
import aima.search.framework.HeuristicFunction;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.StepCostFunction;
import aima.search.framework.SuccessorFunction;
import aima.search.framework.TreeSearch;
import aima.search.uninformed.BidirectionalSearch;
import aima.search.uninformed.DepthFirstSearch;
import universo.*;

public class Main {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Conector con = new Conector("planetas.txt");
		if ( con.Conectar().size() == 0 ){
			System.out.println("Algo funciono bien.");
			SuccessorFunction funcionSucesora = new PlanetSuccesor ();
			GoalTest metas = new Goals(con.getNodosH());
			
			StepCostFunction funcionCoste = new costeSucesor ();
			Problem problema = new Problem(con.getOrigen(),funcionSucesora,metas,funcionCoste);
			
			Random nRandom = new Random();
			nRandom.nextInt(8);
			Search busqueda;
			switch (nRandom.nextInt(8)) {
				case 0 :
					// Busqueda informada: RecursiveBestFirstSearch
					break;
				case 1 :
					// Busqueda informada: Scheduler 
					break;
				case 2 :
					// Busqueda informada: GreedyBestFirstEvaluationFunction
					break;
				case 3 :
					// Busqueda informada: SimulatedAnnealingSearch
					break;
				case 4 :
					// Busqueda no informada: BidirectionalSearch
					busqueda = new BidirectionalSearch();
					
					break;
				case 5 :
					// Busqueda no informada: DepthFirstSearch
					break;
				case 6 :
					// Busqueda no informada: DepthLimitedSearch
					break;
				case 7 :
					// Busqueda no informada: UniformCostSearch
					break;
			}
			busqueda = new DepthFirstSearch(new TreeSearch());
			
			busqueda.search(problema);
			
		}else{
			System.out.print("Algo fallo.");
		}
		
	}


}
