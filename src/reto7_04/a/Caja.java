package reto7_04.a;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Objeto caja con una capacidad determinada. Conforme se va lllenando el
 * parametro capacidad desciende hasta llegar a 0 momento en el que se considera
 * que esta llena.
 * 
 * Controla la modificacion concurrente de la capacidad con un ReentrantLock
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Caja {
	
	/**
	 * Id de la caja
	 */
	private int id;
	
	/**
	 * Capacidad restante de la caja
	 */
	private int capacidadDisponible = Config.TAMANO_CAJA;
	
	/**
	 * Botellas que hay en la caja
	 */
	private int[] botellas = new int[Config.TAMANO_CAJA];
	
	/**
	 * Lock de acceso a la modificacion de de capacidad disponible
	 */
	private ReentrantLock accesoBotellas = new ReentrantLock(true);

	
	/**
	 * Constructor
	 * 
	 * @param id Id de la caja
	 */
	public Caja(int id) {
		this.id = id;
	}

	/**
	 * Devuelve la id de la caja
	 * @return La id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Poner una botella en al caja
	 * @param botella Botella a poner
	 * 
	 * @return True si queda espacio False si no queda
	 */
	public boolean ponerBotella(int botella) {
		try {
			accesoBotellas.lock();
			botellas[capacidadDisponible - 1] = botella;
			capacidadDisponible--;
		} finally {
			accesoBotellas.unlock();
		}
		return capacidadDisponible > 0;
	}

	@Override
	public String toString() {
		return "Caja " + id;
	}

	/**
	 * Devuelve un string con el estado de llenado de la caja
	 * 
	 * @return El string representando el estado de llenado
	 */
	public String contenido() {
		return "Caja " + id + ", botellas=" + Arrays.toString(botellas).replace("0,", "X,");

	}

}
