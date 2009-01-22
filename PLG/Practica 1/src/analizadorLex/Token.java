package analizadorLex;
import java.util.Hashtable;



public class Token {
	public final int ID =1;
	public final int NUM =2;
	public final int VALORCHAR =3;
	public final int NUMREAL =4;
	public final int TRUE =5;
	public final int FALSE =7;


	public static Hashtable <String,String> lexemas = new Hashtable ();

}
