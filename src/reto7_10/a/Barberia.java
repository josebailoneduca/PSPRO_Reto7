package reto7_10.a;

import java.util.concurrent.Semaphore;

public class Barberia {
	public static Semaphore sillasLibres= new Semaphore(Config.N_PLAZAS);
	public static Semaphore barberoLibre= new Semaphore(0);
	public static Semaphore clientes=new Semaphore(0);
}
