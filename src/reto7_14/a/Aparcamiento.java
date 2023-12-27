/**
 * 
 */
package reto7_14.a;

import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * <p>
 * Aparcamiento es un monitor que lleva un seguimiento de los coches que entran
 * y salen del aparcamiento. Utiliza tres listas:
 * <ul>
 * <li>Una cola completa de todos los coches(residentes y no residentes)
 * esperando a entrar</li>
 * <li>Una cola con solo los residentes que esperan a entrar</li>
 * <li>Un listado de coches en el interior del aparcamiento</li>
 * </ul>
 * </p>
 * <p>
 * Ademas lleva un seguimiento de las plazas libres que quedan en el
 * aparcamiento.
 * </p>
 * <p>
 * Cuando un coche intenta entrar se le adjudica un numero de ticket.
 * Tras eso debe pasar la comprobacion de que quedan plazas libres y de que su ticket
 * es al que le toca entrar.
 * </p>
 * <p>
 * Elcritero para decidir a que ticket le toca entrar al aparcamiento es el siguiente:
 * <ul>
 * <li>Si la cantidad de residentes esperando es mayor o igual que la cantidad de plazas libres el ticket sera
 * el del residente que lleva mas tiempo esperando.</li>
 * <li>En otro caso le toca al coche que lleve mas tiempo esperando sea residente o no</li>
 * </ul>
 * </p>
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Aparcamiento {

	/**
	 * Plazas restantes en el aparcamiento
	 */
	private int plazasRestantes = Config.PLAZAS_TOTALES;
	
	/**
	 * Proximo numero de ticket a asignar
	 */
	private int proximoNumeroTicket = 0;
	
	/**
	 * Numero de ticket al que se le permitira la entrada en cuanto haya plaza disponible
	 */
	private int proximoEnEntrar = 0;
	
	/**
	 * Cola total de coches que esperan a entrar (tanto residentes como no residentes)
	 */
	private LinkedList<Coche> cola = new LinkedList<Coche>();
	
	/**
	 * Cola de residentes que esperan a entrar
	 */
	private LinkedList<Coche> colaResidentes = new LinkedList<Coche>();
	
	/**
	 * Lista de coches que hay dentro del aparcamiento
	 */
	private LinkedList<Coche> cochesDentro = new LinkedList<Coche>();

	/**
	 * Metodo utilizado por los coches para entrar al aparcamiento.
	 * Al entrar se le adjudica un numero de ticket.
	 * Luego comprobara si su numero de ticket es al que le toca entrar.
	 * Si el numero de ticket no es el mismo o no quedan plazas queda en espera
	 * 
	 * @param c Coche que intenta entrar en el aparcamiento
	 */
	public synchronized void entrar(Coche c) {

		// coger numero
		c.setNumeroTicket(proximoNumeroTicket++);
		// agregar a la cola de espera
		cola.add(c);

		// agregar tambien a la cola de espera de ridentes si toca
		if (c.isResidente())
			colaResidentes.add(c);

		// actualizar el numero de ticket del proximo coche al que se permitira entrar
		establecerProximaEntrada();
		actualizarPantalla();

		// Esperar mientras el numero que debe entrar no es el propio o no quedan plazas
		while (proximoEnEntrar != c.getNumeroTicket() || plazasRestantes < 1)
			esperar();
		
		//salir de la cola de espera
		cola.remove(c);
		
		//salir de la cola de residentes si toca
		if (c.isResidente())
			colaResidentes.remove(c);
		
		// actualizar el numero de ticket del proximo coche al que se permitira entrar
		establecerProximaEntrada();
		
		//agregar a la lista de coches que hay dentro del Aparcamiento
		cochesDentro.add(c);

		//decrementar las plazas disponibles
		plazasRestantes--;
		actualizarPantalla();
	}

	
	/**
	 * Metodo usado por los coches para salir del aparcamiento.
	 * Se retira de la lidata de coches en el aparcamiento, se incrementan las plazas disponibles
	 * y  se actualiza el numero de ticket al que le toca entrar al aparcamiento.
	 * Tras eso notifica a todos los hilos.
	 * 
	 * @param c Coche que sale del aparcamiento
	 */
	public synchronized void salir(Coche c) {
		cochesDentro.remove(c);
		plazasRestantes++;
		establecerProximaEntrada();
		actualizarPantalla();
		notifyAll();
	}

	/**
	 * Define el ticket que sera el proximo que pueda entrar al aparcamiento.
	 * Si no hay coches se pone en -1. Si hay coches se elije al primero de la cola
	 * excepto si las plazas restantes son menores o iguales que la cantidad de residentes esperando.
	 * En ese caso el ticket para entrar es el del primer coche residente. 
	 */
	private synchronized void establecerProximaEntrada() {
		proximoEnEntrar = -1;
		Coche c = null;
		if (colaResidentes.size() >= plazasRestantes)
			c = colaResidentes.peekFirst();
		else
			c = cola.peekFirst();
		if (c != null)
			proximoEnEntrar = c.getNumeroTicket();
	}

	/**
	 * Actualiza los datos de listas de coches y plazas restantes para que se muestre en pantalla.
	 */
	private synchronized void actualizarPantalla() {
		Estadistica.actualizaListas(cola.stream().map(Object::toString).collect(Collectors.joining(", ")),
				colaResidentes.stream().map(Object::toString).collect(Collectors.joining(", ")),
				cochesDentro.stream().map(Object::toString).collect(Collectors.joining(", ")), plazasRestantes);
	}


	/**
	 * Pone el hilo actual en espera.
	 */
	private synchronized void esperar() {
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
