package reto7_04.b;

import java.util.Arrays;



/**
 * Objeto caja con una capacidad determinada. Conforme se va llenando el
 * parametro capacidad desciende hasta llegar a 0 momento en el que se considera
 * que esta llena.
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
	 * Devuelve la capacidad disponible en la caja
	 * 
	 * @return La capacidad disponible
	 */
	public int getCapacidadDisponible() {
		return capacidadDisponible;
	}

	/**
	 * Poner una botella en al caja
	 * @param botella Botella a poner
	 * 
	 * @return True si queda espacio False si no queda
	 */
	public boolean ponerBotella(int botella) {
		if ((capacidadDisponible - 1) >= 0)
			botellas[capacidadDisponible - 1] = botella;
		return --capacidadDisponible > 0;
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
