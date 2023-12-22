package reto7_05.a;

import java.util.Random;

/**
 * Hilo 1 ejecuta en bucle los pasos a y b. Cuando ejecuta el paso A libera el semaforo que permite la ejecucion de D
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class H1 extends Thread {

	@Override
	public void run() {
		while (true) {
			a();
			b();
			try {
				sleep(new Random().nextInt(Config.ESPERA_MIN_1,Config.ESPERA_MAX_1));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Representacion de a. Libera el semaforo de control de D
	 */
	private void a() {
		Estadistica.a++;
		Control.sD.release();
	}

	/**
	 * Representacion de b
	 */
	private void b() {
		Estadistica.b++;
	}

}
