package reto7_02a.b;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * Buffer FIFO de conexion entre el generador de pedidos y los cocineros.
 * Contiene los objetos en un LinkedBlockingDeque de longitud infinita. 
 *  
 * @author Jose Javier Bailon Ortiz
 */
public class Pedidos {
	
	/**
	 * Lista de pedidos en el buffer
	 */
	private static LinkedBlockingDeque<Pedido> pedidos=new LinkedBlockingDeque<Pedido>();
	
	
	/**
	 * Agrega un pedido al buffer
	 * 
	 * @param pedido El pedido a agregar
	 */
	public static void agregarPedido(Pedido pedido) {
		try {
			pedidos.put(pedido);
			mandarEstadistica();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Saca pedido del buffer
	 * 
	 * @return El pedido mas antiguo
	 */
	public static Pedido cogerPedido() {
		Estadistica.setEstadoCocinero(Estadistica.ESP_COGE_PEDIDO,"");
		Pedido pedido=null;
 
			try {
				pedido=pedidos.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
 
		 return pedido;
	}
	
	
	/**
	 * Manda a estadistica el contenido del buffer
	 */
	private static void mandarEstadistica() {
		String p="";
		for (Pedido pedido : pedidos) {
			p+=pedido+", ";
		}
		Estadistica.setPedidos(p);
	}
}
