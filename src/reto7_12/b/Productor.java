package reto7_12.b;


/**
 * Produce elementos a una velocidad variable y los va introduciendo en el buffer.
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Productor extends Thread{
	
	/**
	 * Referencia al buffer
	 */
	private Buffer buffer;
	
	/**
	 * Ultima id usada para los elementos
	 */
	private int ultimaId=-1;
	
	/**
	 * Tiempo esperado entre generaciones
	 */
	private int tiempoEsperar=Config.T_RAPIDO_GENERAR;
	
	/**
	 * Cantidad de elementos generados. Usado para variar la velocidad cuando se llegue al limite
	 */
	private int elementossGenerados=0;
	
	
	
	/**
	 * Constructor
	 * 
	 * @param buffer Referencia al buffer
	 */
	public Productor(Buffer buffer) {
		this.buffer = buffer;
	}
	
	
	@Override
	public void run() {
		while(true) {
			Estadistica.setEstadoProductor(Estadistica.PREPARANDO);
			esperar();
			Estadistica.setEstadoProductor(Estadistica.ESPERANDO);
			buffer.agregar(new Elemento(++ultimaId));
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
			tiempoEsperar=(tiempoEsperar==Config.T_RAPIDO_GENERAR)?Config.T_LENTO_GENERAR:Config.T_RAPIDO_GENERAR;
			Estadistica.setVelocidadGenerador(tiempoEsperar);
		}
	}

}