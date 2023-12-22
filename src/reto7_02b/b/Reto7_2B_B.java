package reto7_02b.b;

/**
 * <p>
 * Clase main del ejercicio Reto7_2B solucion B
 * </p>
 * 
 *
 *<p>
 * Embotellador genera Botellas y las coloca en la caja vacia. Cuando esta se llena Empaquetador la retira y coloca una caja vacia.
 * </p>
 *
 * <p>
 * La solucion esta implementada haciendo que la clase Fabrica sea monitor. Esta clase se encarga de controlar el acceso a la caja vacia 
 * poniendo en espera al embotellador cuando ya no quedan espacios en la caja y al empaquetador cuando la caja que debe retirar aun no esta 
 * llena. 
 * </p>
 * 
 * <p>
 * Esta clase instancia la fabrica y se la establece a la estadistica(encargada de mostrar el estado) y los dos hilos basados en Embotellador y Empaquetador los cuales pone a correr. 
 * Esos hilos se comunican con la clase Fabrica a traves de la cual se pasan objetos Caja que contienen enteros que representan las id de las botellas.
 * </p>
 * 
 * */
public class Reto7_2B_B {

	public static void main(String[] args) {
		
		Fabrica fabrica= new Fabrica();
		Estadistica.setFabrica(fabrica);
		Thread embotellador = new Thread(new Embotellador(fabrica));
		Thread empaquetador= new Thread(new Empaquetador(fabrica));
		

		embotellador.start();
		empaquetador.start();
		
		
		
	}

}
