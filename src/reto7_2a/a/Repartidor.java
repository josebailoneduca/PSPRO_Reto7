package reto7_2a.a; 

public class Repartidor implements Runnable {
	Bandeja bandeja;
	public Repartidor(Bandeja bandeja) {
		this.bandeja = bandeja;
	}
	@Override
	public void run() {
		bandeja.cogerPizza();
		repartirPizza();
		
	}
	private void repartirPizza() {
		// TODO Auto-generated method stub
		
	}

}
