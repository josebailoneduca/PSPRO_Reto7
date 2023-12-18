package reto7_01.b;

/**
 * Monitor de acceso a la comida
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Comida {
	/**
	 * Plazas ocupadas actualmente en la comida
	 */
	int plazasOcupadas=0;
	
	/**
	 * Da acceso a la comida poniendo en espera los hilos 
	 * que intentan acceder mientras esten agotadas las plazas
	 */
	synchronized public void cogerSitio() {
		if (plazasOcupadas>=Config.PLAZAS_COMIDA)
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		plazasOcupadas++;
	}
	
	/**
	 * Deja libre una plaza para la comida y notifica a un hilo que este 
	 * esperando acceder a ella
	 */
	synchronized public void dejarSitio() {
		plazasOcupadas--;
		notify();
	}
	
}
