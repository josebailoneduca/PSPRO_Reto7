package reto7_2a.b;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;

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
			do {
				try {
					p=pizzas.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} while (p==null);
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
