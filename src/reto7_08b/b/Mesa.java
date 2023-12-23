package reto7_08b.b;


import java.util.concurrent.Semaphore;


/**
 * <p>
 * Mesa en a que se colocan ingredientes por parte del proveedor y se recogen por parte de los mafiosos.
 * </p>
 * <p>
 * La mesa contiene dos semaforos. Uno para controlar el acceso del proveedor a poner nuevos ingredientes. 
 * El otro para controlar el acceso a los ingredientes por parte de los mafiosos
 * </p>
 * <p>
 * Cuando el proveedor entra en Mesa a poner ingredientes espera el semaforo de proveedor. 
 * Tras ponerlos libera un permiso en el semaforo de ingredientes.
 * Los mafiosos cuando entran a coger los ingredientes esperan el semaforo  de ingredientes. Si no son los ingredientes que necesita libera el 
 * semaforo de ingredientes. Si son los ingredinetes que necesita los retira y libera  el semaforo de proveedor para que ponga otros.
 * </p>
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Mesa {
	
	/**
	 * Ingrediente 1 en la mesa
	 */
	private Ingrediente ing1;
	
	/**
	 * Ingrediente 2 en la mesa
	 */
	private Ingrediente ing2;
	
	/**
	 * Semaforo de proveedor se abrira cuando sea necesario poner nuevos ingredientes
	 */
	private Semaphore sProveedor =new Semaphore(1);
	
	/**
	 * Semaforo de ingredientes se abrira cuando haya ingredientes que mirar. 
	 * Ademas controla que los ingredientes se vea por los mafiosos de uno en uno
	 */
	private Semaphore sIngredientes =new Semaphore(0);
	
	/**
	 * Poner ingredientes. Usado por el proveedor suministra ingredientes a la mesa.
	 *  
	 * @param ing1 Ingrediente 1 a colocar en la mesa
	 * @param ing2 Ingrediente 2 a colocar en la mesa
	 */
	public  void poner (Ingrediente ing1,Ingrediente ing2) {
		//esperar semaforo de proveedor
		try {
			sProveedor.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//poner ingredientes
		this.ing1=ing1;
		this.ing2=ing2;
		Estadistica.ponerIngredientes(ing1, ing2);
		
		//liberar semaforo de ingredientes
		sIngredientes.release();
	}
	
	/**
	 * Coger los ingredientes de la mesa. Los mafiosos esperan al semaforo de ingredientes, comprueban los ingredientes y si no son los suyos
	 * libera el semaforo de ingredientes. En caso de ser los suyos libera el semaforo de proveedor para que ponga otros ingredientes
	 * 
	 * @param falta Ingrediente que ya tiene el mafioso por si mismo
	 * 
	 * @return True si los ingredientes de la mesa son diferentes a los del mafioso. False si alguno de los ingredientes es igual al del mafioso
	 */
	public  boolean coger(Ingrediente falta) {
		try {
			sIngredientes.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (ing1==null || ing2==null) {
			sIngredientes.release();
			return false;
		}else if (ing1!=falta && ing2!=falta) {
			ing1=ing2=null;
			sProveedor.release();
			return true;
		}else {
			sIngredientes.release();
			return false;
		}
	}

	 
	
}
