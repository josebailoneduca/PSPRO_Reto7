package reto7_08a.b;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;



/**
 * <p>
 * Mesa en a que se colocan ingredientes por parte del proveedor y se recogen por parte de los mafiosos.
 * </p>
 * <p>
 * La mesa contiene dos barreras. Una para asegurar que se han puesto los ingredientes en la mesay todos los mafiosos estan en 
 * siguacion de comprobar los ingredientes y otra de salida que asegura que los ingredientes se han comprobado y consumido.
 * </p>
 * <p>
 * Cuando el proveedor coloca ingredientes y todos los mafiosos estan listos para comprobar se levanta la barrera de entrada. Entonces
 * los mafiosos comprueban los alimentos y acceden a la barrera de salida junto con el proveedor excepto el mafioso que come. Ese mafioso
 * llega a la barrera de salida una vez ha comido, momento en el que se levanta la barrera de salida y el ciclo empieza de nuevo.
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
	 * Barrera de entrada. Se levanta cuando ya se han puesto los ingredientes
	 * y todos los mafisos estan listos para comprobar los ingredientes de la mesa
	 */
	private CyclicBarrier entrada= new CyclicBarrier(4);
	
	/**
	 * Barrera de salida. Se levanta cuando todos los mafiosos han comprobado los ingredientes 
	 * y ademas se han consumido
	 */
	private CyclicBarrier salida= new CyclicBarrier(4);
	
	
	/**
	 * Poner ingredientes. Usado por el proveedor suministra ingredientes a la mesa.
	 *  
	 * @param ing1 Ingrediente 1 a colocar en la mesa
	 * @param ing2 Ingrediente 2 a colocar en la mesa
	 */
	public  void poner (Ingrediente ing1,Ingrediente ing2) {
		//poner ingredientes en la mesa
		this.ing1=ing1;
		this.ing2=ing2;
		Estadistica.ponerIngredientes(ing1, ing2);
		//esperar barreras
		barreraEntrada();
		barreraSalida();
	}
	
	/**
	 * Coger los ingredientes. Los mafiosos esperan a la barrera de entrada, comprueban los ingredientes y si no son los suyos
	 * espera a la barrera de salida. En caso de ser los suyos no espera barrera
	 * 
	 * @param falta Ingrediente que ya tiene el mafioso por si mismo
	 * 
	 * @return True si los ingredientes de la mesa son diferentes a los del mafioso. False si alguno de los ingredientes es igual al del mafioso
	 */
	public  boolean coger(Ingrediente falta) {
		//esperar a que se hayan puesto los ingredientes en la mesa
		//y todos los mafiosos esten en este punto
		barreraEntrada();
		//si los ingredientes estan a null es que lo ha cogido ya otro mafioso
		if (ing1==null || ing2==null) {
			barreraSalida();
			return false;
		///si son los ingredientes correctos se vacia los ingredientes
		}else if (ing1!=falta && ing2!=falta) {
			ing1=ing2=null;
			return true;
			
		//si no son los ingredientes se espera a la barrera de salida
		}else {
			barreraSalida();
			return false;
		}
	}

	/**
	 * Avisar de que el mafioso ya ha comido. Espera la barrera de salida
	 */
	public  void avisar() {
		barreraSalida();
	}
	
	
	/**
	 * Esperar a la barrera de entrada
	 */
	private void barreraEntrada() {
		try {
			entrada.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Esperar a la barrera de salida
	 */
	private void barreraSalida() {
		try {
			salida.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
}
