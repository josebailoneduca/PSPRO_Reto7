package reto7_09.a;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;


/**
 * Fabrica contiene dos buffers para conectar los preparadodes de panecillos e ingredientes con el preparador de hamburguesas.
 * Los buffers son del tipo LinkedBlockingDeque controlandose automaticamente las esperas cuando no hay sitio en algun buffer
 * y se intenta agregar o cuando no quedan elementos en el buffer y se intenta agregar.
 * 
 * Ademas contiene una lista donde se van colocando las hamburguesas preparadas
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Fabrica {
	
	/**
	 * Buffer de conexion entre el creador de panecillos(productor) y el preparador de haburguesas(consumidor)
	 */
	private static   LinkedBlockingDeque<Integer> cestaPanecillos = new LinkedBlockingDeque<Integer>(Config.N_CESTA_PANECILLOS);
	
	/**
	 * Buffer de conexion entre el creador de ingredientes(productor) y el preparador de haburguesas(consumidor)
	 */
	private static  LinkedBlockingDeque<Integer> cestaIngredientes = new LinkedBlockingDeque<Integer>(Config.N_CESTA_INGREDIENTES);
	
	/**
	 * Lista en la que se colocan las hamburguesas creadas
	 */
	private static   LinkedList<Hamburguesa> bandejaHamburguesas = new LinkedList<Hamburguesa>();

	
	/**
	 * Sacar un panecillo del buffer cestaPanecillos
	 * 
	 * @return El panecillo sacado
	 */
	public static int cogerPanecillo() {
		try {
			return cestaPanecillos.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Poner un panecillo en el buffer cestaPanecillos
	 * 
	 * @param id Id que representa el panecillo
	 */
	public static void ponerPanecillo(int id) {
		try {
			cestaPanecillos.put(id);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sacar un pack de ingredientes del buffer cestaIngredientes
	 * 
	 * @return El pack sacado del buffer
	 */
	public static int cogerIngrediente() {
		try {
			return cestaIngredientes.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Poner un pack de ingredientes en el buffer cestaIngredientes
	 * 
	 * @param id Id que representa el pack de ingredientes
	 */
	public static void ponerIngrediente(int id) {
		try {
			cestaIngredientes.put(id);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Pone una hamburguesa en la lista bandeja Hamburguesas
	 * 
	 * @param h La hamburguesa a agregar
	 */
	public static void ponerHamburguesa(Hamburguesa h) {
		bandejaHamburguesas.add(h);
	}

	/**
	 * Devuelve una referncia al buffer cestaPanecillos
	 * 
	 * @return El buffer
	 */
	public static LinkedBlockingDeque<Integer> getCestaPanecillos() {
		return cestaPanecillos;
	}

	/**
	 * Devuelve una referncia al buffer cestaIngredientes
	 * 
	 * @return El buffer
	 */
	public static LinkedBlockingDeque<Integer> getCestaIngredientes() {
		return cestaIngredientes;
	}

	/**
	 * Devuelve una referncia a la lista bandejaHamburguesas
	 * 
	 * @return El buffer
	 */
	public static LinkedList<Hamburguesa> getBandejaHamburguesas() {
		return bandejaHamburguesas;
	}
	
	
}
