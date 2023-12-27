package reto7_13.b;

 


/**
 * Muestra en pantalla el estado del sistema
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Estadistica {
	
	/**
	 * String con la lista de matematicos esperando a subir al bote
	 */
	private static String esperandoMatematicos = "";
	
	/**
	 * String con la lista de no matematicos esperando a subir al bote
	 */
	private static String esperandoNoMatematicos = "";
	
	/**
	 * String con la lista de matematicos que hay en el bote
	 */
	private static String pasajerosMatematicos = "";
	
	/**
	 * String con la lista de no matematicos que hay en el bote
	 */
	private static String pasajerosNoMatematicos = "";

	/**
	 * String con la lista de matematicos que han sido trasladados
	 */
	private static String trasladadosMatematicos = "Ningungo";
	
	/**
	 * String con la lista de no matematicos que han sido trasladados
	 */
	private static String trasladadosNoMatematicos = "Ninguno";
	
	/**
	 * True si el bote esta en la orilla de salida, False si esta en la orilla de destino
	 */
	private static boolean  enOrigen=true;
	
	/**
	 * Establecer las listas de viajeros que esperan a subir al bote
	 * @param matematicos Lista de matematicos
	 * @param noMatematicos Lista de no matematicos
	 */
	public static void espera(String matematicos, String noMatematicos) {
		esperandoMatematicos=matematicos;
		esperandoNoMatematicos=noMatematicos;
	}
	
	/**
	 * Establecer las listas de viajeros que hay en el bote
	 * @param matematicos Lista de matematicos
	 * @param noMatematicos Lista de no matematicos
	 */
	public static void enBote(String matematicos, String noMatematicos) {
		pasajerosMatematicos=matematicos;
		pasajerosNoMatematicos=noMatematicos;
	}
	
	
	/**
	 * Establecer las listas de viajeros que ya se han trasladado
	 * @param matematicos Lista de matematicos
	 * @param noMatematicos Lista de no matematicos
	 */
	public static void traladados(String matematicos, String noMatematicos) {
		trasladadosMatematicos=matematicos;
		trasladadosNoMatematicos=noMatematicos;
	}

	/**
	 * Establece si el bote esta en la orilla de origen o en la de destino
	 * @param estaEnorigen True en la de origen, False en la de destino
	 */
	public static void enOrigen(boolean estaEnorigen) {
		enOrigen=estaEnorigen;
	}
	
	/**
	 * Muestra los datos por pantalla
	 */
	public static void mostrarEstadistica() {
		System.out.println("\n".repeat(30));
		System.out.println("RETO 7 13 B\n"+("-".repeat(20)));
		System.out.println("Matematicos esperando: "+esperandoMatematicos);
		System.out.println("No matematicos esperando: "+esperandoNoMatematicos);
		System.out.println("ORIGEN");
		System.out.println("-".repeat(30));
		if (!enOrigen)
			System.out.println();
		System.out.println("Matematicos en bote: "+pasajerosMatematicos);
		System.out.println("No matematicos en bote: "+pasajerosNoMatematicos);
		if (enOrigen)
			System.out.println();
		System.out.println("-".repeat(30));
		System.out.println("DESTINO");
		System.out.println("Matematicos trasladados: "+trasladadosMatematicos);
		System.out.println("No matematicos trasladados: "+trasladadosNoMatematicos);
	}
	

}



