package reto7_2a.a;

import reto7_2a.b.GeneradorPedidos;

/**
 * 2 a). Cocineros y repartidores. 
 * Cada cocinero se dedica a preparar pizzas,según van llegando pedidos. Cada vez 
 * que una pizza está hecha, el cocinero la coloca sobre una bandeja para su reparto.
 * A su vez, cada repartidor espera a que haya una pizza sobre la bandeja, la retira 
 * y se la lleva al cliente correspondiente. Tras ello regresa a la pizzeria y espera 
 * por una nueva pizza en la bandeja. Como añadido, la bandeja tiene una capacidad 
 * limitada: no puede haber mas de MAX pizzas pendientes de reparto.
 */
public class Reto7_2A_A {

	public static void main(String[] args) {
		Cocinero[] cocineros = new Cocinero[Config.N_COCINEROS];
		Repartidor[] repartidores= new Repartidor[Config.N_REPARTIDORES];

		for (int i = 0; i < cocineros.length; i++)
			cocineros[i] = new Cocinero(i);
		for (int i = 0; i < repartidores.length; i++)
			repartidores[i] = new Repartidor(i);
 
		for (int i = 0; i < cocineros.length; i++)
			cocineros[i].start();
		for (int i = 0; i < repartidores.length; i++)
			repartidores[i].start();
 
		
		
		//GENERAR PEDIDOS
		
		
		new GeneradorPedidos();
			
			
		
		
		
	}

}
