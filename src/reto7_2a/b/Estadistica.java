package reto7_2a.b;

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
	public static String[] estadoCocineros=new String[Config.N_COCINEROS];
	public static String[] estadoRepartidores=new String[Config.N_REPARTIDORES];
	
	/**
	 * etiquetas de los estados
	 */
	private static final String[] etiquetas= {"esp. coge pedido","cocinando","esp. dejar pizza","esp. coger pizza","repartiendo"};
	


	
 


	
	public static void setEstadoCocinero (int estado,String msg) {
		estadoCocineros[((Cocinero)Thread.currentThread()).getIndice()]=""+etiquetas[estado]+" "+msg;
		imprimirEstadistica();
	}
	public static void setEstadoRepartidores (int estado,String msg) {
		estadoRepartidores[((Repartidor)Thread.currentThread()).getIndice()]=" "+etiquetas[estado]+" "+msg;
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
