package reto7_01.b;

public class Comida {
	int plazasOcupadas=0;
	
	synchronized public void cogerSitio() {
		if (plazasOcupadas>=Config.PLAZAS_COMIDA)
			try {
				this.wait();
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
