package reto7_14.b;

 
/**
 * Muestra en pantalla el estado del aparcamiento
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Estadistica {
	
	/**
	 * String con la lista de coches haciendo cola en la entrada
	 */
	private static  String colaEntrada = "";
	
	/**
	 * String con la lista de coches residentes que hacen cola en la entrada
	 */
	private  static String colaResidentes = "";
	
	/**
	 * String con la lista de coches dentro del aparcamiento
	 */
	private  static  String cochesDentro= "";

	/**
	 * Numero de plazas disponibles en el aparcamiento
	 */
	private  static int plazasDisponibles= Config.PLAZAS_TOTALES;
	
	/**
	 * Velocidad a la que se generan coches
	 */
	private static int velocidadGeneracion=0;
	  
	private static int cambioVelocidadEn=0;
	
	public static void actualizaListas(String nColaEntrada, String nColaResidentes, String nCochesDentro, int nPlazasDisponibles) {
		colaEntrada = nColaEntrada;
		colaResidentes = nColaResidentes;
		cochesDentro = nCochesDentro;
		plazasDisponibles = nPlazasDisponibles;
	}



	public static void setVelocidadGeneracion(int velocidadGeneracion,int cambioVelocidadEn) {
		Estadistica.velocidadGeneracion = velocidadGeneracion;
		Estadistica.cambioVelocidadEn=cambioVelocidadEn;
	}



	/**
	 * Muestra los datos por pantalla
	 */
	public static void mostrarEstadistica() {
		System.out.println("\n".repeat(30));
		System.out.println("RETO 7 14 B\n"+("-".repeat(20)));
		System.out.println("Velocidad de llegada de coches (cambio en "+cambioVelocidadEn+"): 1 coche cada "+velocidadGeneracion+" ms");
		System.out.println("Cola de residentes: "+colaResidentes);
		System.out.println("Cola total: "+colaEntrada);
		System.out.println("Vehiculos dentro: "+cochesDentro);
		System.out.println("Plazas disponibles: "+plazasDisponibles+" de "+ Config.PLAZAS_TOTALES);
 	}


 
	

}



