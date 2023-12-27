/**
 * 
 */
package reto7_13.a;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

/**
 * <p>
 * Controla el flujo de entrada y salida de viajeros del bote. En la orilla
 * origen carga viajeros y los va dejando en la orilla destino siguiendo unas
 * normas para garantizar que no se quede nadie sin viajar. Cuando ya no quedan
 * viajeros que trasladar el programa termina.
 * </p>
 * <p>
 * El flujo de entrada y salida de viajeros del bote se controla con dos
 * semaforos para matematicos (entrada y salida del bote) y dos semaforos para
 * no matematicos (entrada y salida del bote). Esos semaforos son esperados por
 * los viajeros y son abiertos por el propio bote segun las necesidades.
 * </p>
 * <p>
 * Ademas hay otro semaforo que se abre cuando se ha llenado el bote y cuando se
 * han bajado los viajeros en el destino. Ese semaforo lo espera el bote entre
 * viajes y lo abre el ultimo viajero en subir o en bajar.
 * </p>
 * <p>
 * Por norma general el criterio para subida de viajeros al bote o de bajada de
 * viajeros del bote es aleatoria siempre que se cumpla que en el bote no hay 2
 * matematicos y un matematico.
 * </p>
 * <p>
 * Para asegurar que esa restriccion se pueda cumplir cuando quedan pocos
 * viajeros, se controlan ciertas condiciones de quien vuelve con el bote a por
 * mas viajeros y quien sube en el bote en las siguientes condiciones:
 * 
 * 
 * <p>
 * SELECCION DE LA SUBIDA DE PASAJEROS
 * </p>
 * <ul>
 * <li>Si quedan 2 NO matematicos por subir al bote y en el bote hay un
 * matematico: Deben subir ambos NO matematicos</li>
 * 
 * <li>Si quedan 2 NO matematicos por subir al bote, algun matematico esperando
 * y en el bote hay un NO matematico: Sube 1 matematico y 1 no matematico (para
 * evitar una vuelta con un NO matematico solitario)</li>
 * 
 * <li>Si queda 1 NO matematico por subir: Debe subir 1 NO metematico (otra regla asegura que a
 * por el ha tenido que volver 1 NO matematico)</li>
 * 
 * <li>Si hay 1 matematico en el bote, queda 1 matematico esperando y quedan NO
 * matematicos esperando: Entra 1 NO matematico para evitar el bloqueo</li>
 * 
 * <li>En cualquier otro caso suben al azar conservando la restriccion de no
 * poder haber 2 matematicos y 1 NO matematico</li>
 * </ul>
 * 
 * 
 * <p>
 * SELECCION DE QUE PASAJERO HACE LA VUELTA
 * </p>
 * <ul>
 * <li>Si no hay nadie esperando a subir al bote entonces bajan todos y el
 * programa termina</li>
 * 
 * <li>Si solo quedan matematicos esperando vuelve 1 matematico</li>
 * 
 * <li>Si solo queda 1 NO matematico esperando vuelve 1 NO matematico</li>
 * 
 * <li>En cualquier otra situacion vuelve cualquiera</li>
 * </ul>
 * </p>
 * 
 * @author Jose Javier Bailon Ortiz
 * @see Viajero
 */
public class Bote extends Thread {

	/**
	 * Semaforo de control de entrada de matematicos en el bote
	 */
	private final Semaphore sEntradaMat = new Semaphore(0);

	/**
	 * Semaforo de control de entrada de NO matematicos en el bote
	 */
	private final Semaphore sEntradaNoMat = new Semaphore(0);

	/**
	 * Semaforo de salida de matematicos del bote
	 */
	private final Semaphore salidaMat = new Semaphore(0);

	/**
	 * Semaforo de salida de NO matematicos del bote
	 */
	private final Semaphore salidaNoMat = new Semaphore(0);

	/**
	 * Semaforo de control de los viajes
	 */
	private final Semaphore sDarViaje = new Semaphore(0);

	/**
	 * True si el bote estan en la orilla de la que recoge viajeros y False si esta
	 * en la orilla de destino
	 */
	private boolean enOrillaOrigen = true;

	/**
	 * True si ya se han trasladado todos los viajeros. False si no se han
	 * trasladado todos
	 */
	private boolean terminado = false;

	/**
	 * Semaforo de MUTEX para modificar las listas
	 */
	private final Semaphore mutexListas = new Semaphore(1);

	/**
	 * Lista de matematicos en el bote
	 */
	private final LinkedList<Viajero> pasajerosMat = new LinkedList<Viajero>();

	/**
	 * Lista de no matematicos en el bote
	 */
	private final LinkedList<Viajero> pasajerosNoMat = new LinkedList<Viajero>();

	/**
	 * Lista de matematicos esperando a entrar al bote
	 */
	private final LinkedList<Viajero> esperandoMat = new LinkedList<Viajero>();

	/**
	 * Lista de no matematicos esperando a entrar al bote
	 */
	private final LinkedList<Viajero> esperandoNoMat = new LinkedList<Viajero>();

	/**
	 * Lista de matematicos ya trasladados
	 */
	private final LinkedList<Viajero> trasladadosMat = new LinkedList<Viajero>();

	/**
	 * Lista de no matematicos ya trasladados
	 */
	private final LinkedList<Viajero> trasladadosNoMat = new LinkedList<Viajero>();

	/**
	 * Para generar decisiones aleatorias
	 */
	private final Random r = new Random();

	
	
	
	
	
	
	/**
	 * La carrera consiste en un bucle de llenar el bote en una orilla ir a la otra
	 * y descargar viajeros hasta que no queden viajeros.
	 */
	@Override
	public void run() {

		// Dejar entrar a un viajero al principio
		if (esperandoMat.size() > 0)
			sEntradaMat.release();
		else
			sEntradaNoMat.release();
		actualizaPantalla();

		// Bucle de dar viajes mientras no se haya terminado de trasladar todos los
		// pasajeros
		// Cada vuelta del bucle va en un sentido hacia la ida o hacia la vuelta
		while (!terminado) {

			try {
				actualizaPantalla();
				sleep(Config.DURACION_VIAJE);

				// Segun la orilla carga o descarga viajeros
				if (enOrillaOrigen) {
					cargarViajeros();
				} else {
					descargarViajeros();
				}
				// espera la orden de zarpar si no se ha terminado
				if (!terminado)
					sDarViaje.acquire();
				actualizaPantalla();
				sleep(Config.DURACION_VIAJE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// intercambiar la orilla en la que se esta
			enOrillaOrigen = !enOrillaOrigen;

		}

		// si se llega aqui se han trasladado todos los viajeros y el programa termina
		System.out.println("TERMINADO");
		System.exit(0);
	}

	
	
	
	
	/**
	 * Metodo usado por los viajeros para acceder al bote.
	 * El viajero se registra como que quiere viajar y se queda en espera hasta que
	 * se le de paso
	 */
	public void subir() {
		// datos del viajero
		Viajero v = (Viajero) Thread.currentThread();
		boolean matematico = v.isMatematico();

		// registrarlo como viajero en espera
		registrarViajeroEnEspera(v);

		// esperar a tener paso para entrar
		try {
			if (matematico)
				sEntradaMat.acquire();
			else
				sEntradaNoMat.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// registrar como parte del pasaje
		registrarPasajeroEnBote(v);
		actualizaPantalla();
		// avisar al bote para zarpar
		zaparSiEstaLleno();
	}

	
	
	
	
	
	/**
	 * Metodo usado por los viajeros para bajar del bote.
	 * Se queda en espera hasta que se le de paso
	 */
	public void bajar() {

		Viajero v = (Viajero) Thread.currentThread();
		// esperar a poder salir del bote
		try {
			if (v.isMatematico())
				salidaMat.acquire();
			else
				salidaNoMat.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//si ha salido del bote se registra el cambio de lista
		try {
			mutexListas.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (v.isMatematico()) {
			pasajerosMat.remove(v);
			trasladadosMat.add(v);
		} else {
			pasajerosNoMat.remove(v);
			trasladadosNoMat.add(v);
		}
		//zarpar si se han bajado ya todos los que tenian que bajar
		zarparSiHanBajado();
		
		mutexListas.release();
		actualizaPantalla();
	}

	
	
	
	/**
	 * Rellena las plazas del siguiente viaje de manera que no quede ningun viajero
	 * sin poder realizar el viaje. Implementa las condiciones expuestas en la descripcion
	 * de la clase.
	 */
	private void cargarViajeros() {
		// de por si la primera plaza esta ocupada ya que al inicio de la carrera del
		// bote o a la vuelta de un viaje ya esta ocupada una plaza
		int plazasOcupadas = 1;
	
		// viajeros esperando en la orilla de salida
		int nMatematicosEsp = esperandoMat.size();
		int nNoMatematicosEsp = esperandoNoMat.size();
		int totalEspera = nMatematicosEsp + nNoMatematicosEsp;
	
		// viajeros ya en el bote
		int matematicosEnBote = pasajerosMat.size();
		int noMatematicosEnBote = pasajerosNoMat.size();
	
		if (nNoMatematicosEsp == 2) {
			// si ha vuelto un matematico deben subir los dos
			if (matematicosEnBote == 1) {
				sEntradaNoMat.release(2);
				plazasOcupadas += 2;
				noMatematicosEnBote += 2;
				nNoMatematicosEsp -= 2;
				// si ha vuelto un no matematico debe subir 1 si matematicos
				// aparte de esos dos no matematicos que esperan
			} else if (totalEspera > 2) {
				sEntradaNoMat.release();
				plazasOcupadas++;
				noMatematicosEnBote++;
				nNoMatematicosEsp--;
				sEntradaMat.release();
				plazasOcupadas++;
				matematicosEnBote++;
				nMatematicosEsp--;
	
			}
			// si solo queda un no matematico esperando debe asegurarse viajar
			// (habra vuelto un no matematico por las reglas de la vuelta)
		} else if (nNoMatematicosEsp == 1) {
			sEntradaNoMat.release();
			plazasOcupadas++;
			noMatematicosEnBote++;
			nNoMatematicosEsp--;
	
			// si ha vuelto un matematico y solo queda esperando un matematico y otros no
			// matematicos
			// debe entrar un no matematico ya que si entra el matematico qeu espera no
			// podra entrar mas
		} else if (matematicosEnBote == 1 && nMatematicosEsp == 1 && totalEspera > 1) {
			sEntradaNoMat.release();
			plazasOcupadas++;
			noMatematicosEnBote++;
			nNoMatematicosEsp--;
		}
	
		// mientras no se haya llenado el bote y no se hayan gastado los
		// posibles viajeros
		while (plazasOcupadas < 3 && plazasOcupadas < totalEspera + 1) {
			// si hay un no matematico en el bote debe haber otro no matematico
			if (noMatematicosEnBote == 1) {
				sEntradaNoMat.release();
				plazasOcupadas++;
				noMatematicosEnBote++;
				nNoMatematicosEsp--;
				// si hay dos matematicos en el bote debe haber otro matematico
			} else if (matematicosEnBote == 2) {
				sEntradaMat.release();
				plazasOcupadas++;
				matematicosEnBote++;
				nMatematicosEsp--;
				// en cualquier otro caso dar paso a uno al azar
			} else {
				boolean subirMatematico = r.nextBoolean();
				if (subirMatematico && nMatematicosEsp > 0) {
					sEntradaMat.release();
					plazasOcupadas++;
					matematicosEnBote++;
					nMatematicosEsp--;
				} else if (nNoMatematicosEsp > 0) {
					sEntradaNoMat.release();
					plazasOcupadas++;
					noMatematicosEnBote++;
					nNoMatematicosEsp--;
				}
			}
		}
	}

	
	
	
	
	
	
	
	/**
	 * Baja los viajeros para la proxima vuelta siguiendo las condiciones expuestas en la descripcion
	 * de la clase.
	 */
	private void descargarViajeros() {
		
		//contadores
		int nMatematicos = pasajerosMat.size();
		int nNoMatematicos = pasajerosNoMat.size();
		int nEsperandoMat = esperandoMat.size();
		int nEsperandoNoMat = esperandoNoMat.size();
		int totalEsperando = nEsperandoMat + nEsperandoNoMat;

		// si no quedan esperando no vuelve nadie
		if (totalEsperando == 0) {
			salidaMat.release(nMatematicos);
			salidaNoMat.release(nNoMatematicos);
			// solo quedan matematicos
		} else if (nEsperandoNoMat == 0) {
			// vover matematico
			salidaMat.release(nMatematicos - 1);
			salidaNoMat.release(nNoMatematicos);

			// si solo queda un no matematico esperando debe volver un no matematico
		} else if (nEsperandoNoMat == 1) {
			// volver no matematico
			salidaMat.release(nMatematicos);
			salidaNoMat.release(nNoMatematicos - 1);
			System.out.println("Solo qeuda un matematico");
			
			// en cualquier otra situacion que vuelva cualquiera
		} else {
			// mientras haya mas de 1 pasajero o no quede nadie esperando a viajar y haya
			// algun pasajero se saca del bote uno al azar en cada vuelta del bucle
			while (nMatematicos + nNoMatematicos > 1 || (totalEsperando == 0 && (nMatematicos + nNoMatematicos) > 0)) {
				if (r.nextBoolean() && nMatematicos > 0) {
					salidaMat.release();
					nMatematicos--;
				} else if (nNoMatematicos > 0) {
					salidaNoMat.release();
					nNoMatematicos--;
				}
			}
		}

 
			
	}

	/**
	 * Ordena zarpar si el bote esta lleno o no queda nadie esperando
	 */
	private void zaparSiEstaLleno() {
		try {
			mutexListas.acquire();
			if (pasajerosMat.size() + pasajerosNoMat.size() == 3 || esperandoMat.size() + esperandoNoMat.size() == 0)
				sDarViaje.release();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		mutexListas.release();
	}
	
	/**
	 * Ordena zarpar si ya han bajado los pasajeros
	 * y comprueba si hay que terminar por no haber nadie 
	 * en bote ni esperando 
	 */
	private void zarparSiHanBajado() {

		int pasajeros = pasajerosMat.size() + pasajerosNoMat.size();
		int esperando = esperandoMat.size() + esperandoNoMat.size();
		//si es el ultimo que debe bajar se da la orden de zarpar
		if ( pasajeros == 1 && esperando>0)
			sDarViaje.release();
		//si han bajado todos y no hay nadie esperando remina
		if (pasajeros==0 && esperando==0) 
			terminado = true;

	}
	
	
	/**
	 * Registra un Viajero como parte de los que hay en el bote
	 * 
	 * @param v El viajero
	 */
	private void registrarPasajeroEnBote(Viajero v) {
		try {
			mutexListas.acquire();
			if (v.isMatematico()) {
				esperandoMat.remove(v);
				pasajerosMat.add(v);
			} else {
				esperandoNoMat.remove(v);
				pasajerosNoMat.add(v);
			}
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		mutexListas.release();
		actualizaPantalla();
	}

	/**
	 * Registra un viajero como viajero esperando a subir al bote
	 * 
	 * @param v El viajero
	 */
	private void registrarViajeroEnEspera(Viajero v) {
		try {
			mutexListas.acquire();
			if (v.isMatematico())
				esperandoMat.add(v);
			else
				esperandoNoMat.add(v);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		mutexListas.release();
	}

	
	/**
	 * Actualiza los datos a mostrar en pantalla
	 */
	private void actualizaPantalla() {
		try {
			mutexListas.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Estadistica.enBote(pasajerosMat.stream().map(Object::toString).collect(Collectors.joining(",")),
				pasajerosNoMat.stream().map(Object::toString).collect(Collectors.joining(",")));
		Estadistica.espera(esperandoMat.stream().map(Object::toString).collect(Collectors.joining(",")),
				esperandoNoMat.stream().map(Object::toString).collect(Collectors.joining(",")));
		Estadistica.traladados(trasladadosMat.stream().map(Object::toString).collect(Collectors.joining(",")),
				trasladadosNoMat.stream().map(Object::toString).collect(Collectors.joining(",")));
		Estadistica.enOrigen(enOrillaOrigen);
		mutexListas.release();
	}
}
