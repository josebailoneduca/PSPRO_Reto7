package reto7_10.a;

public class Barbero extends Thread {

	@Override
	public void run() {
		while(true) {
			try {
				Estadistica.setEstadoBarbero(Estadistica.DURMIENDO);
				Barberia.clientes.acquire();
				Estadistica.setEstadoBarbero(Estadistica.TRABAJANDO);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Barberia.barberoLibre.release();
			trabajar();
			Estadistica.setEstadoBarbero(Estadistica.DURMIENDO);
		}
	}
	
	
	
	public void trabajar() {
		try {
			sleep(Config.T_TRABAJO);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
