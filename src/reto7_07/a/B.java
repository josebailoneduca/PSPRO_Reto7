package reto7_07.a;

import java.util.Random;


/**
 * Hilo de clase B. Simula en un bucle infinito acceder al recurso y descansar.
 * Informa a estadistica de su estado.
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class B extends Thread {
	
	/**
	 * Id del hilo
	 */
	private int id;
	
	/**
	 * Recurso al que acceder
	 */
	private Recurso r;

	/**
	 * Constructor 
	 * @param id Id del hilo
	 * @param r Recurso al que acceder
	 */
	public B(int id, Recurso r) {
		this.id = id;
		this.r = r;
	}

	@Override
	public void run() {
		while (true) {
			Estadistica.setEstadoB(id, Estadistica.ESPERANDO);
			r.usarB();
			Estadistica.setEstadoB(id, Estadistica.TRABAJANDO);
			espera(new Random().nextLong(Config.DURACION_MIN_TRABAJO, Config.DURACION_MAX_TRABAJO));
			r.dejarDeUsarB();
			Estadistica.setEstadoB(id, Estadistica.DESCANSANDO);
			espera(new Random().nextLong(Config.DURACION_MIN_DESCANSO, Config.DURACION_MAX_DESCANSO));

		}
	}

	/**
	 * Duerme el hilo duranto un tiempo determinado
	 * @param ms El tiempo a dormir
	 */
	private void espera(long ms) {
		try {
			sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
