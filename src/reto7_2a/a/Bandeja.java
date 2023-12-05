package reto7_2a.a;

import java.util.concurrent.Semaphore;

public class Bandeja {

	private static Pizza[] pizzas = new Pizza[Config.TAMANO_BANDEJA];
	private static int entrada = 0;
	private static int salida = 0;
	private static Semaphore sExclusion = new Semaphore(1);
	private static Semaphore sElementos = new Semaphore(0);
	private static Semaphore sHuecos = new Semaphore(Config.TAMANO_BANDEJA);

	public static void ponerPizza(Pizza pizza) {
		try {
			sHuecos.acquire();
			sExclusion.acquire();
			pizzas[entrada] = pizza;
			entrada = (entrada + 1) % Config.TAMANO_BANDEJA;
			sExclusion.release();
			sElementos.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static Pizza cogerPizza() {
		Pizza p = null;
		try {
			sElementos.acquire();
			sExclusion.acquire();
			p = pizzas[salida];
			sExclusion.release();
			sHuecos.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return p;
	}

}
