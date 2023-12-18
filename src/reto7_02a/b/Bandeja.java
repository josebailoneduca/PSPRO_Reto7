package reto7_02a.b;

import java.util.concurrent.LinkedBlockingDeque;


/**
 * Buffer para pasar las pizzas desde los cocineros a los repartidores.
 * Esta construido con una LinkedBlockingDeque de longitud finita
 *  
 * @author Jose Javier Bailon Ortiz
 */
public class Bandeja {

	private static LinkedBlockingDeque<Pizza> pizzas = new LinkedBlockingDeque<Pizza>(Config.TAMANO_BANDEJA);
	
	
	
	public static void ponerPizza(Pizza pizza) {
  		Estadistica.setEstadoCocinero(Estadistica.ESP_DEJA_PIZZA,pizza.toString());
			try {
				pizzas.put(pizza);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			mandarEstadistica();
	}

	
	
	public static Pizza cogerPizza() {
		Estadistica.setEstadoRepartidores(Estadistica.ESP_COGE_PIZZA,"");
			Pizza p = null;
//			do {
//				try {
					try {
						p=pizzas.take();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			} while (p==null);
			mandarEstadistica();

		return p;
	}
	
	
	private static void mandarEstadistica() {
		String p="";
		for (Pizza pizza : pizzas) {
			if (pizza!=null)
			p+=pizza+", ";
		}
		Estadistica.setBandeja(p);
	}
}
