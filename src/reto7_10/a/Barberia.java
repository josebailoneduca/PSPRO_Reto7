package reto7_10.a;

import java.util.concurrent.Semaphore;

/**
 * Control de la barberia
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Barberia {
	/**
	 * Semaforo de sillas libres
	 */
	public static Semaphore sillasLibres= new Semaphore(Config.N_PLAZAS);
	
	/**
	 * Semaforo de barbero libre
	 */
	public static Semaphore barberoLibre= new Semaphore(0);
	
	/**
	 * Semaforo de clientes en espera
	 */
	public static Semaphore clientes=new Semaphore(0);
}
