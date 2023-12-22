package reto7_03.b;

import java.util.Random;

/**
 * Clase Loro representa un loro. En su constructor se decide al azar su sexo. 
 * Su carrera como runnable es comer y columpiarse   en un bucle infinito. 
 * Tiene una caracteristica de sexo con la cual se decide si debe
 * ocupar la plaza para hembras o para machos en el columpio. 
 * 
 * @author Jose Javier Bailon Ortiz
 */
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

	/**
	 * Accede al monitor columpio de la jaula para coger sitio, columpiarse y liberarlo
	 */
	private void columpiarse() {
		try {
				
			long iniT=System.currentTimeMillis();
			
			Jaula.columpio.cogerSitio(esMacho);
				
				Estadistica.columpiarse(id, System.currentTimeMillis()-iniT);
			
			Thread.sleep(new Random().nextLong(Config.MIN_COLUMPIARSE,Config.MAX_COLUMPIARSE));
			
			Estadistica.setEstado(id, Estadistica.ESPERA_COMER);
			Jaula.columpio.dejarSitio(esMacho);
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	
	/**
	 * Accede al monitor comida de la jaula para coger sitio y liberarlo
	 */
	private void comer() {
		try {
			Estadistica.setEstado(id, Estadistica.ESPERA_COMER);
			
			long iniT=System.currentTimeMillis();
			
			Jaula.comida.cogerSitio();
			Estadistica.comer(id, System.currentTimeMillis()-iniT);

			Thread.sleep(new Random().nextLong(Config.MIN_COMER,Config.MAX_COMER));
			
			Estadistica.setEstado(id, Estadistica.ESPERA_COLUMPIO);
			
			Jaula.comida.dejarSitio();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
