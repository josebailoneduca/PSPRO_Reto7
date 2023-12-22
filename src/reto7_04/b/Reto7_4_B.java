package reto7_04.b;
 
/**
/**
 * <p>
 * Clase main del ejercicio Reto7_4 solucion B
 * </p>
 * 
 * <p>
 * La solucion esta implementada con metodos synchrozied en la clase Fabrica que controlan el acceso a la caja vacia por parte de los Embotelladores y  el Empaquetador. 
 * </p>
 *
 *<p>
 * Los Embotelladores generan botellas y las colocan en la caja vacia. Cuando esta se llena Empaquetador la retira y coloca una caja vacia.
 * </p>
 *
 *<p>
 * La exclusion mutua para la modificacion del contenido de la caja se hace por parte de la propia clase Fabrica al accederse exclusivamente a traves de
 * metodos sincronizados 
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
public class Reto7_4_B {

	public static void main(String[] args) {
		
		//crear fabrica y definirla en las estadisticas
		Fabrica fabrica=new Fabrica();
		Estadistica.setFabrica(fabrica);
		
		//crear embotelladores
		Thread[]embotelladores = new Thread[Config.N_EMBOTELLADORES];
		for (int i=0;i<Config.N_EMBOTELLADORES;i++) {
		embotelladores[i]= new Thread(new Embotellador(fabrica,i));
		embotelladores[i].start();
		}
		 
		//crear empaquetador
		Thread empaquetador= new Thread(new Empaquetador(fabrica));
		empaquetador.start();
		
		
		//bucle para mostrar estadisticas
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
