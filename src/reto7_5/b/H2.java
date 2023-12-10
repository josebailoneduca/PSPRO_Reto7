package reto7_5.b;

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
