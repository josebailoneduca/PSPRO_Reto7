package reto7_03.b;



/**
/**
 * 
 *  Clase main del ejercicio Reto7_3 solucion B
 *  
 * En este caso la implementacion controla el acceso a columpio y comida con dos clases monitor: Columpio y Comida
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Reto7_3B {

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
