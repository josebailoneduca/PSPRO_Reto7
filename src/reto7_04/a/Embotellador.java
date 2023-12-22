package reto7_04.a;

/**
 * Productor de botellas. Las va colocando en la caja vacia.
 * Tiene como carrera un bucle infinito de llenar botella y ponerla en la caja
 * 
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Embotellador implements Runnable {

	/**
	 * Identificador del embotellador
	 */
	private int id;
	
	
	/**
	 * Constructor
	 * @param id Id del embotellador
	 */
	public Embotellador(int id) {
		this.id = id;
	}

	
	@Override
	public void run() {
		while(true) {
		Estadistica.setEmbotellador(id,"Llenando botella");
		int botella=llenarBotella();
		Estadistica.setEmbotellador(id,"Botella "+botella+" llenada");
		Estadistica.setEmbotellador(id,"Esperando a poner botella "+botella+" en la caja");
		ponerBotellaEnCaja(botella);
		Estadistica.setEmbotellador(id,"Botella "+botella+" puesta en caja");
		sleep(Config.T_PONER_BOTELLA);
		}
		
	}

	/**
	 * Simula el llenado de una botella haciendo una espera.
	 * @return Devuelve la id que corresponde a la botella
	 */
	private int llenarBotella() {
		sleep(Config.T_CREA_BOTELLA);
		return Fabrica.getNuevaBotella();
	}

	/**
	 * Pone la botella en la caja vacia que hay en la Fabrica
	 * 
	 * @param botella Botella a colocar
	 */
	private void ponerBotellaEnCaja(int botella) {
		Fabrica.ponerBotellaEnCaja(botella);
	}
 
	/**
	 * Duerme el hilo por un tiempo
	 * @param ms Los milisegundos  dormir el hilo
	 */
	private void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
 
}
