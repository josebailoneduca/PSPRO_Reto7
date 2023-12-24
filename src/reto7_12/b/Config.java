package reto7_12.b;

/**
 * Parametros de configuracion
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Config {
	
	/**
	 * Cantidad de consumidores
	 */
	public static final int N_CONSUMIDORES=5;
	
	/**
	 * Capacidad del buffer
	 */
	public static final int TAM_BUFFER=10;
	
	/**
	 * Cantidad de elementos generados necesaria para producir  un 
	 * cambio de velocidad en la generacion de elementos 
	 */
	public static final int CAMBIO_VELOCIDAD_CADA = 20;
	
	/**
	 * Velocidad de generacion rapida
	 */
	public static final int T_RAPIDO_GENERAR = 1000;
	
	/**
	 * Velocidad de generacion lenta
	 */
	public static final int T_LENTO_GENERAR = 5000;
	
	/**
	 * Minimo tiempo empleado para la lectura
	 */
	public static final int T_RAPIDO_LEER = 1000;
	
	/**
	 * Maximo tiempo empleado para la lectura
	 */
	public static final int T_LENTO_LEER = 3000;
}