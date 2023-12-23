package reto7_10.b;

/**
 * <p>
 * Hebra del barbero. Tiene un ciclo infinito con los pasos de esperar cliente y trabajar 
 * </p>
 * 
 * 
 * @author Jose Javier Bailon Ortiz
 * @see Barberia
 */
public class Barbero extends Thread {
	
	/**
	 * Referencia a la barberia
	 */
	private Barberia barberia;
	
	/**
	 * Constructor
	 * 
	 * @param barberia Referencia a la barberia
	 */
	public Barbero(Barberia barberia) {
		this.barberia = barberia;
	}



	@Override
	public void run() {
		while(true) {
 				Estadistica.setEstadoBarbero(Estadistica.DURMIENDO);
				barberia.esperarCliente();
				Estadistica.setEstadoBarbero(Estadistica.TRABAJANDO);
 				trabajar();
 				Estadistica.setEstadoBarbero(Estadistica.DURMIENDO);
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
