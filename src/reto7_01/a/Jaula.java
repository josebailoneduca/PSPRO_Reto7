package reto7_01.a;

import java.util.concurrent.Semaphore;

/**
 * Esta clase representa la jaula, contiene los accesos a la comida y  
 * al columpio representados por un semaforo
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Jaula {
	public static Semaphore comida = new Semaphore(Config.PLAZAS_COMIDA);
	public static Semaphore columpio = new Semaphore(Config.PLAZAS_COLUMPIO);
}
