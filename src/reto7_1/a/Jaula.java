package reto7_1.a;

import java.util.concurrent.Semaphore;

public class Jaula {
	public static Semaphore comida = new Semaphore(Config.PLAZAS_COMIDA);
	public static Semaphore columpio = new Semaphore(Config.PLAZAS_COLUMPIO);
}
