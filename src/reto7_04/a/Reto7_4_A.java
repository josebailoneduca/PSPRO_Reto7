package reto7_04.a;

import java.util.Iterator;

/**
/**
 * <p>
 * Clase main del ejercicio Reto7_4 solucion A
 * </p>
 * 
 * <p>
 * La solucion esta implementada con semaforos en la clase Fabrica que controlan el acceso a la caja vacia por parte de los Embotelladores y  el Empaquetador. 
 * </p>
 *
 *<p>
 * Los Embotelladores generan botellas y las colocan en la caja vacia. Cuando esta se llena Empaquetador la retira y coloca una caja vacia.
 * </p>
 *
 *<p>
 * La exclusion mutua para la modificacion del contenido de la caja se hace en la propia caja con un ReentrantLock 
 *</p>
 *
 *
 * <p>
 * Esta clase genera varios hilos Embotellador y uno Empaquetador y los pone a correr. Esos hilos actuan sobre la clase Fabrica,
 * a traves de la cual comunican sus acciones mediante objetos Caja que contienen enteros que representan las id de las botellas.
 * </p>
 * 
 * <p>
 *  El hilo del main se encarga de ir actualizando las estadisticas cada 500ms
 * </p>
 * 
 * @author Jose Javier Bailon Ortiz
 * */

public class Reto7_4_A {

	public static void main(String[] args) {
		
		//crear embotelladores
		Thread[]embotelladores = new Thread[Config.N_EMBOTELLADORES];
		for (int i=0;i<Config.N_EMBOTELLADORES;i++) {
		embotelladores[i]= new Thread(new Embotellador(i));
		}
		for (Thread embotellador : embotelladores) {
			embotellador.start();
		}
		
		//crear empaquetador
		Thread empaquetador= new Thread(new Empaquetador());
		empaquetador.start();
		
		
		//bucle de actualizacion de las estadisticas
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
