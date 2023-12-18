package reto7_02a.a;

import java.util.concurrent.Semaphore;

/**
 * Buffer para pasar las pizzas desde los cocineros a los repartidores.
 * Esta construido con el algoritmo productores consumidores de buffer limitado
 * visto en clase donde el buffer es un array Pizza[] y se controla su acceso
 * con semaforos de elementos y huecos disponibles e indices de lectura y escritura
 *  
 * @author Jose Javier Bailon Ortiz
 */
public class Bandeja {
	
	/**
	 * Contiene los elementos que hay en el buffer
	 */
	private static Pizza[] pizzas = new Pizza[Config.TAMANO_BANDEJA];
	
	/**
	 * Proximo indice a usar para escribir en el buffer
	 */
	private static int entrada = 0;
	
	/**
	 * Proximo indice a usar para leer del buffer
	 */
	private static int salida = 0;
	
	/**
	 * Semaforo de exclusion para acceso a los indices
	 */
	private static Semaphore sExclusion = new Semaphore(1);
	
	/**
	 * Semaforo que define los elementos disponibles para ser leidos
	 */
	private static Semaphore sElementos = new Semaphore(0);
	
	/**
	 * Semaforo que define los huegos disponibles para escribir
	 */
	private static Semaphore sHuecos = new Semaphore(Config.TAMANO_BANDEJA);

	
	
	/**
	 * Agrega una pizza al buffer quedandose esperando el hilo hasta que haya hueco
	 * 
	 * @param pizza Pizza a agregar
	 */
	public static void ponerPizza(Pizza pizza) {
		Estadistica.setEstadoCocinero(Estadistica.ESP_DEJA_PIZZA,pizza.toString());
		try {
			//esperar a huecos libres
			sHuecos.acquire();
			//exclusion para modificacion de buffer e indice
			sExclusion.acquire();
			//escritura al buffer
			pizzas[entrada] = pizza;
			mandarEstadistica();
			entrada = (entrada + 1) % Config.TAMANO_BANDEJA;
			//liberar exclusion a buffer e indices
			sExclusion.release();
			//definir un elemento mas disponible para ser leido
			sElementos.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Saca una pizza del buffer. El hilo se queda esperando hasta que haya
	 * algun elemnento disponible en el buffer
	 *  
	 * @return La pizza sacada del buffer
	 */
	public static Pizza cogerPizza() {
		Estadistica.setEstadoRepartidores(Estadistica.ESP_COGE_PIZZA,"");
		Pizza p = null;
		try {
			//esperar a un elemento disponible
			sElementos.acquire();
			//exclusion para modificacion de buffer e indice
			sExclusion.acquire();
			//lectura dl buffer
			p = pizzas[salida];
			pizzas[salida]=null;
			mandarEstadistica();
			salida= (salida+ 1) % Config.TAMANO_BANDEJA;
			//liberar exclusion 
			sExclusion.release();
			//Definir hueco libre
			sHuecos.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return p;
	}
	
	
	/**
	 * Manda el estado del buffer a las estadisticas
	 */
	private static void mandarEstadistica() {
		String p="";
		for (Pizza pizza : pizzas) {
			if (pizza!=null)
			p+=pizza+", ";
		}
		Estadistica.setBandeja(p);
	}
}
