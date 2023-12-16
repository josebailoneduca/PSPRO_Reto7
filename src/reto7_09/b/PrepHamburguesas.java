package reto7_09.b;

public class PrepHamburguesas extends Thread {
	int ultimaId = 0;

	@Override
	public void run() {
		while (true) {
			Estadistica.setEstado(Estadistica.PREP_HAMBURGUESAS, Estadistica.PREPARANDO);
			Estadistica.setEstado(Estadistica.PREP_HAMBURGUESAS, Estadistica.ESPERANDO);
			int panecillo1 = Fabrica.cogerPanecillo();
			Estadistica.setEstado(Estadistica.PREP_HAMBURGUESAS, Estadistica.PREPARANDO);
			Estadistica.setEstado(Estadistica.PREP_HAMBURGUESAS, Estadistica.ESPERANDO);
			int panecillo2 = Fabrica.cogerPanecillo();
			Estadistica.setEstado(Estadistica.PREP_HAMBURGUESAS, Estadistica.PREPARANDO);
			Estadistica.setEstado(Estadistica.PREP_HAMBURGUESAS, Estadistica.ESPERANDO);
			int ingredientes = Fabrica.cogerIngrediente();
			Estadistica.setEstado(Estadistica.PREP_HAMBURGUESAS, Estadistica.PREPARANDO);
			esperaPreparando();
			Fabrica.ponerHamburguesa(new Hamburguesa(++ultimaId, panecillo1, panecillo2, ingredientes));
		}
	}

	public void esperaPreparando() {
		try {
			sleep(Config.T_PREPARA_HAMBURGUESA);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
