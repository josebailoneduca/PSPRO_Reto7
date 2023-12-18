package reto7_01.a;



/**
 * Clase main del ejercicio Reto7_1 solucion A
 * 
 * Crea un hilo por loro y lo lanza.
 * 
 * La solucion esta implementada con semaforos los cuales representan
 * las plazas disponibles para comer y columpiarse
 * 
 * @author Jose Javier Bailon Ortiz
 * 
 */
public class Reto7_1_A {

	public static void main(String[] args) {
		Thread[] loros = new Thread[Config.N_LOROS];
		
		for (int i=0;i<loros.length;i++)
			loros[i]=new Thread(new Loro(i));
		
		for (int i=0;i<loros.length;i++)
			loros[i].start();
		
		
		
	}

}
