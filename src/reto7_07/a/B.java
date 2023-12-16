package reto7_07.a;

import java.util.Random;

public class B extends Thread{
	int id;
	Recurso r;
	
	public B(int id, Recurso r) {
		this.id = id;
		this.r = r;
	}

	@Override
	public void run() {
		while(true) {
			Estadistica.setEstadoB(id, Estadistica.ESPERANDO);
			r.usarB();
			Estadistica.setEstadoB(id, Estadistica.TRABAJANDO);
			try {
				sleep(new Random().nextLong(Config.DURACION_MIN_TRABAJO,Config.DURACION_MAX_TRABAJO));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			r.dejarDeUsarB();
 
		}
	}

}
