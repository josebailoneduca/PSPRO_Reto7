package reto7_02b.b;

/**
 * Recoje las cajas llenas de la Fabrica, las coloca en el Almacen de la Fabrica
 * y pone una nueva caja vacia para que el embotellador la llene
 */
public class Empaquetador implements Runnable {
	/**
	 * Referencia a la fabrica
	 */
	Fabrica fabrica;
	
	/**
	 * Constructor
	 * @param fabrica Referencia a la fabrica
	 */
	public Empaquetador(Fabrica fabrica) {
		super();
		this.fabrica = fabrica;
	}

	@Override
	public void run() {
		while (true) {
			retirarCajaLlena();
			Estadistica.setEmpaquetador("Cambiando la caja");
			ponerCajaVacia();
		}

	}

	/**
	 * Crea una nueva caja vacia y la colocola en la Fabrica
	 */
	private void ponerCajaVacia() {
		Caja caja = new Caja(fabrica.idProximaCaja++);
		sleep(Config.T_PONER_CAJA);
		fabrica.ponerCajaVacia(caja);
		Estadistica.setEmpaquetador("Caja vacia " + caja.getId() + " puesta");
	}

	
	/**
	 * Retira la caja llena de la Fabrica y la coloca en el Almacen de la Fabrica
	 */
	private void retirarCajaLlena() {
		Estadistica.setEmpaquetador("Esperando para poder retirar caja llena");
		Caja caja = fabrica.retirarCajaLlena();
		Estadistica.setEmpaquetador("Retirando caja llena");
		sleep(Config.T_QUITAR_CAJA);
		fabrica.almacen.add(caja);
	}
	
	/**
	 * Duerme el hilo por un tiempo
	 * 
	 * @param ms Los milisegundos dormir el hilo
	 */	
	private void sleep(long ms) {
		try {
			Thread.currentThread().sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
