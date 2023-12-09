package reto7_4.b;

public class Embotellador implements Runnable {
	Fabrica fabrica;
	int id;
	
	
	public Embotellador(Fabrica fabrica,int id) {
		this.fabrica = fabrica;
		this.id=id;
	}

	@Override
	public void run() {
		while(true) {
		Estadistica.setEmbotellador(id,"Llenando botella");
		int botella=llenarBotella();
		Estadistica.setEmbotellador(id,"Botella "+botella+" llenada");
		Estadistica.setEmbotellador(id,"Esperando a poner botella "+botella+" en la caja");
		ponerBotellaEnCaja(botella);
		Estadistica.setEmbotellador(id,"Botella "+botella+" puesta en caja");
		sleep(Config.T_PONER_BOTELLA);
		}
		
	}

	private int llenarBotella() {
		sleep(Config.T_CREA_BOTELLA);
		return fabrica.getIdProximaBotella();
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
