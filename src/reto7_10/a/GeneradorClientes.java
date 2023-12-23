package reto7_10.a;


/**
 * Genera clientes a un ritmo que varia entre dos velocidades, rapida y lenta segun 
 * lo establecido en la configuracion.
 * 
 * @author Jose Javier Bailon Ortiz
 * @see Config
 */
public class GeneradorClientes extends Thread {
	
	/**
	 * Ultima id asignada a un cliente
	 */
	private int ultimaId=0;
	
	/**
	 * Tiempo a esperar entre la generacion de clientes. Va variando.
	 */
	private int tiempoEsperar=Config.T_RAPIDO_NUEVO_CLIENTE;
	
	/**
	 * Cantidad de clientes generados. Llegado a un limite marcado en la configuracion
	 * significa que debe cambiarse la velocidad de generacion
	 */
	private int clientesGenerados=0;
	
	
	@Override
	public void run() {
		while(true) {
			//espera entre generaciones
			esperar();
			//generar nuevo cliente
			new Cliente(++ultimaId).start();
		}
	}
	
	/**
	 * Hace una espera y recalcula la velocidad a la que debe esperar la proxima vez
	 */
	private void esperar() {
		try {
			sleep(tiempoEsperar);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//cuando la cantidad de clientes generados es  un multiplo de lo definido en la configuracion
		//se intercambia la velocidad de rapido a lento o al contrario segun toque
		clientesGenerados=(clientesGenerados+1)%Config.CAMBIO_VELOCIDAD_CADA;
		if (clientesGenerados==0) {
			tiempoEsperar=(tiempoEsperar==Config.T_RAPIDO_NUEVO_CLIENTE)?Config.T_LENTO_NUEVO_CLIENTE:Config.T_RAPIDO_NUEVO_CLIENTE;
			Estadistica.setVelocidadClientes(tiempoEsperar);
		}
	}

}
