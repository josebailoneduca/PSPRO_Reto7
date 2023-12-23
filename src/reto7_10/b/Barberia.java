package reto7_10.b;

import java.util.LinkedList;

/**
 * <p>
 * Controla el flujo de ejecucion y espera de las hebras de clientes y barbero
 * llevando un seguimiento de sillas libres, estado del barbero, cantidad de clientes,
 * y cola de espera de los clientes.
 * </p>
 * <p>
 * El barbero debe acceder a este monitor en una secuencia ciclica de esperarCliente
 * </p>
 * <p>
 * Los clientes deben acceder a este monitor siguiendo la secuencia:<br>cogerPlaza->NO->fin <br>o<br> cogerPlaza->SI->esperarBarbero->liberarPlaza 
 * </p>
 * <p>
 * Cuando el barbero espera a un cliente el hilo espera en el monitor mientras no haya clientes.
 * Si pasa esa parte entonces saca el primer cliente de la cola de clientes esperando, se establece como
 * barbero libre y notifica al resto de hilos.
 * </p>
 * <p>
 * Cuando un cliente intenta conseguir plaza si no hay sillas libres termina. Si hay sillas libres se asigna 
 * una plaza al cliente, registrandolo en la cola, aumentando el numero de clientes en espera y disminuyendo 
 * la cantidad de sitios libre. Seguidamente se notifica a todos para despertar al barbero de ser necesario
 * </p>
 * <p>
 * Cuando un cliente espera al barbero se mantendra en espera mientras no este libre o se este apuntado
 * en la cola de clientes(cuando se va a ser atendido se sale de la cola). Tras la espera establece el barbero como ocupado
 * </p>
 * <p>
 * Cuando un cliente consigue que le vaya a atender el barbero deja una silla libre, establece el barbero como ocupado y 
 * disminuye el numero de clientes en espera
 * </p>
 * 

 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Barberia {
	
	/**
	 * Cantidad de sillas libres
	 */
	private int sillasLibres= Config.N_PLAZAS;
	
	/**
	 * Estado del barbero true puede trabajar, false esta ocupado
	 */
	private boolean barberoLibre= false;
	
	/**
	 * Numero de lcientes esperando
	 */
	private int nClientes=0;
	
	/**
	 * Lista de clientes esperando
	 */
	private LinkedList<Cliente> cola=new LinkedList<Cliente>();
	
	
	/**
	 * Asigna una plaza a un cliente, registrandolo en la cola, aumentando el numero 
	 * de clientes en espera y disminuyendo la cantidad de sitios libre.
	 * Notifica a todos para despertar al barbero de ser necesario
	 * 
	 * @return True si ha cogido plaza. False si no hay plazas disponibles
	 */
	public synchronized boolean cogerPlaza() {
		if (sillasLibres<1)
			return false;
		sillasLibres--;
		cola.add((Cliente)Thread.currentThread());
		nClientes++;
		notifyAll();
		return true;
	}
	
	/**
	 * Deja una silla libre porque se va a ser atendido por el barbero
	 * Establece el barbero como ocupado y disminuye el numero de clientes
	 * en espera
	 */
	public synchronized void liberarPlaza() {
		sillasLibres++;
		barberoLibre=false;
		nClientes--;
	}

	/**
	 * Espera mientras el barbero no este libre o se este apuntado
	 * en la cola de clientes(cuando se va a ser atendido se sale de la cola)
	 * Tras la espera establece el barbero como ocupado
	 */
	public synchronized void esperarBarbero() {
		while (!barberoLibre || cola.contains(Thread.currentThread()))
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		barberoLibre=false;
	}
	
	/**
	 * Mantiene el barbero dormido mientras no haya clientes.
	 * Si pasa esa parte entonces saca el primer cliente 
	 * de la cola de clientes esperando, se establece como
	 * barbero libre y notifica al resto de hilos
	 */
	public synchronized void esperarCliente() {
		while(nClientes==0)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		cola.pop();
		barberoLibre=true;
		notifyAll();
	}


	
}
