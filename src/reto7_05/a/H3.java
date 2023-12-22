package reto7_05.a;

import java.util.Random;


/**
 * Hilo 3 ejecuta en bucle los pasos e y f. Cuando ejecuta el paso E libera el semaforo que permite la ejecucion de D
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class H3 extends Thread {

	@Override
	public void run() {
		while (true) {
			e();
			f();
			try {
				sleep(new Random().nextInt(Config.ESPERA_MIN_3,Config.ESPERA_MAX_3));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * Representacion de e. Libera el semaforo de control de D
	 */
	private void e() {
		Estadistica.e++;
		Control.sD.release();
	}

	/**
	 * Representacion de f.
	 */
	private void f() {
		Estadistica.f++;
	}

}
