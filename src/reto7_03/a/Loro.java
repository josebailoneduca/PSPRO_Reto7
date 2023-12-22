package reto7_03.a;

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

	/**
	 * Id del loro
	 */
	private int id;
	
	/**
	 * True si es macho y false si es hembra
	 */
	private boolean esMacho;
	
	/**
	 * Constructor
	 * @param id Id del loro
	 */
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
	 * Cuando el loro va a columpiarse espera al semaforo que le corresponde a su sexo.
	 * Al terminar de colupiarse libera el semaforo adquirido
	 */
	private void columpiarse() {
		try {
				
			long iniT=System.currentTimeMillis();
			if(esMacho)
			Jaula.columpioPlazaMacho.acquire();
			else
			Jaula.columpioPlazaHembra.acquire();
			
			Estadistica.columpiarse(id, System.currentTimeMillis()-iniT);
			
			Thread.sleep(new Random().nextLong(Config.MIN_COLUMPIARSE,Config.MAX_COLUMPIARSE));
			
			Estadistica.setEstado(id, Estadistica.ESPERA_COMER);
			if (esMacho)
			Jaula.columpioPlazaMacho.release();
			else
			Jaula.columpioPlazaHembra.release();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Adquiere una plaza de comida y la libera al terminar de comer
	 */
	private void comer() {
		try {
			Estadistica.setEstado(id, Estadistica.ESPERA_COMER);
			
			long iniT=System.currentTimeMillis();
			
			Jaula.comida.acquire();
			Estadistica.comer(id, System.currentTimeMillis()-iniT);

			Thread.sleep(new Random().nextLong(Config.MIN_COMER,Config.MAX_COMER));
			
			Estadistica.setEstado(id, Estadistica.ESPERA_COLUMPIO);
			
			Jaula.comida.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}

}
