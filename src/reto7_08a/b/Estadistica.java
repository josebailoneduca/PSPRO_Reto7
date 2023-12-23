package reto7_08a.b;

/**
 * Muestra el estado general del sistema
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Estadistica {
	/**
	 * Estado esperando
	 */
	public static final int ESPERANDO=0;
	
	/**
	 * Estado comiendo
	 */
	public static final int COMIENDO=1;
	
	/**
	 * Etiquetas de los estados
	 */
	private static String[]etiquetas = {"Esperando","Comiendo --------"};
	
	/**
	 * Estado del mafioso pasta
	 */
	private static int mafiosoPasta =0;
	
	/**
	 * Estado del mafioso tomate
	 */
	private static int mafiosoTomate =0;
	
	/**
	 * Estado del mafioso queso
	 */
	private static int mafiosoQueso =0;
	
	/**
	 * Ingrediente 1 en la mesa
	 */
	private static Ingrediente ing1;
	
	/**
	 * Ingrediente 2 en la mesa
	 */
	private static Ingrediente ing2;

	/**
	 * Define el estado de un mafioso
	 * @param ingrediente Ingrediente que tiene
	 * @param estado Estado del mafiosos
	 */
	public static void setEstado(Ingrediente ingrediente, int estado) {
		switch (ingrediente) {
		case PASTA ->{mafiosoPasta=estado;}
		case TOMATE->{mafiosoTomate=estado;}
		case QUESO ->{mafiosoQueso=estado;}
		default->{}
		}
	}
	
	/**
	 * Actualiza los ingredientes sobre la mesa
	 * @param ingred1 Ingrediente 1 de la mesa
	 * @param ingred2 Ingrediente 2 de la mesa
	 */
	public static void ponerIngredientes(Ingrediente ingred1, Ingrediente ingred2) {
		ing1=ingred1;
		ing2=ingred2;
	}
	
	
	/**
	 * Muestra los datos del sistema 
	 */
	public static void mostrarEstadistica() {
		System.out.println("\n".repeat(30));
		System.out.println("RETO 7 8A B\n"+("-".repeat(20)));
		System.out.println("Mafioso pasta: "+etiquetas[mafiosoPasta]);
		System.out.println("Mafioso tomate: "+etiquetas[mafiosoTomate]);
		System.out.println("Mafioso queso: "+etiquetas[mafiosoQueso]);
		System.out.println("Ultimos ingredientes puestos en la mesa:" + ing1+" , "+ ing2 );
	}
}



