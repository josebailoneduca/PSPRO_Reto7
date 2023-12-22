package reto7_05.b;


/**
 * Muestra las veces que se ha ejecutado cada paso
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Estadistica {
	/**
	 * Veces que se ha ejecutado a
	 */
	public static int a = 0;

	/**
	 * Veces que se ha ejecutado b
	 */

	public static int b = 0;

	/**
	 * Veces que se ha ejecutado c
	 */
	public static int c = 0;

	/**
	 * Veces que se ha ejecutado d
	 */
	public static int d = 0;

	/**
	 * Veces que se ha ejecutado e
	 */
	public static int e = 0;

	/**
	 * Veces que se ha ejecutado f
	 */
	public static int f = 0;

	
	/**
	 * Muestra la cantidad de veces que se ha ejecutado cada apartado
	 */
	public static void mostrarEstadistica() {
		System.out.println("\n".repeat(30));
		System.out.println("RETO 7 5 B\n" + ("-".repeat(20)));

		System.out.println("A: " + a);
		System.out.println("B: " + b);
		System.out.println("C: " + c);
		System.out.println("D: " + d + " ----");
		System.out.println("E: " + e);
		System.out.println("F: " + f);
		System.out.println("-".repeat(5));
		System.out.println("Suma A+E:" + (a + e));

	}
}
