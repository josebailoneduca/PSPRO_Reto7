package reto7_05.a;

/**
* <p>
 * Clase main del ejercicio Reto7_5 solucion A
 * </p>
 * 
 * <p>
 * La solución esta implementada usando un semáforo que debe adquirir D y que A y E liberan.
 * H1, H2 y H3 tienen su propia clase que extiende de Thread y actuan cuando toca sobre el semáforo 
 * contenido en la clase Control. 
 * H1 libera el semáforo en A, H2 espera el semaforo en D y H3 libera el semáforo en E.	 * 
 * </p>
 * 
 * <p>
 *  El hilo del main se encarga de ir actualizando las estadisticas cada 500ms
 * </p>
 * 
 * @author Jose Javier Bailon Ortiz
*/
public class Reto7_5_A {

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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}
