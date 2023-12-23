package reto7_08a.a;


/**
 * Hilo de mafioso. Tiene un atributo que define el ingrediente que ya tiene.
 * Tambien tiene una referencia a la mesa desde la cual coge los ingredientes que le faltan.
 * En su carrera tiene un bucle infinito en el que intenta coger los ingredientes qeu le faltan 
 * y cuando los obtiene se come el plato
 */
public class Mafioso extends Thread {
	/**
	 * Ingrediente que ya tiene el mafioso
	 */
	private Ingrediente ingrediente;
	
	/**
	 * Referencia a la mesa desde la que coger los ingredientes que le faltan
	 */
	private Mesa mesa;

	public Mafioso(Ingrediente ingrediente, Mesa mesa) {
		this.ingrediente = ingrediente;
		this.mesa = mesa;
	}

	@Override
	public void run() {
		while(true) {
			Estadistica.setEstado(ingrediente, Estadistica.ESPERANDO);
			//esperar hasta coger los ingredientes
			if (mesa.coger(ingrediente)) {
				Estadistica.setEstado(ingrediente, Estadistica.COMIENDO);
				//espera simulando que cocina y come el plato
				espera(Config.T_COCINAR);
				//avisar a la mesa para que ponga otros ingredientes
				mesa.avisar();
			}
		}
	}

	/**
	 * Espera durante un tiempo
	 * @param t El tiempo a esperar en ms
	 */
	private void espera(long t) {
		try {
			sleep(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	

}
