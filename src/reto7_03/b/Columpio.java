package reto7_03.b;

public class Columpio {
	int plazasOcupadasMacho = 0;
	int plazasOcupadasHembra = 0;

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

	synchronized public void dejarSitio(boolean esMacho) {
		if (esMacho)
			plazasOcupadasMacho--;
		else
			plazasOcupadasHembra--;
		notifyAll();
	}

}
