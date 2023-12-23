package reto7_09.a;


/**
 * Preparador de hamburguesas. Recoge dos panecillos de la cesta de panecillos, un pack de ingredientes,
 * prepara la hamburguesa y la coloca en la bandeja de hamburguesas.
 * 
 * Lo hace en un bulce infinito.
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class PrepHamburguesas extends Thread {
	
	/**
	 * Ultima id asignada a las hamburguesas
	 */
	private int ultimaId = 0;

	@Override
	public void run() {
		while (true) {
			//recoger panecillos
			Estadistica.setEstado(Estadistica.PREP_HAMBURGUESAS, Estadistica.PREPARANDO);
			Estadistica.setEstado(Estadistica.PREP_HAMBURGUESAS, Estadistica.ESPERANDO);
			int panecillo1 = Fabrica.cogerPanecillo();
			Estadistica.setEstado(Estadistica.PREP_HAMBURGUESAS, Estadistica.PREPARANDO);
			Estadistica.setEstado(Estadistica.PREP_HAMBURGUESAS, Estadistica.ESPERANDO);
			int panecillo2 = Fabrica.cogerPanecillo();
			
			//recoger ingredientes
			Estadistica.setEstado(Estadistica.PREP_HAMBURGUESAS, Estadistica.PREPARANDO);
			Estadistica.setEstado(Estadistica.PREP_HAMBURGUESAS, Estadistica.ESPERANDO);
			int ingredientes = Fabrica.cogerIngrediente();
			Estadistica.setEstado(Estadistica.PREP_HAMBURGUESAS, Estadistica.PREPARANDO);
			
			//preparar hamburguesa
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
