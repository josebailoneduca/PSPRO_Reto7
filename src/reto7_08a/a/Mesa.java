package reto7_08a.a;

public class Mesa {
	
	private Ingrediente ing1;
	private Ingrediente ing2;
	
	public synchronized void poner (Ingrediente ing1,Ingrediente ing2) {
		this.ing1=ing1;
		this.ing2=ing2;
		Estadistica.ponerIngredientes(ing1, ing2);
		notifyAll();
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized boolean coger(Ingrediente falta) {
		if (ing1==null || ing2==null)
			return false;
		if (ing1!=falta && ing2!=falta) {
			ing1=ing2=null;
			return true;
		}else {
			try {wait();} catch (InterruptedException e) {}
			return false;
		}
	}

	public synchronized void avisar() {
		notifyAll();
	}
	
}
