package reto7_01.a;

import java.util.Random;


/**
 * Implementa el comportamiento de un loro.
 * Va adquieriendo el acceso a la comida y al columpio en un bucle infinito
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Loro implements Runnable {

	/**
	 * Identificador del loro
	 */
	private int id;
	
	
	/**
	 * Constructor
	 * @param id Id del loro
	 */
	public Loro(int id) {
		this.id = id;
	}

	/**
	 * Carrera. Bucle infinito comer-columpiarse
	 */
	@Override
	public void run() {
		while(true) {
			Estadistica.setEstado(id, Estadistica.ESPERA_COMER);
			comer();
			Estadistica.setEstado(id, Estadistica.ESPERA_COLUMPIO);
			columpiarse();	
		}
	}

	/**
	 * Define el proceso de comer. Adquiere el semaforo de acceso a la comida. 
	 * Tras obtenerlo se hace una espera y despues se libera la plaza 
	 * de la comida
	 */
	private void comer() {
		try {
			//obetener el acceso a la compida
			long iniT=System.currentTimeMillis();			
			Jaula.comida.acquire();
			Estadistica.comer(id, System.currentTimeMillis()-iniT);
			//espera simulando comer
			Thread.sleep(new Random().nextLong(Config.MIN_COMER,Config.MAX_COMER));
			//liberar plaza de comida
			Jaula.comida.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	/**
	 * Define el proceso de columpiarse. Adquiere el semaforo de acceso al columpio. 
	 * Tras obtenerlo se hace una espera y despues se libera la plaza 
	 * del columpio
	 */
	private void columpiarse() {
		try {		
			//Conseguir plaza en el columpio
			long iniT=System.currentTimeMillis();
			Jaula.columpio.acquire();
			Estadistica.columpiarse(id, System.currentTimeMillis()-iniT);
			//espera simulando columpiarse
			Thread.sleep(new Random().nextLong(Config.MIN_COLUMPIARSE,Config.MAX_COLUMPIARSE));
			//liberar plaza del columpio
			Jaula.columpio.release();		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
