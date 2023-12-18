package reto7_02a.b;

/**
 * Imprime estadisticas en pantalla sobre la actividad de los filosofos y la pertenencia de los tenedores
 *  
 * @author Jose Javier Bailon Ortiz
 */
public class Estadistica {
	/**
	 * Estado esperando pedido
	 */
	public static final int ESP_COGE_PEDIDO=0;
	
	/**
	 * Estado cocinando
	 */
	public static final int COCINANDO=1;
	
	/**
	 * Estado esperando dejar pizza en bandeja
	 */
	public static final int ESP_DEJA_PIZZA=2;
	
	/**
	 * Estado esperando a coger pizza de bandeja
	 */
	public static final int ESP_COGE_PIZZA=3;
	
	/**
	 * Estado repartiendo
	 */
	public static final int REPARTIENDO=4;
	
	/**
	 * Lista de pedidos en cola
	 */
	private static  String pedidos="";
	
	/**
	 * Lista de pizzas en bandeja
	 */
	private static  String bandeja="";
	
	/**
	 * Estado de los cocineros
	 */
	private static String[] estadoCocineros=new String[Config.N_COCINEROS];
	
	/**
	 * Estado de los repartidores
	 */
	private static String[] estadoRepartidores=new String[Config.N_REPARTIDORES];
	
	/**
	 * etiquetas de los estados
	 */
	private static final String[] etiquetas= {"esp. coge pedido","cocinando","esp. dejar pizza","esp. coger pizza","repartiendo"};
	

	



	/**
	 * Define el estado de un cocinero
	 * 
	 * @param estado El estado
	 * @param msg Mensaje asociado al  estado
	 */
	public static void setEstadoCocinero (int estado,String msg) {
		estadoCocineros[((Cocinero)Thread.currentThread()).getIndice()]=""+etiquetas[estado]+" "+msg;
		imprimirEstadistica();
	}
	
	/**
	 * Define el estado de un repartidor
	 * @param estado El estado
	 * @param msg Mensaje asociado al  estado
	 */
	public static void setEstadoRepartidores (int estado,String msg) {
		estadoRepartidores[((Repartidor)Thread.currentThread()).getIndice()]=" "+etiquetas[estado]+" "+msg;
		imprimirEstadistica();
	}
	
	/**
	 * Define la lista de pedidos pendientes
	 * 
	 * @param p String con la lista de pedidos
	 */
	public static void setPedidos(String p) {
		pedidos=p;
		imprimirEstadistica();
	}
	
	/**
	 * Define la lista de pizzas en la bandeja
	 * 
	 * @param b String con la lista de pizzas en bandeja
	 */
	public static void setBandeja(String b) {
		bandeja=b;
	}
 

	
	/**
	 * Impime la estadistica segun los datos actuales
	 */
	synchronized public static void imprimirEstadistica() {
		System.out.println("\n\n\n\n\n\nRETO 7 2A B\n######################################################################");
		System.out.println("Pedidos: "+pedidos);
		System.out.println();
		System.out.println("COCINEROS:");
		for (int i = 0; i < estadoCocineros.length; i++) {
			System.out.println("Cocinero "+i+": "+ estadoCocineros[i]);
		}
		System.out.println();
		System.out.println("Bandeja: "+bandeja);
		System.out.println();
		System.out.println("REPARTIDORES:");
		for (int i = 0; i < estadoRepartidores.length; i++) {
			System.out.println("Repartidor "+i+": "+estadoRepartidores[i]);
		}
		
	}
	
	
}
