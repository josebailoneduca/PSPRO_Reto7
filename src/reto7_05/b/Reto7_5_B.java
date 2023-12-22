package reto7_05.b;


/**
* <p>
 * Clase main del ejercicio Reto7_5 solucion B
 * </p>
 * 
 * <p>
 * La solución esta implementada de manera parecida a la solución A pero usando un AtomicInteger 
 * que define las veces que puede ejecutarse D. Este Atomic Integer se va incrementando en las fases A y E 
 * y decrementando en la fase D, la cual para ejecutarse requiere que el AtomicInteger de control sea 
 * positivo superior a 0.
 * </p>
 * 
 * <p>
 *  El hilo del main se encarga de ir actualizando las estadisticas cada 500ms
 * </p>
 * 
 * @author Jose Javier Bailon Ortiz
*/
public class Reto7_5_B {

 	public static void main(String[] args) {
		
 		//lanzar las hebras
 		H1 h1=new H1();
		H2 h2=new H2();
		H3 h3=new H3();
		
		
		h1.start();
		h2.start();
		h3.start();
		
		
		//bucle de actualizacion de estadisticas
		while(true) {
			Estadistica.mostrarEstadistica();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}
}
