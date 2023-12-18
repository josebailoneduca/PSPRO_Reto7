package reto7_02a.b;


/**
 * Elementos de configuracion
 *  
 * @author Jose Javier Bailon Ortiz
 */
public class Config {
	
	
	/**
	 * Longitud del buffer Bandeja
	 */
	public static int TAMANO_BANDEJA=4;
	
	/**
	 * Cantidad de hilos repartidores
	 */
	public static int N_REPARTIDORES=4;
	
	/**
	 * Cantidad de hilos cocineros
	 */
	public static int N_COCINEROS=4;
	
	/**
	 * Tiempo minimo de creacion de un pedido
	 */
	public static final int MIN_CREA_PEDIDO=800;
	
	/**
	 * Tiempo maximo de creacion de un pedido
	 */
	public static final int MAX_CREA_PEDIDO=1500;
	
	/**
	 * Tiempo minimo de cocinado de pizza
	 */
	public static final int MIN_COCINAR=2000;
	
	/**
	 * Tiempo maximo de cocinado de pizza
	 */
	public static final int MAX_COCINAR=4000;
	
	/**
	 * Tiempo minimo empleado para repartir
	 */
	public static final int MIN_REPARTIR=2000;
	
	/**
	 * Tiempo maximo empleado para repartir
	 */
	public static final int MAX_REPARTIR=4000;
	

	
	
	
}
