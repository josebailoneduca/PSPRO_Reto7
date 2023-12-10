package reto7_5.b;

import java.util.Random;

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

	private void a() {
		Estadistica.a++;
		Control.permiso.incrementAndGet();
	}

	private void b() {
		Estadistica.b++;
	}

}
