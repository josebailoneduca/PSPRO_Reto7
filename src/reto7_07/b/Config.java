package reto7_07.b;

/**
 * Parametros de configuracion
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Config {
	/**
	 * Numero maximo de hilos que puede haber en el recurso
	 */
	public static int N_MAX_EN_RECURSO=10;
	
	/**
	 * Numero de hilos de tipo A a crear
	 */
	public static int N_A=10;
	
	/**
	 * Numero de hilos de tipo B a crear
	 */
	public static int N_B=10;
	
	/**
	 * Duracion minima de trabajo
	 */
	public static int DURACION_MIN_TRABAJO=1000;
	
	/**
	 * Duracion maxima de trabajo
	 */
	public static int DURACION_MAX_TRABAJO=3000;
	
	/**
	 * Duracion minima de descanso
	 */
	public static int DURACION_MIN_DESCANSO=1000;
	
	/**
	 * Duracion maxima de descanso
	 */
	public static int DURACION_MAX_DESCANSO=8000;
}
