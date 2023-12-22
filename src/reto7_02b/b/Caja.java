package reto7_02b.b;

import java.util.Arrays;

import reto7_02b.a.Config;

/**
 * Objeto caja con una capacidad determinada. Conforme se va lllenando el
 * parametro capacidad desciende hasta llegar a 0 momento en el que se considera
 * que esta llena.
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Caja {
	/**
	 * Identificador de la caja
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
	 * @param id Id de la caja
	 */
	public Caja(int id) {
		this.id = id;
	}

	/**
	 * Devuelve la id
	 * @return El id de la caja
	 */
	public int getId() {
		return id;
	}

	/**
	 * Devuelve la capacidad disponible en la caja
	 * @return La capacidad disponible
	 */
	public int getCapacidadDisponible() {
		return capacidadDisponible;
	}

	/**
	 * Coloca una botella en la caja si queda capacidad disponible
	 * 
	 * @param botella La botella a colocar
	 * 
	 * @return Devuelve si queda espacio en la caja
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
		return "Caja " + id + ", botellas=" + Arrays.toString(botellas).replace("0", "X");

	}

}
