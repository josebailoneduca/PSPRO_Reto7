package reto7_02a.a;

import java.util.Random;


/**
 * Genera pedidos aleatorios en un bucle infinito esperando un tiempo entre pedidos.
 * Tras generar un pedido lo agrega al buffer de Pedidos
 *  
 * @author Jose Javier Bailon Ortiz
 */
public class GeneradorPedidos {
	/**
	 * Id del ultimpo pedido
	 */
	private int ultimoPedido=0;
	
	/**
	 * Tipos de pizza disponibles
	 */
	private String [] nombres= {"Carbonara","Cuatro estaciones","Napolitana","Cuatro quesos", "De Peperoni", "Marinara", "Siciliana", "Margarita"};
	
	/**
	 * Constructor. Inicia el bucle de crear pedido>agregar al buffer>esperar
	 */
	public GeneradorPedidos() {
		while (true) {
			Pedidos.agregarPedido(crearPedido());

			try {
				Thread.sleep(Config.MIN_CREA_PEDIDO,Config.MAX_CREA_PEDIDO);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Crea un pedido 
	 * 
	 * @return El pedido creado
	 */
	private Pedido crearPedido() {
		ultimoPedido++;
		return new Pedido(ultimoPedido, nombres[new Random().nextInt(0,nombres.length)]);
	}

	
}
