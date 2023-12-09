package reto7_2b.a;

public class Empaquetador implements Runnable {

	@Override
	public void run() {
		while (true) {
			retirarCajaLlena();
			Estadistica.setEmpaquetador("Cambiando la caja");
			ponerCajaVacia();
		}

	}

	private void ponerCajaVacia() {
		Caja caja = new Caja(Fabrica.idProximaCaja++);
		sleep(Config.T_PONER_CAJA);
		Fabrica.ponerCajaVacia(caja);
		Estadistica.setEmpaquetador("Caja vacia " + caja.getId() + " puesta");
	}

	private void retirarCajaLlena() {
		Estadistica.setEmpaquetador("Esperando para poder retirar caja llena");
		Caja caja = Fabrica.retirarCajaLlena();
		Estadistica.setEmpaquetador("Retirando caja llena");
		sleep(Config.T_QUITAR_CAJA);
		Fabrica.almacen.add(caja);
	}
	
	
	private void sleep(long ms) {
		try {
			Thread.currentThread().sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
