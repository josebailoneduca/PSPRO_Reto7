package reto7_10.b;

/**
 * <p>
 * Hebra de cliente. Tiene una carrera finita con el siguiente comportamiento. 
 * </p>
 * <p>
 * Intenta coger plaza. Si no la consigue inmediatamente termina su carrera. 
 * Si consigue plaza en la barberia espera al barbero y una vez que lo atiende 
 * libera la plaza que habia conseguido. 
 * </p>
 * 
 * 
 * @author Jose Javier Bailon Ortiz
 * @see Barberia
 */
public class Cliente extends Thread {
	
	/**
	 * Id del cliente
	 */
	private int id;
	
	/*
	 * referencia a la barberia
	 */
	private Barberia barberia;
	
	/**
	 * Constructor
	 * @param id Id del cliente
	 * @param barberia Referencia a la barberia
	 */
	public Cliente(int id, Barberia barberia) {
		this.id = id;
		this.barberia=barberia;
	}

	@Override
	public void run() {
		
		//comprobar si hay plazas libres
		if (barberia.cogerPlaza()) {
			//espera al barbero
 			Estadistica.esperar();
			barberia.esperarBarbero();
			
			//deja la plaza libre
			Estadistica.dejarDeEsperar();
			barberia.liberarPlaza();
 
		} else {
			//si no hay plazas libres termina
			Estadistica.desistir();
		}
	}

	@Override
	public String toString() {
		return "Cliente " + id + "";
	}

}