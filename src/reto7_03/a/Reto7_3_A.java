package reto7_03.a;

/**
 * 
 *  Clase main del ejercicio Reto7_3 solucion A
 *  
 * Implementacion muy parecida al caso del reto 7_1. En este caso como hay plazas exculsivas por sexo en 
 * el columpio los loros tienen un atributo que define si es macho o no. En funcion de ese atributo se 
 * accede a la plaza de su sexo representado por un semaforo para cada sexo.
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Reto7_3_A {

	public static void main(String[] args) {
		Thread[] loros = new Thread[Config.N_LOROS];
		
		//generar hilos basados en runnables loros
		for (int i=0;i<loros.length;i++)
			loros[i]=new Thread(new Loro(i));
		
		
		//lanzar hilos
		for (int i=0;i<loros.length;i++)
			loros[i].start();
		
		
		
	}

}
