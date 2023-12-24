package reto7_12.b;

import java.util.Random;
 

/**
 * Consume elementos del buffer. En su constructor elige al azar una velocidad
 * de lectura entres el minimo y maximo definidos en la configuracion.
 * Su carrera consiste en un bucle infinito de leer/esperar
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Consumidor extends Thread {
	
	/**
	 * Referencia al buffer
	 */
	private Buffer buffer;
	
	/**
	 * Id del consumidor
	 */
	private int id;
	
	/**
	 * velocidad de lectura
	 */
	private int velocidadLectura;

	/**
	 * Constructor 
	 * @param id Id del consumidor
	 * @param buffer Referencia al buffer
	 */
	public Consumidor(int id, Buffer buffer) {
		this.id = id;
		this.buffer = buffer;
		//definir velocidad de lectura a usar
		velocidadLectura = new Random().nextInt(Config.T_RAPIDO_LEER, Config.T_LENTO_LEER);
		Estadistica.registrarTiempoLetura(id, velocidadLectura);
	}

	@Override
	public void run() {
		while (true) {
	 
			//consumir elemento
			Estadistica.setEstadoConsumidor(id, Estadistica.ESPERANDO);
			Elemento el = buffer.consumir(id);
			
			//simular tiempo de lectura
			Estadistica.setEstadoConsumidor(id, Estadistica.LEYENDO);
			Estadistica.addLectura(id, el.getId());
			try {
				sleep(velocidadLectura);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

