package reto7_11.b;

 

/**
 * Produce barcos a una velocidad variable y los va iniciando.
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class GeneradorDeBarcos extends Thread{
	
	/**
	 * Referencia al canal
	 */
	private Canal canal;
	
	/**
	 * Ultima id usada para los elementos
	 */
	private int ultimaId=-1;
	
	/**
	 * Tiempo esperado entre generaciones
	 */
	private int tiempoEsperar=Config.T_MIN_CREAR_BARCO;
	
	/**
	 * Cantidad de elementos generados. Usado para variar la velocidad cuando se llegue al limite
	 */
	private int elementossGenerados=0;
	
	
	
	/**
	 * Constructor
	 * 
	 * @param buffer Referencia al buffer
	 */
	public GeneradorDeBarcos(Canal canal) {
		this.canal = canal;
	}
	
	
	@Override
	public void run() {
		while(true) {
 			esperar();
 			new Barco(++ultimaId,canal).start();
		}
	}
	
	/**
	 * Espera un tiempo y comprueba si es necesario un cambio de velocidad
	 */
	private void esperar() {
		
		//esperar
		try {
			sleep(tiempoEsperar);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//comprobar si es necesario un cambio de velocidad
		elementossGenerados=(elementossGenerados+1)%Config.CAMBIO_VELOCIDAD_CADA;
		if (elementossGenerados==0) {
			tiempoEsperar=(tiempoEsperar==Config.T_MIN_CREAR_BARCO)?Config.T_MAX_CREAR_BARCO:Config.T_MIN_CREAR_BARCO;
		}
	}

}
