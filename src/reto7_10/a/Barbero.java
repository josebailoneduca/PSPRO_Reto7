package reto7_10.a;

/**
 * <p>
 * Hebra del barbero. Tiene un ciclo infinito con el siguiente comportamiento:
 * </p>
 * <p>
 * El barbero al inicio espera el semaforo de clientes. Cuando consigue pasar ese semaforo libera 
 * un permiso del semaforo de barberoLibre. Simula el cortado de pelo con una espera y vuelta a empezar.
 * </p>
 * 
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Barbero extends Thread {

	@Override
	public void run() {
		while(true) {
			//esperar a que haya clientes
			try {
				Estadistica.setEstadoBarbero(Estadistica.DURMIENDO);
				Barberia.clientes.acquire();
				Estadistica.setEstadoBarbero(Estadistica.TRABAJANDO);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//avisar de que el barbero esta disponible
			Barberia.barberoLibre.release();
			//simular el corte de pelo
			trabajar();
		}
	}
	
	
	/**
	 * Simula el tiempo de trabajo
	 */
	public void trabajar() {
		try {
			sleep(Config.T_TRABAJO);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
