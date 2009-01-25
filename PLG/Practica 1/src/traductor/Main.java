package traductor;

import java.io.IOException;
import java.util.Vector;


public class Main {

	
	
	
	public static void main(){
		traductor t;
		Vector<Object> obj;
		Object e=3;
		Object b=4;
		obj=new Vector();
		obj.add(e);
		obj.add(b);
		try {
			t=new traductor(obj);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
