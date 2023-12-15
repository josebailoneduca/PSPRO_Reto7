package reto7_7.b;

import java.util.Random;

public class A extends Thread{
	int id;
	Recurso r;
	
	public A(int id, Recurso r) {
		this.id = id;
		this.r = r;
	}

	@Override
	public void run() {
		while(true) {
			Estadistica.setEstadoA(id, Estadistica.ESPERANDO);
			r.usarA();
			Estadistica.setEstadoA(id, Estadistica.TRABAJANDO);
			espera();
			r.dejarDeUsarA();
 
		}
	}

	private void espera() {
		try {
			sleep(new Random().nextLong(Config.DURACION_MIN_TRABAJO,Config.DURACION_MAX_TRABAJO));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
