/**
 * 
 */
package reto7_14.a;

import java.util.Random;

/**
 * Tienen como atributos una matricula(id), si es residente o no y un numero de ticket que obtiene 
 * al ir a la entrada del aparcamiento.
 *  
 *  
 * @author Jose Javier Bailon Ortiz
 * @see Aparcamiento
 */
public class Coche extends Thread {
	
	/**
	 * Identificador
	 */
	private int matricula;
	
	/**
	 * True si es residente, False si no lo es
	 */
	private boolean residente;
	
	/**
	 * Numero de ticket proporcionado por el aparcamiento
	 */
	private int numeroTicket;
	
	/**
	 * Referencia al aparcamiento
	 */
	private Aparcamiento aparcamiento;

	
	/**
	 * Constructor
	 * @param matricula Identificador
	 * @param residente True si es redidente, False si no lo es
	 * @param aparcamiento Referencia al aparcamiento
	 */
	public Coche(int matricula, boolean residente, Aparcamiento aparcamiento) {
		this.matricula = matricula;
		this.residente = residente;
		this.aparcamiento=aparcamiento;
	}

	/**
	 * Define el numero de ticket
	 * @param n Numero de ticket
	 */
	public void setNumeroTicket(int n) {
		numeroTicket=n;
	}
	
	/**
	 * Devuelve el numero de ticket
	 * @return El numero
	 */
	public int getNumeroTicket() {
		return numeroTicket;
	}
	
	
	/**
	 * Devuelve si es residente o no
	 * 
	 * @return True si es redidente, False si no lo es
	 */
	public boolean isResidente() {
		return residente;
	}

	@Override
	public void run() {
		aparcamiento.entrar(this);
		try {
			sleep(new Random().nextInt(Config.T_MIN_APARCADO,Config.T_MAX_APARCADO));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		aparcamiento.salir(this);
	}

	@Override
	public String toString() {
		return ((residente)?"Resid":"Coche" ) + matricula + "(#" + numeroTicket + ")";
	}

	
	
	
}
