package reto7_01.b;


/**
 * Esta clase representa la jaula, contiene los accesos a la comida y  
 * al columpio representados por dos monitores
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Jaula {
	/**
	 * Monitor de acceso a la comida
	 */
	public static Comida comida = new Comida();
	
	/**
	 * Monitor de acceso al columpio
	 */
	public static Columpio columpio = new Columpio();

}
