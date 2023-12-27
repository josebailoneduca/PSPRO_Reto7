/**
 * 
 */
package reto7_13.a;

/**
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Viajero extends Thread {
	boolean matematico;
	String dni;
	Bote bote;
	
	public Viajero(boolean matematico, String dni, Bote bote) {
		this.matematico = matematico;
		this.dni = dni;
		this.bote=bote;
	}

	public boolean isMatematico() {
		return matematico;
	}

	public String getDni() {
		return dni;
	}

	
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
