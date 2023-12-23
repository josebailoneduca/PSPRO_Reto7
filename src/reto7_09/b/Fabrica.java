package reto7_09.b;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;




/**
 * <p>
 * Fabrica contiene dos buffers para conectar los preparadodes de panecillos e ingredientes con el preparador de hamburguesas.
 * </p>
 * <p>
 * Los buffers son arrays de enteros controlandose el acceso a los mismos con semaforos en un esquema productor/consumidor.
 * </p>
 * <p>
 * Ademas contiene una lista donde se van colocando las hamburguesas preparadas
 * </p>
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Fabrica {
	/**
	 * Buffer para panecillos
	 */
	private static   int[] cestaPanecillos = new int[Config.N_CESTA_PANECILLOS];
	
	/**
	 * Semaforo de control de huecos disponibles para panecillos
	 */
	private static Semaphore sPanPoner=new Semaphore(Config.N_CESTA_PANECILLOS);
	
	/**
	 * Semaforo de control de panecillos disponibles en el buffer
	 */
	private static Semaphore sPanCoger=new Semaphore(0);
	
	/**
	 * Indice donde agregar en el buffer para panecillos
	 */
	private static int iPanPoner=-1;
	
	/**
	 * Indice del que coger en el buffer para pencillos
	 */
	private static int iPanCoger=-1;
	
	/**
	 * Buffer de ingredientes
	 */
	private static  int[] cestaIngredientes = new int[Config.N_CESTA_INGREDIENTES];
	
	/**
	 * Semaforo de control de huecos disponibles para ingredientes
	 */
	private static Semaphore sIngPoner=new Semaphore(Config.N_CESTA_INGREDIENTES);
	
	/**
	 * Semaforo de control de ingredientes disponibles en el buffer
	 */
	private static Semaphore sIngCoger=new Semaphore(0);

	/**
	 * Indice en el que poner en el buffer para ingredientes
	 */
	private static int iIngPoner=-1;
	
	/**
	 * Indice del que coger en el buffer para ingredientes
	 */
	private static int iIngCoger=-1;

	
	/**
	 * Lista en la que se almacenan las hamburguesas preparadas
	 */
	private static   LinkedList<Hamburguesa> bandejaHamburguesas = new LinkedList<Hamburguesa>();

	/**
	 * Retira un panecillo de la cesta de panecillos
	 * 
	 * @return El panecillo retirado
	 */
	public static int cogerPanecillo() {
		try {
			sPanCoger.acquire();
			int pan = cestaPanecillos[(++iPanCoger)%Config.N_CESTA_PANECILLOS];
			cestaPanecillos[iPanCoger%Config.N_CESTA_PANECILLOS]=0;
			sPanPoner.release();
			return pan;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Pone un panecillo en la cesta de panecillos
	 * 
	 * @param id El panecillo a colocar
	 */
	public static void ponerPanecillo(int id) {
		try {
			sPanPoner.acquire();
			cestaPanecillos[(++iPanPoner)%Config.N_CESTA_PANECILLOS]=id;
			sPanCoger.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retira un ingrediente de la cesta de ingredientes
	 * 
	 * @return El ingrediente retirado
	 */
	public static int cogerIngrediente() {
		try {
			sIngCoger.acquire();
			int ing = cestaIngredientes[(++iIngCoger)%Config.N_CESTA_INGREDIENTES];
			cestaIngredientes[iIngCoger%Config.N_CESTA_INGREDIENTES]=0;
			sIngPoner.release();
			return ing;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Pone un ingrediente en la cesta de ingredientes
	 * 
	 * @param id El ingrediente a agregar
	 */
	public static void ponerIngrediente(int id) {
		try {
			sIngPoner.acquire();
			cestaIngredientes[(++iIngPoner)%Config.N_CESTA_INGREDIENTES]=id;
			sIngCoger.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Pone una hamburguesa en la lista de hamburguesas preparadas
	 * 
	 * @param h La hamburguesa a agregar
	 */
	public static void ponerHamburguesa(Hamburguesa h) {
		bandejaHamburguesas.add(h);
	}

	/**
	 * Devuelve el buffer de panecillos
	 * 
	 * @return El buffer
	 */
	public static int[] getCestaPanecillos() {
		return cestaPanecillos;
	}

	/**
	 * Devuelve el buffer de ingredientes
	 * 
	 * @return El buffer
	 */
	public static int[] getCestaIngredientes() {
		return cestaIngredientes;
	}

	/**
	 * Devuelve la lista de hamburguesas
	 * 
	 * @return La lista
	 */
	public static LinkedList<Hamburguesa> getBandejaHamburguesas() {
		return bandejaHamburguesas;
	}
	
	
}
