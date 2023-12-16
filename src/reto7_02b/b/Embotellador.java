package reto7_02b.b;

public class Embotellador implements Runnable {
	Fabrica fabrica;
	
	
	
	public Embotellador(Fabrica fabrica) {
		this.fabrica = fabrica;
	}

	@Override
	public void run() {
		while(true) {
		Estadistica.setEmbotellador("Llenando botella");
		int botella=llenarBotella();
		Estadistica.setEmbotellador("Botella "+botella+" llenada");
		Estadistica.setEmbotellador("Esperando a poner botella "+botella+" en la caja");
		ponerBotellaEnCaja(botella);
		Estadistica.setEmbotellador("Botella "+botella+" puesta en caja");
		sleep(Config.T_PONER_BOTELLA);
		}
		
	}

	private int llenarBotella() {
		sleep(Config.T_CREA_BOTELLA);
		return fabrica.idProximaBotella++;
	}

	private void ponerBotellaEnCaja(int botella) {
		fabrica.ponerBotellaEnCaja(botella);
	}
 
	private void sleep(long ms) {
		try {
			Thread.currentThread().sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
 
}
