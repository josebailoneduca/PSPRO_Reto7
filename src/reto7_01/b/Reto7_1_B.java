package reto7_01.b;



/**
 * Clase main del ejercicio Reto7_1 solucion B
 * 
 * Crea un hilo por loro y lo lanza.
 * 
 * La solucion esta implementada con monitores que controlan el acceso
 * a la comida y el columpio
 * 
 * @author Jose Javier Bailon Ortiz
 * 
 */
public class Reto7_1_B {

	public static void main(String[] args) {
		Thread[] loros = new Thread[Config.N_LOROS];
		
		for (int i=0;i<loros.length;i++)
			loros[i]=new Thread(new Loro(i));
		
		
		
		for (int i=0;i<loros.length;i++)
			loros[i].start();
		
		
		
	}

}
