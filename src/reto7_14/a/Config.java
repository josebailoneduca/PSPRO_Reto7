package reto7_14.a;


/**
 * Parametros de configuracion
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Config {
	/**
	 * Total de plazas en el aparcamiento
	 */
	public static final int PLAZAS_TOTALES = 5;
	
	/**
	 * Tiempo minimo aparcado
	 */
	public static final int T_MIN_APARCADO = 10000;
	
	/**
	 * Tiempo maximo aparcado
	 */
	public static final int T_MAX_APARCADO = 20000;
	
	
	/**
	 * Tiempo minimo empleado en generar un coche
	 */
	public static final int T_MIN_CREAR_COCHE = 2000;
	
	/**
	 * Tiempo maximo empleado en generar un coche
	 */
	public static final int T_MAX_CREAR_COCHE = 6000;
	
	/**
	 * Cantidad de coches creados para que el generador de coches cambie la velocidad de creacion
	 */
	public static final int CAMBIO_VELOCIDAD_CADA = 20;
	
	/**
	 * Porcentaje de residentes respecto al total de coches generados (0-100)
	 */
	public static final int PORCENTAJE_RESIDENTES = 30;
}
