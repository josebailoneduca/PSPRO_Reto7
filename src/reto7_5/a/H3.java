package reto7_5.a;

import java.util.Random;

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

	private void e() {
		Estadistica.e++;
		Control.sD.release();
	}

	private void f() {
		Estadistica.f++;
	}

}
