package reto7_13.a;

/**
 * Representa a los viajeros. Tanto los matematicos como los no matematicos.
 * Tiene una carrera en la que sube y baja del bote.
 * 
 * @author Jose Javier Bailon Ortiz
 * @see Bote
 */
public class Viajero extends Thread {
	
	/**
	 * True si es matematico y False si no es matematico
	 */
	private boolean matematico;
	/**
	 * Identificador del viajero
	 */
	private String dni;
	
	/**
	 * Referencia al bote
	 */
	private Bote bote;
	
	
	/**
	 * Constructor
	 * 
	 * @param matematico True si es matematico False si no lo es
	 * @param dni Identificador del viajero
	 * @param bote Referencia al bote
	 */
	public Viajero(boolean matematico, String dni, Bote bote) {
		this.matematico = matematico;
		this.dni = dni;
		this.bote=bote;
	}

	/**
	 * Devuelve si es matematico o no
	 * @return True si es matematico, False si no es matematico
	 */
	public boolean isMatematico() {
		return matematico;
	}

	/**
	 * Devuelve el identificador
	 * @return El identificador
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * La carrera consiste en subir al bote y bajar de el 
	 */
	@Override
	public void run() {
		bote.subir();
		bote.bajar();
	}

	@Override
	public String toString() {
		return dni;
	}
	
}
