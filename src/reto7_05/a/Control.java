package reto7_05.a;

import java.util.concurrent.Semaphore;

/**
 * Clase que contiene el sistema de control
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Control {
	/**
	 * Semaforo que permite ejecuciones de D
	 */
	public static Semaphore sD=new Semaphore(0);
}
