package reto7_03.b;


/**
 * Monitor de acceso al columpio
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Columpio {
	
	/**
	 * Plazas de macho ocupadas
	 */
	private int plazasOcupadasMacho = 0;
	
	/**
	 * Plazas de hembra ocupadas
	 */
	private int plazasOcupadasHembra = 0;

	
	/**
	 * Coge una plaza en el columpio. Si no quedan plazas disponibles para el sexo del loro 
	 * lo hace esperar
	 *  
	 * @param esMacho Sexo del loro que intenta coger sitio en el columpio
	 */
	synchronized public void cogerSitio(boolean esMacho) {
		if (esMacho) {
			while (plazasOcupadasMacho >= Config.PLAZAS_COLUMPIO)
				try {wait();} catch (InterruptedException e) {e.printStackTrace();}
			plazasOcupadasMacho++;
			return;
		} else {
			while (plazasOcupadasHembra >= Config.PLAZAS_COLUMPIO)
				try {wait();} catch (InterruptedException e) {e.printStackTrace();}
			plazasOcupadasHembra++;
			return;
		}
	}

	/**
	 * Libera una plaza del columpio correspondiente al sexo del loro
	 * 
	 * @param esMacho El sexo del loro que libera la plaza
	 */
	synchronized public void dejarSitio(boolean esMacho) {
		if (esMacho)
			plazasOcupadasMacho--;
		else
			plazasOcupadasHembra--;
		//notify all para asegurar que se notifica a un loro(si es que existe) del sexo del loro que deja sitio
		notifyAll();
	}

}
