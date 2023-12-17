package reto7_01.a;

import java.util.Random;

public class Loro implements Runnable {

	private int id;
	
	public Loro(int id) {
		this.id = id;
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
			
			Jaula.columpio.acquire();
				
				Estadistica.columpiarse(id, System.currentTimeMillis()-iniT);
			
			Thread.currentThread().sleep(new Random().nextLong(Config.MIN_COLUMPIARSE,Config.MAX_COLUMPIARSE));
			
			Estadistica.setEstado(id, Estadistica.ESPERA_COMER);
			Jaula.columpio.release();
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	private void comer() {
		try {
			Estadistica.setEstado(id, Estadistica.ESPERA_COMER);
			
			long iniT=System.currentTimeMillis();
			
			Jaula.comida.acquire();
			Estadistica.comer(id, System.currentTimeMillis()-iniT);

			Thread.currentThread().sleep(new Random().nextLong(Config.MIN_COMER,Config.MAX_COMER));
			
			Estadistica.setEstado(id, Estadistica.ESPERA_COLUMPIO);
			
			Jaula.comida.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}

}