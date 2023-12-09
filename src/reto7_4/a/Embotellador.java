package reto7_4.a;

public class Embotellador implements Runnable {

	int id;
	
	public Embotellador(int id) {
		super();
		this.id = id;
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
		return Fabrica.getNuevaBotella();
	}

	private void ponerBotellaEnCaja(int botella) {
		Fabrica.ponerBotellaEnCaja(botella);
	}
 
	private void sleep(long ms) {
		try {
			Thread.currentThread().sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
 
}
