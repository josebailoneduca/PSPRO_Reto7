package reto7_8a.a;

public class Mafioso extends Thread {
	Ingrediente ingrediente;
	Mesa mesa;

	public Mafioso(Ingrediente ingrediente, Mesa mesa) {
		this.ingrediente = ingrediente;
		this.mesa = mesa;
	}

	@Override
	public void run() {
		while(true) {
			Estadistica.setEstado(ingrediente, Estadistica.ESPERANDO);
			if (mesa.coger(ingrediente)) {
				Estadistica.setEstado(ingrediente, Estadistica.COMIENDO);
				espera(Config.T_COCINAR);
				mesa.avisar();
			}
		}
	}

	private void espera(long t) {
		try {
			sleep(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	

}
