package reto7_10.b;

public class Cliente extends Thread {
	private int id;
	private Barberia barberia;
	public Cliente(int id, Barberia barberia) {
		this.id = id;
		this.barberia=barberia;
	}

	@Override
	public void run() {

		 
		if (barberia.cogerPlaza()) {
 			Estadistica.esperar();
			barberia.esperarBarbero();
			Estadistica.dejarDeEsperar();
			barberia.liberarPlaza();
 
		} else {
			Estadistica.desistir();
		}
	}

	@Override
	public String toString() {
		return "Cliente " + id + "";
	}

}