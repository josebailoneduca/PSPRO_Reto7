package reto7_8b.b;


import java.util.concurrent.Semaphore;

public class Mesa {
	
	private Ingrediente ing1;
	private Ingrediente ing2;
	Semaphore sProveedor =new Semaphore(1);
	Semaphore sIngredientes =new Semaphore(0);
	public  void poner (Ingrediente ing1,Ingrediente ing2) {
		try {
			sProveedor.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.ing1=ing1;
		this.ing2=ing2;
		Estadistica.ponerIngredientes(ing1, ing2);
		sIngredientes.release();
	}
	
	public  boolean coger(Ingrediente falta) {
		try {
			sIngredientes.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (ing1==null || ing2==null) {
			sIngredientes.release();
			return false;
		}else if (ing1!=falta && ing2!=falta) {
			ing1=ing2=null;
			sProveedor.release();
			return true;
		}else {
			sIngredientes.release();
			return false;
		}
	}

	 
	
}
