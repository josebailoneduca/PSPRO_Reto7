package reto7_12.b;



/**
 * <p>
 * Buffer de capacidad finita con posibilidad para ser leido por multiples consumidores
 * a velocidades distintas.
 * </p>
 * <p>
 * El buffer en si es un array de elementos. Tiene establecido el sistema de productor/consumidor
 * controlado como monitor con contadores, posicion de escritura para el productor y posiciones 
 * de lectura para cada consumidor.
 * </p>
 * <p>
 * Cuando el productor agrega un elemento incrementa los contadores de elmeentos disponibles de cada consumidor
 * y el cabezal de escritura avanza.
 * </p>
 * <p>
 * Cuando un consumidor consume un elemento este contabiliza la lectura. Si el elemento ha recibido
 * tantas lecturas como consumidores entonces el elemento se considera terminado y la cantidad de huecos 
 * disponibles se incrementa.
 * En cualquier caso el cabezal de lectura del consumidor en cuestion es movido.
 * </p>

 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Buffer {

	/**
	 * Huecos disponibles
	 */
	private int huecos = Config.TAM_BUFFER;

	/**
	 * Numero de elementos disponibles para cada consumidor
	 */
	private int[] elementosDisponibles = new int[Config.N_CONSUMIDORES];

	/**
	 * Array de elementos que conforman el buffer
	 */
	private Elemento[] buffer = new Elemento[Config.TAM_BUFFER];

	/**
	 * Posicion de escritura
	 */
	private int posEscritura = 0;

	/**
	 * Array con las posiciones de lectura para cada consumidor
	 */
	private int[] posLecturas = new int[Config.N_CONSUMIDORES];

	/**
	 * Agregar un elemento. Si no hay huecos espera.
	 * Una vez agregado un elemento incrementa la 
	 * cantidad de elementos disponibles para cada consumidor
	 * y notifica a las hebras en espera
	 * 
	 * @param el El elemento a agregar
	 */
	public synchronized void agregar(Elemento el) {

		// mientras no haya huecos esperar
		while (huecos == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Estadistica.agregaBuffer(el.getId());
		//escribir
		buffer[posEscritura] = el;
		
		//reducir el numero de huecos disponibles
		huecos--;
		//mueve la cabeza de escritura
		posEscritura = (posEscritura + 1) % Config.TAM_BUFFER;
		
		//incrementa en 1 los elementos disponibles para cada consumidor
		for (int i = 0; i < elementosDisponibles.length; i++) {
			elementosDisponibles[i]++;
		}
		//notificar a los hilos en espera
		notifyAll();
	}

	
	/**
	 * Lee y consume un elemento por parte de un consumidor
	 * 
	 * @param idConsumidor Id del consumidor
	 * 
	 * @return El elemento leido
	 */
	public synchronized Elemento consumir(int idConsumidor) {
		//posicion de lectura para este consumidor concreto
		int posLectura = posLecturas[idConsumidor] % Config.TAM_BUFFER;
		Elemento leido = null;
		
		//esperar mientras no haya elementos disponibles para este consumidor
		while (elementosDisponibles[idConsumidor] == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		//leer
		leido = buffer[posLectura];
		//incrementar el contador de lecturas del elemento
		//Si no quedan lecturas disponibles aunmenta en 1 la cantidad de huecos disponibles
		if (leido.gastarLectura() == 0) {
			Estadistica.eliminaBuffer();
			huecos++;
		}

		//decrementa la cantidad de elementos disponibles para el consumidor
		elementosDisponibles[idConsumidor]--;
		//mueve la cabeza de lectura del consumidor
		posLecturas[idConsumidor]++;
		
		//notifica a los hilos en espera
		notifyAll();
		
		//devuelve el elemento leido
		return leido;
	}

}
