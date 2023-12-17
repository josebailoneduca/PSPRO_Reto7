package reto7_10.a;

public class Cliente extends Thread {
	private int id;

	public Cliente(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		if (Barberia.sillasLibres.tryAcquire()) {
			try {
				Estadistica.esperar();
				Barberia.clientes.release();
				Barberia.barberoLibre.acquire();
				Estadistica.dejarDeEsperar();
				Barberia.sillasLibres.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			Estadistica.desistir();
		}
	}

	@Override
	public String toString() {
		return "Cliente " + id + "";
	}
	
	
}