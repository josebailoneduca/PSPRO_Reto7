package reto7_03.a;

import java.util.concurrent.Semaphore;


/**
 * Representacion de la jaula. Contiene los semaforos que dan acceso a la comidia y las plazas 
 * del columpio.
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Jaula {
	/**
	 * Semaforo de acceso a la comida
	 */
	public static Semaphore comida = new Semaphore(Config.PLAZAS_COMIDA);
	
	/**
	 * Semaforo de acceso a la plaza para machos del columpio
	 */
	public static Semaphore columpioPlazaMacho = new Semaphore(Config.PLAZAS_COLUMPIO);
	
	/**
	 * Semaforo de acceso a la plaza para hembras del columpio
	 */
	public static Semaphore columpioPlazaHembra = new Semaphore(Config.PLAZAS_COLUMPIO);
}
