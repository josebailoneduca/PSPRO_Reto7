package reto7_04.a;

/**
 * Parametros de configuracion
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Config {
	
	/**
	 * Numero de embotelladores que trabajan simultaneamente
	 */
	public static int N_EMBOTELLADORES=3; 
	/**
	 * Capacidad de las cajas
	 */
	public static int TAMANO_CAJA=10;
	
	/**
	 * Tiempo empleado en crear una botella por parte del embotellador
	 */
	public static int T_CREA_BOTELLA=500;
	
	/**
	 * Tiempo empleado en poner una botella en la caja por parte del embotellador
	 */
	public static int T_PONER_BOTELLA=1000;
	
	/**
	 * Tiempo empleado en quitar la caja por parte del empaquetador
	 */
	public static int T_QUITAR_CAJA=3000;
	
	/**
	 * Tiempo empleado por el empaquetador para poner una caja vacia
	 */
	public static int T_PONER_CAJA=3000;
	
}
