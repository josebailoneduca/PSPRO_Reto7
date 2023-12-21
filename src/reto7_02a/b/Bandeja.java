package reto7_02a.b;

import java.util.concurrent.LinkedBlockingDeque;


/**
 * Buffer para pasar las pizzas desde los cocineros a los repartidores.
 * Esta construido con una LinkedBlockingDeque de longitud finita
 *  
 * @author Jose Javier Bailon Ortiz
 */
public class Bandeja {

	/**
	 * Lista de pizzas que hay en la bandeja
	 */
	private static LinkedBlockingDeque<Pizza> pizzas = new LinkedBlockingDeque<Pizza>(Config.TAMANO_BANDEJA);
	
	
	
	/**
	 * Agrega una pizza al buffer quedandose esperando el hilo hasta que haya hueco
	 * 
	 * @param pizza Pizza a agregar
	 */
	public static void ponerPizza(Pizza pizza) {
  		Estadistica.setEstadoCocinero(Estadistica.ESP_DEJA_PIZZA,pizza.toString());
			try {
				pizzas.put(pizza);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			mandarEstadistica();
	}

	
	/**
	 * Saca una pizza del buffer. El hilo se queda esperando hasta que haya
	 * algun elemento disponible en el buffer
	 *  
	 * @return La pizza sacada del buffer
	 */	
	public static Pizza cogerPizza() {
		Estadistica.setEstadoRepartidores(Estadistica.ESP_COGE_PIZZA,"");
			Pizza p = null;
					try {
						p=pizzas.take();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			mandarEstadistica();
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
