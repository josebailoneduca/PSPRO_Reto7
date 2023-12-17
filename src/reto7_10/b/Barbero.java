package reto7_10.b;

public class Barbero extends Thread {
	Barberia barberia;
	
	public Barbero(Barberia barberia) {
		this.barberia = barberia;
	}



	@Override
	public void run() {
		while(true) {
 				Estadistica.setEstadoBarbero(Estadistica.DURMIENDO);
				barberia.esperarCliente();
				Estadistica.setEstadoBarbero(Estadistica.TRABAJANDO);
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
