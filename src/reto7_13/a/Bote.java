/**
 * 
 */
package reto7_13.a;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

/**
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Bote extends Thread {

	/**
	 * Semaforo de control de entrada
	 */
	public final Semaphore entradaMat = new Semaphore(0);
	public final Semaphore entradaNoMat = new Semaphore(0);

	/**
	 * Semaforo de control de los viajes
	 */
	private boolean enOrillaOrigen = true;
	private Semaphore darViaje = new Semaphore(0);
	private boolean terminado = false;

	/**
	 * Semaforo de salida de matematicos
	 */
	public final Semaphore salidaMat = new Semaphore(0);
	public final Semaphore salidaNoMat = new Semaphore(0);

	/**
	 * Lista de viajeros en el bote
	 */
	public final LinkedList<Viajero> pasajerosMat = new LinkedList<Viajero>();
	public final LinkedList<Viajero> pasajerosNoMat = new LinkedList<Viajero>();

	/**
	 * Lista de viajeros esperando en la salida
	 */
	public final LinkedList<Viajero> esperandoMat = new LinkedList<Viajero>();
	public final LinkedList<Viajero> esperandoNoMat = new LinkedList<Viajero>();

	/**
	 * Lista de viajeros ya trasladados
	 */
	public final LinkedList<Viajero> trasladadosMat = new LinkedList<Viajero>();
	public final LinkedList<Viajero> trasladadosNoMat = new LinkedList<Viajero>();

	/**
	 * Semaforo de MUTEX de las listas
	 */
	public final Semaphore mutexListas = new Semaphore(1);
	private final Random r = new Random();

	@Override
	public void run() {

		// Dejar entrar a uno al principio
		if (esperandoMat.size() > 0)
			entradaMat.release();
		else
			entradaNoMat.release();
		actualizaPantalla();
		
		
		// dar viajes
		while (!terminado) {

			try {
				actualizaPantalla();
				sleep(Config.DURACION_VIAJE);
				if (enOrillaOrigen) {
					rellenarPlazasDelViaje();
				} else {
					gestionarLlegadaADestino();
				}
				darViaje.acquire();
				actualizaPantalla();
				sleep(Config.DURACION_VIAJE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			enOrillaOrigen = !enOrillaOrigen;

		}

		System.out.println("TERMINADO");
		System.exit(0);
	}

	/**
	 * Gestiona quien tiene que bajar de la barca
	 */
	private void gestionarLlegadaADestino() {
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
			// mientras haya mas de 1 pasajero o no quede nadie esperando y haya algun pasajero
			// sale del bote uno al azar en queda vuelta del bucle
			while (nMatematicos + nNoMatematicos > 1
					|| (totalEsperando == 0 && (nMatematicos + nNoMatematicos) > 0)) {
				if (r.nextBoolean() && nMatematicos > 0) {
					salidaMat.release();
					nMatematicos--;
				} else if (nNoMatematicos > 0) {
					salidaNoMat.release();
					nNoMatematicos--;
				}
			}
		}

		//si no quedan viajeros esperando se termina el programa
		if (totalEsperando == 0)
			terminado = true;
	}

	/**
	 * Rellena las plazas del siguiente viaje de manera que no quede ningun viajero
	 * sin poder realizar el viaje
	 */
	private void rellenarPlazasDelViaje() {
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

		// si hay 3 NO matematicos esperando deben subir 2 no matematicos
		if (nNoMatematicosEsp == 3) {
			entradaNoMat.release(2);
			plazasOcupadas += 2;
			noMatematicosEnBote += 2;
			nNoMatematicosEsp-=2;
			
			// si hay 2 NO matematicos esperando
		}else if(nNoMatematicosEsp == 2) {
			//si ha vuelto un matematico deben subir los dos
			if (matematicosEnBote==1) {
				entradaNoMat.release(2);
				plazasOcupadas+=2;
				noMatematicosEnBote+=2;
				nNoMatematicosEsp-=2;		
			//si ha vuelto un no matematico debe subir 1 si matematicos
			//aparte de esos dos no matematicos que esperan
			}else if (totalEspera>2){
				entradaNoMat.release();
				plazasOcupadas++;
				noMatematicosEnBote++;
				nNoMatematicosEsp--;
				entradaMat.release();
				plazasOcupadas++;
				matematicosEnBote++;
				nMatematicosEsp--;
				
			}
			// si solo queda un no matematico esperando debe asegurarse viajar
			// (habra vuelto un no matematico por las reglas de la vuelta)
		} else if (nNoMatematicosEsp == 1) {
			entradaNoMat.release();
			plazasOcupadas++;
			noMatematicosEnBote++;
			nNoMatematicosEsp--;
 
	    //si ha vuelto un matematico y solo queda esperando un matematico y otros no matematicos 
		//debe entrar un no matematico ya que si entra el matematico qeu espera no podra entrar mas
		}else if (matematicosEnBote==1&&nMatematicosEsp==1 && totalEspera>1) {
			entradaNoMat.release();
			plazasOcupadas++;
			noMatematicosEnBote++;
			nNoMatematicosEsp--;
		}

		// mientras no se haya llenado el bote y no se hayan gastado los
		// posibles viajeros
		while (plazasOcupadas < 3 && plazasOcupadas < totalEspera + 1) {
			// si hay un no matematico en el bote debe haber otro no matematico
			if (noMatematicosEnBote == 1) {
				entradaNoMat.release();
				plazasOcupadas++;
				noMatematicosEnBote++;
				nNoMatematicosEsp--;
				// si hay dos matematicos en el bote debe haber otro matematico
			} else if (matematicosEnBote == 2) {
				entradaMat.release();
				plazasOcupadas++;
				matematicosEnBote++;
				nMatematicosEsp--;
				// en cualquier otro caso dar paso a uno al azar
			} else {
				boolean subirMatematico = r.nextBoolean();
				if (subirMatematico && nMatematicosEsp > 0) {
					entradaMat.release();
					plazasOcupadas++;
					matematicosEnBote++;
					nMatematicosEsp--;
				} else if (nNoMatematicosEsp > 0) {
					entradaNoMat.release();
					plazasOcupadas++;
					noMatematicosEnBote++;
					nNoMatematicosEsp--;
				}
			}
		}
	}

	/**
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
				entradaMat.acquire();
			else
				entradaNoMat.acquire();
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
	 * 
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
		if (pasajerosMat.size() + pasajerosNoMat.size() == 1)
			darViaje.release();
		mutexListas.release();
		actualizaPantalla();

	}

	/**
	 * Ordena zarpar si el bote esta lleno o no queda nadie esperando
	 */
	private void zaparSiEstaLleno() {
		try {

			mutexListas.acquire();

			if (pasajerosMat.size() + pasajerosNoMat.size() == 3
					|| esperandoMat.size() + esperandoNoMat.size() == 0)
				darViaje.release();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		mutexListas.release();
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
	 * @param v
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
