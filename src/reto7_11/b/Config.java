package reto7_11.b;

/**
 * Parametros de configuracion
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Config {
	
	/**
	 * Capacidad maxima del canal
	 */
	public static int CAPACIDAD_MAXIMA_CANAL=4;
	
	/**
	 * Tiempo empleado en transitar el canal
	 */
	public static int T_TRANSITO_CANAL=7000;

	/**
	 * Tiempo empleado en la maniobra de entrada
	 */
	public static int T_MANIOBRA_ENTRADA=500;
	/**
	 * Tiempo minimpo para la generacion de cada barco
	 */
	public static int T_MIN_CREAR_BARCO=1000;
	/**
	 * Tiempo maximo para la generacion de cada barco
	 */
	public static int T_MAX_CREAR_BARCO=3000;
	
	/**
	 * Cantidad de barcos tras los cuales la velocidad de creacion cambia
	 */
	public static int CAMBIO_VELOCIDAD_CADA=10;
}
