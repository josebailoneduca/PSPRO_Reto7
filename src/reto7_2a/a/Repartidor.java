package reto7_2a.a;

import java.util.Random;

public class Repartidor implements Runnable {
	
	private int id;
	public Repartidor(int id) {
		this.id=id;
		}
	@Override
	public void run() {
		while (true) {
			repartirPizza(Bandeja.cogerPizza());
		}
		
	}
	private void repartirPizza(Pizza pizza) {
		try {
			Thread.currentThread().sleep(new Random().nextInt(Config.MIN_COCINAR,Config.MAX_COCINAR));
		} catch (InterruptedException e) {}
	}

}
