package reto7_09.b;

import java.util.stream.Collectors;

/**
 * Muestra el estado del sistema
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Estadistica {
	/**
	 * Identificador del preparador de panecillos
	 */
	public static final int PREP_PANECILLOS = 0;

	/**
	 * Identificador del preparador de ingredientes
	 */
	public static final int PREP_INGREDIENTES = 1;

	/**
	 * Identificador del preparador de hamburguesas
	 */
	public static final int PREP_HAMBURGUESAS = 2;

	/**
	 * Estado preparando
	 */
	public static final int PREPARANDO = 0;

	/**
	 * Estado esperando
	 */
	public static final int ESPERANDO = 1;

	/**
	 * Etiquetas de los estados
	 */
	private static String[] etiquetas = { "Preparando", "Esperando" };

	/**
	 * Estado actual del preparador de panecillos
	 */
	private static int estadoPrepPanecillos = 0;

	/**
	 * Estado actual del preparador de ingredientes
	 */
	private static int estadoPrepIngredientes = 0;

	/**
	 * Estado actual del preparador de hamburguesas
	 */
	private static int estadoPrepHamburguesas = 0;

	/**
	 * Define el estado de un actor
	 * 
	 * @param tipo   Tipo de actor (prep_ingredientes, prep_panecillos,
	 *               prep_hamburguesas)
	 * @param estado Estado en el que esta (preparando, esperando)
	 */
	public static void setEstado(int tipo, int estado) {
		switch (tipo) {
		case PREP_PANECILLOS -> {
			estadoPrepPanecillos = estado;
		}
		case PREP_INGREDIENTES -> {
			estadoPrepIngredientes = estado;
		}
		case PREP_HAMBURGUESAS -> {
			estadoPrepHamburguesas = estado;
		}
		default -> {
		}
		}
	}

	/**
	 * Muestra el estado por pantalla
	 */
	public static void mostrarEstadistica() {
		try {
			System.out.println("\n".repeat(30));
			System.out.println("RETO 7 9 B\n" + ("-".repeat(20)));
			System.out.println("Preparador panecillos: " + etiquetas[estadoPrepPanecillos]);
			System.out.println("CestaPanecillos:");
			for (int p : Fabrica.getCestaPanecillos()) {
				if (p > 0)
					System.out.print(p + ",");
			}
			System.out.println();
			System.out.println("Preparador ingredientes: " + etiquetas[estadoPrepIngredientes]);
			System.out.println("CestaIngredientes:");
			for (int i : Fabrica.getCestaIngredientes()) {
				if (i > 0)
					System.out.print(i + ",");
			}
			System.out.println();
			System.out.println("Preparador haburguesas: " + etiquetas[estadoPrepHamburguesas]);
			System.out.println("Hamburguesas preparadas: " + Fabrica.getBandejaHamburguesas().stream()
					.map(Object::toString).collect(Collectors.joining(", ")));
		} catch (Exception ex) {

		}

	}
}
