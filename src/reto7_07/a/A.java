package reto7_07.a;

import java.util.Random;


/**
 * Hilo de clase A. Simula en un bucle infinito acceder al recurso y descansar.
 * Informa a estadistica de su estado.
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class A extends Thread{
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
	 * @param r recurso al que acceder
	 */
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
			espera(new Random().nextLong(Config.DURACION_MIN_TRABAJO,Config.DURACION_MAX_TRABAJO));
			r.dejarDeUsarA();
			Estadistica.setEstadoA(id, Estadistica.DESCANSANDO);
			espera(new Random().nextLong(Config.DURACION_MIN_DESCANSO,Config.DURACION_MAX_DESCANSO));

 
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
