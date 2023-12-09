package reto7_3.a;

import java.util.concurrent.Semaphore;

public class Jaula {
	public static Semaphore comida = new Semaphore(Config.PLAZAS_COMIDA);
	public static Semaphore columpioPlazaMacho = new Semaphore(Config.PLAZAS_COLUMPIO);
	public static Semaphore columpioPlazaHembra = new Semaphore(Config.PLAZAS_COLUMPIO);
}
