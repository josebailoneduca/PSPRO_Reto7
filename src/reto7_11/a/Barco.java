package reto7_11.a;

import java.util.Random;

/**
 * <p>
 * Hilos barco. Tiene un identificador y una referencia al canal.
 * </p>
 * <p>
 * En su creacion se decide la direccion en la que cruza el canal
 * </p>
 * <p>
 * Su carrera es entrar al canal, simular el transito con una espera y salir del canal.
 * </p>
 * 
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Barco extends Thread {
	
	/**
	 * Identificador del barco
	 */
	private int id;
	
	/**
	 * Referencia al canal
	 */
	private Canal canal;

	/**
	 * Direccion True para Este->oeste y false para Oeste->este
	 */
	private boolean direccionEO;

	
	/**
	 * Constructor
	 * @param id Identificador del barco
	 * @param canal Referencia al canal
	 */
	public Barco(int id, Canal canal) {
		this.id=id;
		this.canal=canal;
		this.direccionEO = new Random().nextBoolean();
	}

	/**
	 * Devuelve la direccion. True para Este->oeste y false para Oeste->este
	 * @return la direccion
	 */
	public boolean isDireccionEO() {
		return direccionEO;
	}

	
	
	
	
	
	@Override
	public void run() {
		Estadistica.esperando(this);
		canal.entrar();
		Estadistica.enCanal(this);
		try {
			sleep(Config.T_TRANSITO_CANAL);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		canal.salir();
		Estadistica.salir(this);
	}

	@Override
	public String toString() {
		return "[("+id + ") " +((direccionEO)?"E-o<<<":"O-e>>>")+"]";
	}
 
	
}
