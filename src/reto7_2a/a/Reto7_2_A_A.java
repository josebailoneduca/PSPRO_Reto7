package reto7_2a.a;

/**
 * 2 a). Cocineros y repartidores. 
 * Cada cocinero se dedica a preparar pizzas,según van llegando pedidos. Cada vez 
 * que una pizza está hecha, el cocinero la coloca sobre una bandeja para su reparto.
 * A su vez, cada repartidor espera a que haya una pizza sobre la bandeja, la retira 
 * y se la lleva al cliente correspondiente. Tras ello regresa a la pizzeria y espera 
 * por una nueva pizza en la bandeja. Como añadido, la bandeja tiene una capacidad 
 * limitada: no puede haber mas de MAX pizzas pendientes de reparto.
 */
public class Reto7_2_A_A {

	public static void main(String[] args) {
		Thread[] loros = new Thread[Config.N_LOROS];

		for (int i = 0; i < loros.length; i++)
			loros[i] = new Thread(new Loro(i));

		for (int i = 0; i < loros.length; i++)
			loros[i].start();

	}

}
