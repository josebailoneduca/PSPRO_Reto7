package reto7_05.b;

import java.util.Random;

/**
 * Hilo 3 ejecuta en bucle los pasos e y f.  Cuando ejecuta el paso E incrementa el valor de permisos disponibles para la ejecucion de D
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
	 * Representacion de e. Incrementa el valor del permiso de control de ejecucion de D
	 */
	private void e() {
		Estadistica.e++;
		Control.permiso.incrementAndGet();
	}

	/**
	 * Representacion de f
	 */
	private void f() {
		Estadistica.f++;
	}

}
