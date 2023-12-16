package reto7_9.a;

public class PrepIngredientes extends Thread {
	int ultimaId =0;
	
	@Override
	public void run() {
		while(true) {
			Estadistica.setEstado(Estadistica.PREP_INGREDIENTES, Estadistica.PREPARANDO);
			esperaPreparando();
			Estadistica.setEstado(Estadistica.PREP_INGREDIENTES, Estadistica.ESPERANDO);
			Fabrica.ponerIngrediente(++ultimaId);
		}
	}
	
	public void 			esperaPreparando() {
		try {
			sleep(Config.T_PREPARA_INGREDIENTE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
