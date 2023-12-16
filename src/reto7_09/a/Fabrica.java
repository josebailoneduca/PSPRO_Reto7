package reto7_09.a;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;

public class Fabrica {
	private static   LinkedBlockingDeque<Integer> cestaPanecillos = new LinkedBlockingDeque<Integer>(Config.N_CESTA_PANECILLOS);
	private static  LinkedBlockingDeque<Integer> cestaIngredientes = new LinkedBlockingDeque<Integer>(Config.N_CESTA_INGREDIENTES);
	private static   LinkedList<Hamburguesa> bandejaHamburguesas = new LinkedList<Hamburguesa>();

	public static int cogerPanecillo() {
		try {
			return cestaPanecillos.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public static void ponerPanecillo(int id) {
		try {
			cestaPanecillos.put(id);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static int cogerIngrediente() {
		try {
			return cestaIngredientes.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public static void ponerIngrediente(int id) {
		try {
			cestaIngredientes.put(id);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void ponerHamburguesa(Hamburguesa h) {
		bandejaHamburguesas.add(h);
	}

	public static LinkedBlockingDeque<Integer> getCestaPanecillos() {
		return cestaPanecillos;
	}

	public static LinkedBlockingDeque<Integer> getCestaIngredientes() {
		return cestaIngredientes;
	}

	public static LinkedList<Hamburguesa> getBandejaHamburguesas() {
		return bandejaHamburguesas;
	}
	
	
}
