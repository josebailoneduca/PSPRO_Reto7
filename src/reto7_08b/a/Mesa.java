package reto7_08b.a;

/**
 * <p>
 * Mesa en la que se colocan ingredientes por parte del proveedor y se recogen 
 * por parte de los mafiosos.
 * </p>
 * <p>
 * La mesa en si es un monitor. Deja a los mafiosos esperando si intentan coger 
 * los ingredientes pero no son los que necesita.
 * </p>
 * <p>
 * Cuando el proveedor coloca ingredientes notifica a los hilos que esperan 
 * </p>
 * <p>
 * Cuando el proveedor coloca ingredientes queda esperando y cuando un mafioso 
 * coge los ingredientes notifica a los hilos que esperan haciendo que el 
 * proveedor coloque inmediatamente otros dos ingredientes.
 * </p>
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Mesa {

	/**
	 * Ingrediente 1 de la mesa
	 */
	private Ingrediente ing1;

	/**
	 * Ingrediente 2 de la mesa
	 */
	private Ingrediente ing2;

	/**
	 * Poner dos ingredientes en la mesa. Una vez puestos los ingredientes notifica
	 * a todos los hilos
	 * 
	 * @param ing1 Primer ingrediente a poner
	 * @param ing2 Segundo ingrediente a poner
	 */
	public synchronized void poner(Ingrediente ing1, Ingrediente ing2) {
		this.ing1 = ing1;
		this.ing2 = ing2;
		Estadistica.ponerIngredientes(ing1, ing2);
		notifyAll();
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Coger ingredientes de la mesa. Compara el ingrediente suministrado con los
	 * que hay en la mesa. Si conincide alguno deja el hilo esperando. 
	 * Si no coinciden los ingredientes es porque son para el mafioso y retira los
	 * ingredientes ademas de notificar a los hilos que estan esperando(entre ellos el proveedor)  
	 * 
	 * @param falta Ingrediente que tiene el mafioso. Sera comparado con los de la
	 *              mesa para ver si forman un grupo de tres ingredientes
	 *              diferentes.
	 * @return True si el ingrediente suministrado es el que faltaba para tener tres
	 *         diferentes. False si no hay ingredientes o alguno es igual que el
	 *         suministrado
	 */
	public synchronized boolean coger(Ingrediente falta) {
		if (ing1 == null || ing2 == null)
			return false;
		if (ing1 != falta && ing2 != falta) {
			ing1 = ing2 = null;
			notifyAll();
			return true;
		} else {
			try {
				wait();
			} catch (InterruptedException e) {
			}
			return false;
		}
	}
}
