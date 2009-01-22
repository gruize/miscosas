package universo;


import java.util.Hashtable;

public class Universo {

	public static Hashtable<Integer, Nodo> planetas;
	
	
	public Universo() {
		
	}
	public Universo(Hashtable<Integer, Nodo> planetas) {
		super();
		Universo.planetas = planetas;
		
	}
	
}