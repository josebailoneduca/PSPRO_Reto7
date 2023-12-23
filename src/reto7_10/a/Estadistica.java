package reto7_10.a;


import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Muestra el estado del sistema
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Estadistica {
	
	/**
	 * Estado de durmiendo
	 */
	public static final int DURMIENDO=0;
	
	/**
	 * Estado de trabajando
	 */
	public static final int TRABAJANDO=1;

	/**
	 * Etiquetas de los estados
	 */
	private static final String[] etiquetas = {"Durmiendo","Trabajando"};
	
	/**
	 * Velocidad actual de generacion de clientes
	 */
	private static int velocidadGeneracionClientes=Config.T_RAPIDO_NUEVO_CLIENTE;
	
	/**
	 * Lista de la sala de espera
	 */
	private static LinkedList<Cliente> esperando = new LinkedList<>();
	
	/**
	 * Cliente sobre el que se esta trabajando
	 */
	private static Cliente afeitandose;
	
	/**
	 * Estado actual del barbero
	 */
	private static int estadoBarbero=0;
	
	
	/**
	 * Registrar cliente como esperando
	 */
	public static synchronized void esperar() {
			esperando.add((Cliente) Thread.currentThread());
			mostrarEstadistica();
	}
	
	/**
	 * Quitar un cliente del registro de esperando
	 */
	public static synchronized void dejarDeEsperar() {
		esperando.remove((Cliente) Thread.currentThread());
		afeitandose=(Cliente) Thread.currentThread();
		mostrarEstadistica();
	}
	
	/**
	 * Registrar un cliente como que desiste por no haber espacio
	 */
	public static synchronized void desistir() {
		System.out.println("El cliente "+Thread.currentThread()+" se va porque no hay sitio");
	}
	
	/**
	 * Define el estado del barbero
	 * @param e El estado
	 */
	public static synchronized void setEstadoBarbero(int e) {
		if (e==DURMIENDO)
			afeitandose=null;
		estadoBarbero=e;
		mostrarEstadistica();
	}
	
	/**
	 * Informa de la velocidad actual de generacion de clientes
	 * 
	 * @param tiempo Tiempo de generacion
	 */
	public synchronized static void setVelocidadClientes(int tiempo) {
		velocidadGeneracionClientes=tiempo;
		mostrarEstadistica();
	}

	
	/**
	 * Muestra el estado de la barberia, barbero y clientes
	 */
	private static void mostrarEstadistica() {
		System.out.println("\n".repeat(30));
		System.out.println("RETO 7 10 A\n"+("-".repeat(20)));
		System.out.println("Llega un nuevo cliente cada "+velocidadGeneracionClientes+" ms");
		System.out.println("Sala de espera(max:"+Config.N_PLAZAS+"): "+esperando.stream().map(Object::toString).collect(Collectors.joining(", ")));
		System.out.println("Barbero: "+etiquetas[estadoBarbero]);
		System.out.println( "Afetiandose: "+((afeitandose!=null)?afeitandose:"nadie"));
	}
}


