package reto7_2a.a;

import java.util.Random;

public class Cocinero implements Runnable{
private int id;

	
	public Cocinero(int id) {
	this.id = id;
}


	@Override
	public void run() {
		while(true) {
		Pedido pedido = Pedidos.cogerPedido();
		Pizza pizza = cocinar(pedido);
		Bandeja.ponerPizza(pizza); 
		}
	}


	private Pizza cocinar(Pedido pedido) {
		try {
			Thread.currentThread().sleep(new Random().nextInt(Config.MIN_COCINAR,Config.MAX_COCINAR));
		} catch (InterruptedException e) {}
		return new Pizza(pedido.getId(), pedido.getNombrePizza());
	}


	public int getId() {
		return id;
	}

}
