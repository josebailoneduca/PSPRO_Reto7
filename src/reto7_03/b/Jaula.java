package reto7_03.b;


/**
 * Representa la jaula, contiene la comida y el columpio, ambos monitores.
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Jaula {
	
	/**
	 * Monitor que controla el acceso a la comida
	 */
	public static Comida comida = new Comida();
	
	/**
	 * Monitor que controla el acceso al columpio
	 */
	public static Columpio columpio = new Columpio();
}
