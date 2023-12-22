package reto7_03.b;


/**
 * Monitor de acceso a las plazas de comida
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Comida {
	
	/**
	 * Plazas de comida ocupadas
	 */
	private int plazasOcupadas=0;
	
	
	/**
	 * Adquiere una plaza para la comida esperando hasta que alguna quede libre.
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
	 * Libera una plaza de comida y notifica a una hebra de las que este esperando
	 */
	synchronized public void dejarSitio() {
		plazasOcupadas--;
		notify();
	}
	
}
