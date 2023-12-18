package reto7_01.b;

/**
 * Monitor de acceso al columpio
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Columpio {
	/**
	 * Plazas ocupadas actualmente en el columpio
	 */
	private int plazasOcupadas=0;
	
	/**
	 * Da acceso al columpio poniendo en espera los hilos 
	 * que intentan acceder mientras este el columpo ocupado
	 */
	synchronized public void cogerSitio() {
		if (plazasOcupadas>=Config.PLAZAS_COLUMPIO)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		plazasOcupadas++;
	}
	
	/**
	 * Deja libre el columpio y notifica a un hilo que este 
	 * esperando acceder a el
	 */
	synchronized public void dejarSitio() {
		plazasOcupadas--;
		notify();
	}
	
}
