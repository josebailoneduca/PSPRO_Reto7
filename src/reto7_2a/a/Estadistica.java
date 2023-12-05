package reto7_2a.a;

import java.util.Iterator;


/**
 * Imprime estadisticas en pantalla sobre la actividad de los filosofos y la pertenencia de los tenedores
 */
public class Estadistica {
	public static final int ESP_COGE_PEDIDO=0;
	public static final int COCINANDO=1;
	public static final int ESP_DEJA_PIZZA=2;
	public static final int ESP_COGE_PIZZA=3;
	public static final int REPARTIENDO=4;
	
	public static  String pedidos="";
	public static  String bandeja="";
	public static int[] estadoCocineros=new int[Config.N_COCINEROS];
	public static int[] estadoRepartidores=new int[Config.N_REPARTIDORES];
	
	/**
	 * etiquetas de los estados
	 */
	private static final String[] etiquetas= {"esp. coge pedido","cocinando","esp. dejar pizza","esp. coger pizza","repartiendo"};
	


	
 


	
	public static void setEstadoCocinero (int estado) {
		estadoCocineros[((Cocinero)Thread.currentThread()).getIndice()]=estado;
		imprimirEstadistica();
	}
	public static void setEstadoRepartidores (int estado) {
		estadoRepartidores[((Repartidor)Thread.currentThread()).getIndice()]=estado;
		imprimirEstadistica();
	}
	
	public static void setPedidos(String p) {
		pedidos=p;
		imprimirEstadistica();
	}
	public static void setBandeja(String b) {
		bandeja=b;
		
	}
 
 

  
	
	
	/**
	 * Impime la estadistica segun los datos actuales
	 */
	synchronized public static void imprimirEstadistica() {
		System.out.println("\n\n\n\n\n\nRETO 7 2A A\n######################################################################");
		System.out.println("Pedidos: "+pedidos);
		System.out.println("Bandeja: "+bandeja);
		System.out.println("COCINEROS:");
		for (int i = 0; i < estadoCocineros.length; i++) {
			System.out.println("Cocinero "+i+": "+etiquetas[estadoCocineros[i]]);
		}
		System.out.println("REPARTIDORES:");
		for (int i = 0; i < estadoRepartidores.length; i++) {
			System.out.println("Repartidor "+i+": "+etiquetas[estadoRepartidores[i]]);
		}
		
	}
	
	
}
