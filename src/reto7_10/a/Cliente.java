package reto7_10.a;


/**
 * <p>
 * Hebra del cliente. Tiene una carrera finita con el siguiente comportamiento:
 * </p>
 * <p>
 * El cliente intenta esperar el semaforo de sillasLibres. Si no puede hacerlo de manera inmediata desiste y termina. 
 * Si consigue una silla libre libera un permiso en el semaforo "clientes" para avisar de que hay un cliente esperando. 
 * Seguidamente se pone a esperar al semaforo de barberoLibre. Cuando consigue pasar ese semaforo libera un permiso del 
 * semaforo de sillasLibres.
 * </p>
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Cliente extends Thread {
	
	/**
	 * Id del cliente
	 */
	private int id;

	/**
	 * Constructor
	 * @param id Id del cliente
	 */
	public Cliente(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		//mirar si hay sitios libres
		if (Barberia.sillasLibres.tryAcquire()) {
			try {
				Estadistica.esperar();
				//avisar de que hay un nuevo cliente
				Barberia.clientes.release();
				
				//esperar a que el barbero este libre
				Barberia.barberoLibre.acquire();
				Estadistica.dejarDeEsperar();
				
				//dejar una silla libre
				Barberia.sillasLibres.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			//si no hay sitios libres irse
			Estadistica.desistir();
		}
	}

	@Override
	public String toString() {
		return "Cliente " + id + "";
	}
	
	
}