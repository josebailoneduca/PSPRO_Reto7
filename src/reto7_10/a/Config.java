package reto7_10.a;

/**
 * Parametros de configuracion
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Config {
	
	/**
	 * Numero de plazas en la sala de espera de la barberia
	 */
	public static int N_PLAZAS=5;
	
	/**
	 * Tiempo que tarda el barbero en pelar
	 */
	public static int T_TRABAJO=2000;
	
	/**
	 * Tiempo de generacion rapida de clientes
	 */
	public static int T_RAPIDO_NUEVO_CLIENTE=500;
	
	/**
	 * Tiempo de generacion lenta de clientes
	 */
	public static int T_LENTO_NUEVO_CLIENTE=4000;
	
	/**
	 * Numero de clientes tras el cuel el generador de clientes cambia de velocidad
	 */
	public static int CAMBIO_VELOCIDAD_CADA=10;
}
