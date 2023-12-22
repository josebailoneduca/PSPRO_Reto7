package reto7_01.b;

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
			
			comer();
			
			columpiarse();
			
		}

	}



	/**
	 * Define el proceso de comer. Adquiere plaza en la comida.  
	 * Tras obtenerla se hace una espera y despues se libera la plaza 
	 * de la comida
	 */
	private void comer() {
		try {
			Estadistica.setEstado(id, Estadistica.ESPERA_COMER);
			
			long iniT=System.currentTimeMillis();
			
			Jaula.comida.cogerSitio();
			Estadistica.comer(id, System.currentTimeMillis()-iniT);

			Thread.sleep(new Random().nextLong(Config.MIN_COMER,Config.MAX_COMER));
			
			Estadistica.setEstado(id, Estadistica.ESPERA_COLUMPIO);
			
			Jaula.comida.dejarSitio();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Define el proceso de columpiarse. Adquiere plaza en el columpio. 
	 * Tras obtenerla se hace una espera y despues se libera la plaza 
	 * del columpio
	 */
	private void columpiarse() {
		try {
				
			long iniT=System.currentTimeMillis();
			
			Jaula.columpio.cogerSitio();
				
				Estadistica.columpiarse(id, System.currentTimeMillis()-iniT);
			
			Thread.sleep(new Random().nextLong(Config.MIN_COLUMPIARSE,Config.MAX_COLUMPIARSE));
			
			Estadistica.setEstado(id, Estadistica.ESPERA_COMER);
			Jaula.columpio.dejarSitio();
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
