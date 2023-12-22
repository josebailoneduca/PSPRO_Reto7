package reto7_02a.b;

import java.util.Random;

/**
 * Representa un repartidor. Tiene una carrera consistente en un bucle infinito
 * de coger una pizza del buffer Bandeja, repartirla y vuelta a coger otra pizza
 * del buffer Bandeja.
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Repartidor extends Thread {

	/**
	 * Id del repartidor
	 */
	private int indice;

	/**
	 * Constructor
	 * 
	 * @param indice El identificador del repartidor
	 */
	public Repartidor(int indice) {
		this.indice = indice;
	}

	@Override
	public void run() {
		while (true) {
			repartirPizza(Bandeja.cogerPizza());
		}

	}

	/**
	 * Simula el reparto de una pizza esperando un tiempo aleatorio
	 * 
	 * @param pizza La pizza a repartir
	 */
	private void repartirPizza(Pizza pizza) {
		Estadistica.setEstadoRepartidores(Estadistica.REPARTIENDO, pizza.toString());
		try {
			Thread.sleep(new Random().nextInt(Config.MIN_REPARTIR, Config.MAX_REPARTIR));
		} catch (InterruptedException e) {
		}
	}

	/**
	 * Devuelve la id del repartidor
	 * 
	 * @return La id del repartidor
	 */
	public int getIndice() {
		return indice;
	}

}
