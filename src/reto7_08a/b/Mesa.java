package reto7_08a.b;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Mesa {
	
	private Ingrediente ing1;
	private Ingrediente ing2;
	CyclicBarrier entrada= new CyclicBarrier(4);
	CyclicBarrier salida= new CyclicBarrier(4);
	public  void poner (Ingrediente ing1,Ingrediente ing2) {
		this.ing1=ing1;
		this.ing2=ing2;
		Estadistica.ponerIngredientes(ing1, ing2);
		
		barreraEntrada();
		barreraSalida();
	}
	
	public  boolean coger(Ingrediente falta) {
		barreraEntrada();
		if (ing1==null || ing2==null) {
			barreraSalida();
			return false;
		}else if (ing1!=falta && ing2!=falta) {
			ing1=ing2=null;
			return true;
		}else {
			barreraSalida();
			return false;
		}
	}

	public  void avisar() {
		barreraSalida();
	}
	
	private void barreraEntrada() {
		try {
			entrada.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
	
	private void barreraSalida() {
		try {
			salida.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
}
