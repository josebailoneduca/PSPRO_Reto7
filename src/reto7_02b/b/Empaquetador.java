package reto7_02b.b;

public class Empaquetador implements Runnable {
	Fabrica fabrica;
	
	public Empaquetador(Fabrica fabrica) {
		super();
		this.fabrica = fabrica;
	}

	@Override
	public void run() {
		while (true) {
			retirarCajaLlena();
			Estadistica.setEmpaquetador("Cambiando la caja");
			ponerCajaVacia();
		}

	}

	private void ponerCajaVacia() {
		Caja caja = new Caja(fabrica.idProximaCaja++);
		sleep(Config.T_PONER_CAJA);
		fabrica.ponerCajaVacia(caja);
		Estadistica.setEmpaquetador("Caja vacia " + caja.getId() + " puesta");
	}

	private void retirarCajaLlena() {
		Estadistica.setEmpaquetador("Esperando para poder retirar caja llena");
		Caja caja = fabrica.retirarCajaLlena();
		Estadistica.setEmpaquetador("Retirando caja llena");
		sleep(Config.T_QUITAR_CAJA);
		fabrica.almacen.add(caja);
	}
	
	
	private void sleep(long ms) {
		try {
			Thread.currentThread().sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
