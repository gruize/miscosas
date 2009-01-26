package traductor;

import interprete.MaquinaEjecucion;
import interprete.OperandoNum;
import interprete.OperandoValorBoolean;
import interprete.Operandos;
import interprete.TokenMaquina;

import java.io.IOException;
import java.util.Vector;

import utilidades.Operaciones;

public class Main {

	/**
	 * 
	 * @param args
	 * @throws Exception 
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception{
		Vector<Object> obj=new Vector<Object>();
		Object e=new Operaciones(TokenMaquina.APILA);
		Object c=new OperandoNum(3);
		//Object c = new OperandoValorBoolean(false);
		Object f=new Operaciones(TokenMaquina.APILA);
		Object h = new OperandoNum(4);
		//Object h = new OperandoValorBoolean(true);
		Object s = new Operaciones(TokenMaquina.SUMA);
		Object ap = new Operaciones(TokenMaquina.DESAPILA_DIR);
		Object dir = new OperandoNum(0);
		Object escribe = new Operaciones(TokenMaquina.ESCRITURA);
		Object dir2 = new OperandoNum(0);
		//Object lee = new Operaciones(TokenMaquina.LECTURA);
		//Object dir3 = new OperandoNum(0);
		Object fin = new Operaciones(TokenMaquina.STOP);

		obj.add(e);
		obj.add(c);
		obj.add(f);
		obj.add(h);
		obj.add(s);
		obj.add(ap);
		obj.add(dir);
		obj.add(escribe);
		obj.add(dir2);
		//obj.add(lee);
		//obj.add(dir3);		
		obj.add(fin);
		
		traductor t = new traductor(obj);
		MaquinaEjecucion me=new MaquinaEjecucion(t.dameNombre());                       
	    me.run();
	}

}
