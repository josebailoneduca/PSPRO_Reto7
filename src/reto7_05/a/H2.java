package reto7_05.a;

import java.util.Random;


/**
 * Hilo 2 ejecuta en bucle los pasos c y d. Cuando ejecuta el paso D debe esperar al semaforo
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class H2 extends Thread {

	@Override
	public void run() {
		while (true) {
			c();
			d();
			try {
				sleep(new Random().nextInt(Config.ESPERA_MIN_2,Config.ESPERA_MAX_2));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * Representacion de c.
	 */
	private void c() {
		Estadistica.c++;
	}

	/**
	 * Representacion de a. Debe esperar al semaforo de control de D
	 */
	private void d() {
		try {
			Control.sD.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Estadistica.d++;
	}

}
