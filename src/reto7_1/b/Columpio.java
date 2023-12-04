package reto7_1.b;

public class Columpio {
	int plazasOcupadas=0;
	
	synchronized public void cogerSitio() {
		if (plazasOcupadas>=Config.PLAZAS_COLUMPIO)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		plazasOcupadas++;
	}
	
	synchronized public void dejarSitio() {
		plazasOcupadas--;
		notify();
	}
	
}
