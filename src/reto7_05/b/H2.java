package reto7_05.b;

import java.util.Random;

/**
 * Hilo 2 ejecuta en bucle los pasos c y d. Cuando ejecuta el paso D debe esperar a que el permiso de ejecucion de D sea positivo. 
 * Al final del paso D decrementa la cantidad de permisos de ejecucion disponibles. 
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
	 * Representacion de a. Debe esperar a que haya permisos disponibles para D.
	 * Al final del metodo decrementa la cantidad de permisos disponibles
	 */
	private void d() {
		while (Control.permiso.get()<1) {
			try {
				sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
			
 
		Control.permiso.decrementAndGet();
		Estadistica.d++;
	}

}
