package reto7_02a.a;

import java.util.Random;

/**
 * Representa un cocinero. Tiene una carrera consistente en coger un pedido del
 * buffer Pedidos , cocinarlo y poner la pizza en el buffer Bandeja.
 *  
 * @author Jose Javier Bailon Ortiz
 */
public class Cocinero extends Thread {
	/*
	 * identificador del cocinero. Usado solo por la estadistica
	 */
	private int indice;

	
	/**
	 * Constructor.
	 * @param indice Identificador del cocinero
	 */
	public Cocinero(int indice) {
		this.indice = indice;
	}

	
	/**
	 * Carrera infinita de coger pedido del buffer Pedidos, cocinar pizza, dejar pizza en buffer Bandeja
	 */
	@Override
	public void run() {
		while (true) {
			Pedido pedido = Pedidos.cogerPedido();
			Pizza pizza = cocinar(pedido);
			Bandeja.ponerPizza(pizza);
		}
	}
	
	
	/**
	 * Devuelve el identificador
	 * @return El identificador
	 */
	public int getIndice() {
		return indice;
	}
	
	
	/**
	 * Simula el cocinado de la pizza a partir de un pedido y un tiempo aleatorio de
	 * espera.
	 * @param pedido Pedido que especifica el nombre de la pizza
	 * 
	 * @return La pizza preparada
	 */
	private Pizza cocinar(Pedido pedido) {
		Estadistica.setEstadoCocinero(Estadistica.COCINANDO, pedido.toString());
		try {
			Thread.sleep(new Random().nextInt(Config.MIN_COCINAR, Config.MAX_COCINAR));
		} catch (InterruptedException e) {
		}
		return new Pizza(pedido.getId(), pedido.getNombrePizza());
	}

	


}
