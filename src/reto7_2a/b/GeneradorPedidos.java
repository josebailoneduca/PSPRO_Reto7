package reto7_2a.b;

import java.util.Random;

public class GeneradorPedidos {
	int ultimoPedido=0;
	String [] nombres= {"Carbonara","Cuatro estaciones","Napolitana","Cuatro quesos", "De Peperoni", "Marinara", "Siciliana", "Margarita"};
	public GeneradorPedidos() {
		while (true) {
			Pedidos.agregarPedido(crearPedido());

			try {
				Thread.currentThread().sleep(Config.MIN_CREA_PEDIDO,Config.MAX_CREA_PEDIDO);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private Pedido crearPedido() {
		ultimoPedido++;
		return new Pedido(ultimoPedido, nombres[new Random().nextInt(0,nombres.length)]);
	}

	
}
