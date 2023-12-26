package reto7_11.b;

 import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Collectors;
 
/**
 * Muestra el estado del sistema en pantalla
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Estadistica {
	
	/**
	 * Lista de barcos en la entrada Este
	 */
	private static LinkedBlockingDeque<Barco> colaEntradaEste=new LinkedBlockingDeque<Barco>();
	
	/**
	 * Lista de barcos en la entrada Oeste
	 */
	private static LinkedBlockingDeque<Barco> colaEntradaOeste=new LinkedBlockingDeque<Barco>();
	
	/**
	 * Lista de barcos en el canal
	 */
	private static LinkedBlockingDeque<Barco> canal=new LinkedBlockingDeque<Barco>();
 
	/**
	 * Direccion actual de uso del canal
	 */
	private static String direccion="Este->Oeste";


	/**
	 * Pone un barco en la cola de visualizacion correspondiente
	 * @param b Barco a mostrar esperando
	 */
	public static void esperando(Barco b) {
		if (b.isDireccionEO())
			colaEntradaEste.add(b);
		else
			colaEntradaOeste.add(b);
		mostrarEstadistica();
	}
 

	/**
	 * Saca un barco de la cola de visualizacion en la que esta y la pone en la lista de barcos en el canal
	 * 
	 * @param b El barco
	 */
	public static void enCanal(Barco b) {
		if (b.isDireccionEO())
			colaEntradaEste.remove(b);
		else
			colaEntradaOeste.remove(b);
		
		canal.add(b);
		mostrarEstadistica();
	}

	
	/**
	 * Saca un barco de la lista de visualizacion del canal
	 * 
	 * @param b El barco
	 */
	public static void salir(Barco b) {
		canal.remove(b);
	}
	/**
	 * Actualiza la direccion de uso del canal. Si son diferents es que se esta vaciando.
	 * 
	 * @param direccionActual Direccion actual
	 * @param proximaDireccion Direccion proxima
	 */
	public static void setDireccion(boolean direccionActual, boolean proximaDireccion) {
		if (direccionActual!=proximaDireccion)
			direccion="Vaciando canal";
		else
			direccion=(direccionActual)?"Este->Oeste":"Oeste->Este";
		
	}

	
	/**
	 * Muestra el estado por pantalla
	 */
	private static void mostrarEstadistica() {
 		System.out.println("\n".repeat(30));
		System.out.println("RETO 7 11 B\n"+("-".repeat(20)));
		System.out.println("Direccion: "+direccion);
		System.out.println("Esperando entrada Este("+colaEntradaEste.size()+"): "+colaEntradaEste.stream().map(Object::toString).collect(Collectors.joining(", ")));
		System.out.println("Esperando entrada Oeste("+colaEntradaOeste.size()+"): "+colaEntradaOeste.stream().map(Object::toString).collect(Collectors.joining(", ")));
		System.out.println("Dentro del canal("+canal.size()+"): "+canal.stream().map(Object::toString).collect(Collectors.joining(", ")));
 
	}













}



