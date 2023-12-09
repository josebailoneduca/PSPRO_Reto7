package reto7_4.a;

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
	public static Semaphore plazasParaBotellasDisponible = new Semaphore(Config.TAMANO_CAJA);

	/**
	 * Semaforo de acceso a nueva id de botella
	 */
	public static Semaphore sIdBotella=new Semaphore(1);
	
	
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
			plazasParaBotellasDisponible.release(Config.TAMANO_CAJA);
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
			plazasParaBotellasDisponible.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//poner botella y comprobar si quedan huecos.  
		boolean quedanHuecos = cajaParaLLenar.ponerBotella(botella);
		//Si no quedan huegos abrir el semaforo de caja llena
		if (!quedanHuecos)
			cajaEstaLlena.release();

	}

	public static Caja getCajaParaLLenar() {
		return cajaParaLLenar;
	}

	public static int getNuevaBotella() {
		try {
			sIdBotella.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int id=idProximaBotella++;
		sIdBotella.release();
		return id;
	}
 
}
