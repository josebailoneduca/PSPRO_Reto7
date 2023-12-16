package reto7_9.b;

public class PrepPanecillos extends Thread {
	int ultimaId = 0;

	@Override
	public void run() {
		while (true) {
			Estadistica.setEstado(Estadistica.PREP_PANECILLOS, Estadistica.PREPARANDO);
			esperaPreparando();
			Estadistica.setEstado(Estadistica.PREP_PANECILLOS, Estadistica.ESPERANDO);
			Fabrica.ponerPanecillo(++ultimaId);
		}
	}

	public void esperaPreparando() {
		try {
			sleep(Config.T_PREPARA_PANECILLO);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
