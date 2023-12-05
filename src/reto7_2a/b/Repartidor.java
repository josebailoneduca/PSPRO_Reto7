package reto7_2a.b;

import java.util.Random;

public class Repartidor extends Thread{
	
	private int indice;
	public Repartidor(int indice) {
		this.indice=indice;
		}
	@Override
	public void run() {
		while (true) {
			repartirPizza(Bandeja.cogerPizza());
		}
		
	}
	private void repartirPizza(Pizza pizza) {
		Estadistica.setEstadoRepartidores(Estadistica.REPARTIENDO,pizza.toString());
		try {
			Thread.currentThread().sleep(new Random().nextInt(Config.MIN_REPARTIR,Config.MAX_REPARTIR));
		} catch (InterruptedException e) {}
	}
	public int getIndice() {
		return indice;
	}

	
}
