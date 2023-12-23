package reto7_09.b;

/**
 * <p>
 * Main del Reto7_9_B.
 * </p>
 * <p>
 * Hay 3 hebras, una prepara panecillos, otra perpara ingredientes y otra
 * prepara hamburguesas. Para comunicar las hebras se usa la clase Fabrica la
 * cual contiene buffers en donde se colocan y retiran elementos que representan
 * los panecillos, los ingredientes y las hamburguesas
 * </p>
 * <p>
 * En esta implementacion los buffers son arrays de enteros controlandose el
 * acceso a los mismos con semaforos en un esquema productor/consumidor.
 * </p>
 * 
 * @author Jose Javier Bailon Ortiz
 * @see Fabrica
 */
public class Reto7_9_B {

	public static void main(String[] args) {

		//crear preparadores
		Thread prepPanecillos = new PrepPanecillos();
		Thread prepIngredientes = new PrepIngredientes();
		Thread prepHamburguesas = new PrepHamburguesas();

		//iniciar hebras
		prepPanecillos.start();
		prepIngredientes.start();
		prepHamburguesas.start();

		//bucle que muestra el estado del sistema cada 500ms
		while (true) {
			Estadistica.mostrarEstadistica();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
