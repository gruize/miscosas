package traductor;

import interprete.MaquinaEjecucion;
import interprete.OperandoNum;
import interprete.OperandoNumReal;
import interprete.OperandoValorBoolean;
import interprete.OperandoValorChar;
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
		Object lee = new Operaciones(TokenMaquina.LECTURA);
		Object dir3 = new OperandoNum(0);
		Object escribe2 = new Operaciones(TokenMaquina.ESCRITURA);
		Object dir22 = new OperandoNum(0);		
		Object e1=new Operaciones(TokenMaquina.APILA);
		Object c1=new OperandoValorChar('x');
		Object ap1 = new Operaciones(TokenMaquina.DESAPILA_DIR);
		Object dir1 = new OperandoNum(1);
		Object leeChar = new Operaciones(TokenMaquina.LECTURA);
		Object dir12 = new OperandoNum(1);
		Object escribe3 = new Operaciones(TokenMaquina.ESCRITURA);
		Object dir23 = new OperandoNum(1);
		
		Object e11=new Operaciones(TokenMaquina.APILA);
		Object c11=new OperandoNumReal(3.45);
		Object ap11 = new Operaciones(TokenMaquina.DESAPILA_DIR);
		Object dir11 = new OperandoNum(2);
		Object leereal = new Operaciones(TokenMaquina.LECTURA);
		Object dir121 = new OperandoNum(2);
		Object escribe31 = new Operaciones(TokenMaquina.ESCRITURA);
		Object dir231 = new OperandoNum(2);
		
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
		obj.add(lee);
		obj.add(dir3);
		obj.add(escribe2);
		obj.add(dir22);	
		obj.add(e1);
		obj.add(c1);
		obj.add(ap1);
		obj.add(dir1);
		obj.add(leeChar);
		obj.add(dir12);
		obj.add(escribe3);
		obj.add(dir23);	
		obj.add(e11);
		obj.add(c11);
		obj.add(ap11);
		obj.add(dir11);
		obj.add(leereal);
		obj.add(dir121);
		obj.add(escribe31);
		obj.add(dir231);	
		obj.add(fin);
		
		traductor t = new traductor(obj);
		MaquinaEjecucion me=new MaquinaEjecucion(t.dameNombre());                       
	    me.run();
	}

}
