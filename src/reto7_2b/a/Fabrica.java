package reto7_2b.a;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Fabrica {
	private static Caja cajaParaLLenar = new Caja(0);
	public static LinkedList<Caja> almacen = new LinkedList<Caja>();
	public static int idProximaBotella=1;
	public static int idProximaCaja=1;
	/**
	 * Abierto cuando hay caja llena
	 */
	public static Semaphore cajaEstaLlena = new Semaphore(0);

	/**
	 * Abierto cuando hace falta caja vacia
	 */
	public static Semaphore faltaCajaVacia = new Semaphore(0);
	/**
	 * abierto cuando hay caja con plazas disponibles
	 */
	public static Semaphore hayCajaConPlazas = new Semaphore(1);

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

	public static Caja getCajaParaLLenar() {
		return cajaParaLLenar;
	}
 
}
