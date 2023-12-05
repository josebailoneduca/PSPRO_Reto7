package reto7_2a.b;

import java.util.Random;

public class Cocinero extends Thread{
private int indice;

	
	public Cocinero(int indice) {
	this.indice = indice;
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
		Estadistica.setEstadoCocinero(Estadistica.COCINANDO,pedido.toString());
		try {
			Thread.currentThread().sleep(new Random().nextInt(Config.MIN_COCINAR,Config.MAX_COCINAR));
		} catch (InterruptedException e) {}
		return new Pizza(pedido.getId(), pedido.getNombrePizza());
	}


	public int getIndice() {
		return indice;
	}

}
