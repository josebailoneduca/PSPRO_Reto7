package reto7_3.b;

import java.util.Random;

import reto7_3.b.Estadistica;

public class Loro implements Runnable {

	private int id;
	private boolean esMacho;
	public Loro(int id) {
		this.id = id;
		esMacho=new Random().nextBoolean();
		Estadistica.setSexoLoro(id, esMacho);
	}

	@Override
	public void run() {
		
		while(true) {
			
			comer();
			
			columpiarse();
			
		}

	}

	private void columpiarse() {
		try {
				
			long iniT=System.currentTimeMillis();
			
			Jaula.columpio.cogerSitio(esMacho);
				
				Estadistica.columpiarse(id, System.currentTimeMillis()-iniT);
			
			Thread.currentThread().sleep(new Random().nextLong(Config.MIN_COLUMPIARSE,Config.MAX_COLUMPIARSE));
			
			Estadistica.setEstado(id, Estadistica.ESPERA_COMER);
			Jaula.columpio.dejarSitio(esMacho);
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	private void comer() {
		try {
			Estadistica.setEstado(id, Estadistica.ESPERA_COMER);
			
			long iniT=System.currentTimeMillis();
			
			Jaula.comida.cogerSitio();
			Estadistica.comer(id, System.currentTimeMillis()-iniT);

			Thread.currentThread().sleep(new Random().nextLong(Config.MIN_COMER,Config.MAX_COMER));
			
			Estadistica.setEstado(id, Estadistica.ESPERA_COLUMPIO);
			
			Jaula.comida.dejarSitio();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}

}
