package reto7_12.a;

import java.util.concurrent.Semaphore;

/**
 * <p>
 * Buffer de capacidad finita con posibilidad para ser leido por multiples consumidores
 * a velocidades distintas.
 * </p>
 * <p>
 * El buffer en si es un array de elementos. Tiene establecido el sistema de productor/consumidor
 * controlado por semaforos teniendo un semaforo para controlar huecos disponibles y
 * tantos semaforos de elementos disponibles como consumidores.
 * </p>
 * <p>
 * Cuando el productor agrega un elemento senala los semaforos de elementos disponibles de todos
 * los consumidores y el cabezal de escritura avanza.
 *  * </p>
 * <p>
 * Cuando un consumidor consume un elemento este contabiliza la lectura. Si el elemento ha recibido
 * tantas lecturas como consumidores entonces el elemento es eliminado y el semaforo de huecos es senalado.
 * En cualquier caso el cabezal de lectura del consumidor en cuestion es movido.
 * </p>

 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Buffer {

	/**
	 * Semaforo de huecos
	 */
	private Semaphore sHuecos = new Semaphore(Config.TAM_BUFFER);

	/**
	 * Semaforo de mutex
	 */
	private Semaphore sExclusion = new Semaphore(1);

	/**
	 * Semaforos de elementos disponibles para cada consumidor
	 */
	private Semaphore[] sElementosDisponibles = new Semaphore[Config.N_CONSUMIDORES];

	/**
	 * Posiciones de lectura para cada consumidor
	 */
	private int[] posLecturas = new int[Config.N_CONSUMIDORES];

	/**
	 * Buffer de elementos
	 */
	private Elemento[] buffer = new Elemento[Config.TAM_BUFFER];

	/**
	 * Posicion de escritura
	 */
	private int posEscritura = 0;

	
	
	/**
	 * Constructor. Prepara el array de semaforos de elementos disponibles
	 */
	public Buffer() {
		for (int i = 0; i < Config.N_CONSUMIDORES; i++) {
			this.sElementosDisponibles[i] = new Semaphore(0);
		}
	}

	/**
	 * Agrega un elemento al buffer
	 * 
	 * @param el El elemento a agregar
	 */
	public void agregar(Elemento el) {

		// esperar al semaforo de los huecos libres
		try {
			sHuecos.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Estadistica.agregaBuffer(el.getId());
		// agregar el elemento
		buffer[posEscritura] = el;

		// desplazar la cabeza de escritura
		posEscritura = (posEscritura + 1) % Config.TAM_BUFFER;
		// avisar a todos los semaforos de los consumidores que hay un nuevo elemento
		for (Semaphore s : sElementosDisponibles) {
			s.release();
		}
	}

	
	/**
	 * Consume un elemento para un consumidor
	 * @param idConsumidor Id del consumidor
	 * 
	 * @return El elemento consumido
	 */
	public Elemento consumir(int idConsumidor) {
		//indice de lectura para el consumidor
		int posLectura = posLecturas[idConsumidor] % Config.TAM_BUFFER;
		
		//esperar al semaforo de elementos disponibles para el consumidor concreto
		Elemento leido = null;
		try {
			sElementosDisponibles[idConsumidor].acquire();
			
			//mutex para modificacion de elemento
			sExclusion.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//leer
		leido = buffer[posLectura];

		// incrementar la cantidad de lecturas del elemento
		// y senalar semaforo de huecos si se ha leido por todos los consumidores 
		//(si gastarLectura devuelve 0)
		if (leido.gastarLectura() == 0) {
			Estadistica.eliminaBuffer();
			sHuecos.release();
		}
		//fin de mutex
		sExclusion.release();
		//desplazar la cabeza de lectura para el consumidor concreto
		posLecturas[idConsumidor]++;
		
		//devolver el elemento leido
		return leido;
	}

}
