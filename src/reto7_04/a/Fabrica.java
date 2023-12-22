package reto7_04.a;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * Clase que controla el acceso a la caja vacia y el almacen donde se guardan las cajas llenas.
 * Controla el acceso de los embotelladores y del empaquetador usando semaforos.
 * Tambien controla la generacion de id para las botellas.
 * 
 * Para controlar el acceso a la caja con plazas disponibles usa el semaforo 
 * plazasParaBotellasDisponibles. Ese semaforo se inicializa con tantos permisos 
 * como plazas tiene la caja al ser colocada por el empaquetador. Cuando un embotellador
 * coloca una botella debe reducir los permisos de este semaforo sirviendo asi como 
 * control para evitar que un semaforo intente poner una botella en una caja llena
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Fabrica {
	private static Caja cajaParaLLenar = new Caja(0);
	private static int idProximaBotella=1;
	public static LinkedList<Caja> almacen = new LinkedList<Caja>();
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
	 * Abierto cuando mientras hay plazas disponibles en la caja.
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
	 * Poner una botella en una caja vacia. El hilo que ejecuta este metodo
	 * pasa por el semaforo de plazas para botellas disponibles
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

	/**
	 * Devuele la caja para llenar
	 * @return La caja
	 */
	public static Caja getCajaParaLLenar() {
		return cajaParaLLenar;
	}

	/**
	 * Devuelve una nueva botella
	 * 
	 * @return La nueva botella
	 */
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
