package reto7_13.a;

 


/**
 * Muestra en pantalla el estado del sistema
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Estadistica {
	
	private static String esperandoMatematicos = "";
	private static String esperandoNoMatematicos = "";
	
	private static String pasajerosMatematicos = "";
	private static String pasajerosNoMatematicos = "";

	private static String trasladadosMatematicos = "Ningungo";
	private static String trasladadosNoMatematicos = "Ninguno";
	
	private static boolean  enOrigen=true;
	
	public static void espera(String matematicos, String noMatematicos) {
		esperandoMatematicos=matematicos;
		esperandoNoMatematicos=noMatematicos;
	}
	 
	public static void enBote(String matematicos, String noMatematicos) {
		pasajerosMatematicos=matematicos;
		pasajerosNoMatematicos=noMatematicos;
	}
	
	public static void traladados(String matematicos, String noMatematicos) {
		trasladadosMatematicos=matematicos;
		trasladadosNoMatematicos=noMatematicos;
	}

	public static void enOrigen(boolean estaEnorigen) {
		enOrigen=estaEnorigen;
	}
	
	/**
	 * Muestra los datos por pantalla
	 */
	public static void mostrarEstadistica() {
		System.out.println("\n".repeat(30));
		System.out.println("RETO 7 13 A\n"+("-".repeat(20)));
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



