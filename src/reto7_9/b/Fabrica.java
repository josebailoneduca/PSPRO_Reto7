package reto7_9.b;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;

public class Fabrica {
	private static   int[] cestaPanecillos = new int[Config.N_CESTA_PANECILLOS];
	private static Semaphore sPanPoner=new Semaphore(Config.N_CESTA_PANECILLOS);
	private static Semaphore sPanCoger=new Semaphore(0);
	private static int iPanPoner=-1;
	private static int iPanCoger=-1;
	private static  int[] cestaIngredientes = new int[Config.N_CESTA_INGREDIENTES];
	private static Semaphore sIngPoner=new Semaphore(Config.N_CESTA_INGREDIENTES);
	private static Semaphore sIngCoger=new Semaphore(0);
	private static int iIngPoner=-1;
	private static int iIngCoger=-1;

	private static   LinkedList<Hamburguesa> bandejaHamburguesas = new LinkedList<Hamburguesa>();

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

	public static void ponerPanecillo(int id) {
		try {
			sPanPoner.acquire();
			cestaPanecillos[(++iPanPoner)%Config.N_CESTA_PANECILLOS]=id;
			sPanCoger.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

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

	public static void ponerIngrediente(int id) {
		try {
			sIngPoner.acquire();
			cestaIngredientes[(++iIngPoner)%Config.N_CESTA_INGREDIENTES]=id;
			sIngCoger.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void ponerHamburguesa(Hamburguesa h) {
		bandejaHamburguesas.add(h);
	}

	public static int[] getCestaPanecillos() {
		return cestaPanecillos;
	}

	public static int[] getCestaIngredientes() {
		return cestaIngredientes;
	}

	public static LinkedList<Hamburguesa> getBandejaHamburguesas() {
		return bandejaHamburguesas;
	}
	
	
}
