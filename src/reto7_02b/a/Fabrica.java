package reto7_02b.a;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * Clase que controla el acceso a la caja vacia y el almacen donde se guardan las cajas llenas.
 * Controla el acceso de embotellador y empaquetador usando semaforos.
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Fabrica {
	
	/**
	 * Almacena las cajas llenas
	 */
	public static LinkedList<Caja> almacen = new LinkedList<Caja>();

	/**
	 * Id a usar para la proxima botella
	 */
	public static int idProximaBotella=1;

	/**
	 * Id a usar para la proxima caja
	 */
	public static int idProximaCaja=1;

	/**
	 * Caja para ser completada por el embotellador
	 */
	private static Caja cajaParaLLenar = new Caja(0);
	
	/**
	 * Abierto cuando hay caja llena
	 */
	private static Semaphore cajaEstaLlena = new Semaphore(0);

	/**
	 * Abierto cuando hace falta caja vacia
	 */
	private static Semaphore faltaCajaVacia = new Semaphore(0);

	/**
	 * abierto cuando hay caja con plazas disponibles
	 */
	private static Semaphore hayCajaConPlazas = new Semaphore(1);

	/**
	 * Poner una caja vacia para ser llenada
	 * 
	 * @param caja La caja a poner
	 */
	public static void ponerCajaVacia(Caja caja) {
		try {
			// esperar al semaforo de que falta caja vacia
			faltaCajaVacia.acquire();
			// poner la caja
			cajaParaLLenar = caja;
			// abrir el semaforo de que hay una caja sin llenar
			hayCajaConPlazas.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Retirar una caja llena
	 * 
	 * @return la caja retirada
	 */
	public static Caja retirarCajaLlena() {
		try {
			// esperar semaforo de caja llena
			cajaEstaLlena.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//quitar la caja y devolverla
		Caja aux = cajaParaLLenar;
		cajaParaLLenar = null;
		//aviso de que falta caja vacia
		faltaCajaVacia.release();
		return aux;
	}

	/**
	 * Poner una botella en una caja vacia
	 * 
	 * @param botella La botella a poner
	 */
	public static void ponerBotellaEnCaja(int botella) {
		try {
			//esperar semaforo de caja con plazas
			hayCajaConPlazas.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//poner botella y comprobar si quedan huecos.  
		boolean quedanHuecos = cajaParaLLenar.ponerBotella(botella);
		//Abrir el semaforo apropiado segun queden huecos o no
		if (quedanHuecos)
			hayCajaConPlazas.release();
		else
			cajaEstaLlena.release();

	}

	/**
	 * Devuelve una referencia a la caja que hay actualmente para ser llenada
	 * @return La caja
	 */
	public static Caja getCajaParaLLenar() {
		return cajaParaLLenar;
	}
 
}
