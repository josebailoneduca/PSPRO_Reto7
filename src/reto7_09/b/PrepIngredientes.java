package reto7_09.b;


/**
 * Preparador de ingredientes. Prepara un pack de ingredientes y lo coloca en la cesta de ingredientes.
 * 
 * Lo hace en un bulce infinito.
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class PrepIngredientes extends Thread {
	
	/**
	 * Ultima id asignada a un pack de ingredientes
	 */
	private int ultimaId =0;
	
	@Override
	public void run() {
		while(true) {
			//preparar ingredientes
			Estadistica.setEstado(Estadistica.PREP_INGREDIENTES, Estadistica.PREPARANDO);
			esperaPreparando();
			//ponerlos en la cesta
			Estadistica.setEstado(Estadistica.PREP_INGREDIENTES, Estadistica.ESPERANDO);
			Fabrica.ponerIngrediente(++ultimaId);
		}
	}
	
	/**
	 * Simula el tiempo de preparacion del pack de ingredientes
	 */
	public void esperaPreparando() {
		try {
			sleep(Config.T_PREPARA_INGREDIENTE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}