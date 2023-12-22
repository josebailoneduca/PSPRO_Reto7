package reto7_02b.a;

/**
 * <p>
 * Clase main del ejercicio Reto7_2B solucion A
 * </p>
 * 
 * <p>
 * La solucion esta implementada con semaforos en la clase Fabrica que controlan el acceso a la caja vacia por parte de Embotellador y Empaquetador. 
 * </p>
 *
 *<p>
 * Embotellador genera Botellas y las coloca en la caja vacia. Cuando esta se llena Empaquetador la retira y coloca una caja vacia.
 * </p>
 *
 * <p>
 * Esta clase genera dos hilos basados en Embotellador y Empaquetador y los pone a correr. Esos hilos actuan sobre la clase Fabrica,
 * a traves de la cual comunican sus acciones mediante objetos Caja que contienen enteros que representan las id de las botellas.
 * </p>
 * 
 * @author Jose Javier Bailon Ortiz
 * */
public class Reto7_2B_A {

	public static void main(String[] args) {
		Thread embotellador = new Thread(new Embotellador());
		Thread empaquetador= new Thread(new Empaquetador());
		

		embotellador.start();
		empaquetador.start();
		
		
		
	}

}
