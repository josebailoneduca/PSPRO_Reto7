package reto7_5.a;

import java.util.Random;

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

	private void c() {
		Estadistica.c++;
	}

	private void d() {
		try {
			Control.sD.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Estadistica.d++;
	}

}
