package reto7_05.b;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clase que contiene el sistema de control
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Control {
	/**
	 * Representa el numero de veces que D tiene permitido ejecutarse en este momento
	 */
	public static AtomicInteger permiso=new AtomicInteger(0);
}
