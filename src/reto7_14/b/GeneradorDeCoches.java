package reto7_14.b;

import java.util.Random;

/**
 * Produce coches a una velocidad variable y los va iniciando.
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class GeneradorDeCoches extends Thread {

	/**
	 * Referencia al aparcamiento
	 */
	private Aparcamiento aparcamiento;

	/**
	 * Tiempo esperado entre generaciones
	 */
	private int tiempoEsperar = Config.T_MIN_CREAR_COCHE;

	/**
	 * Cantidad de elementos generados. Usado para variar la velocidad cuando se
	 * llegue al limite
	 */
	private int elementossGenerados = 0;

	/**
	 * Constructor
	 * 
	 * @param buffer Referencia al buffer
	 */
	public GeneradorDeCoches(Aparcamiento aparcamiento) {
		this.aparcamiento = aparcamiento;
	}

	/**
	 * Bucle infinito que genera coches eligiendo al azar si es residente
	 */
	@Override
	public void run() {
		int indexResidente = 0;
		int indexNormal = 0;
		int index = 0;
		while (true) {
			esperar();
			boolean esResidente = (new Random().nextInt(100)) < Config.PORCENTAJE_RESIDENTES;
			if (esResidente)
				index = indexResidente++;
			else
				index = indexNormal++;
			new Coche(index, esResidente, aparcamiento).start();
		}
	}

	/**
	 * Espera un tiempo y comprueba si es necesario un cambio de velocidad
	 */
	private void esperar() {

		// esperar
		try {
			sleep(tiempoEsperar);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// comprobar si es necesario un cambio de velocidad
		elementossGenerados = (elementossGenerados + 1) % Config.CAMBIO_VELOCIDAD_CADA;
		if (elementossGenerados == 0) {
			tiempoEsperar = (tiempoEsperar == Config.T_MIN_CREAR_COCHE) ? Config.T_MAX_CREAR_COCHE
					: Config.T_MIN_CREAR_COCHE;
		}
		Estadistica.setVelocidadGeneracion(tiempoEsperar,Config.CAMBIO_VELOCIDAD_CADA-elementossGenerados);
	}

}
