package reto7_09.a;

/**
 * Preparador de panecillos. Crea un panecillo y lo coloca en la cesta de panecillos
 * Lo hace en un bulce infinito.
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class PrepPanecillos extends Thread {
	
	/**
	 * Ultima id asignada a un panecillo
	 */
	private int ultimaId = 0;

	@Override
	public void run() {
		while (true) {
			//preparar panecillo
			Estadistica.setEstado(Estadistica.PREP_PANECILLOS, Estadistica.PREPARANDO);
			esperaPreparando();
			
			//ponerlo en la cesta
			Estadistica.setEstado(Estadistica.PREP_PANECILLOS, Estadistica.ESPERANDO);
			Fabrica.ponerPanecillo(++ultimaId);
		}
	}

	
	/**
	 * Simular tiempo de preparacion de un panecillo
	 */
	public void esperaPreparando() {
		try {
			sleep(Config.T_PREPARA_PANECILLO);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
