package reto7_02a.a;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;



/**
 * Buffer FIFO de conexion entre el generador de pedidos y los cocineros.
 * Contiene los objetos en un LinkedList. 
 * Controla el acceso al LinkedList mediante semaforos 
 *  
 * @author Jose Javier Bailon Ortiz
 */
public class Pedidos {
	
	/**
	 * Lista de pedidos en el buffer
	 */
	private static LinkedList<Pedido> pedidos=new LinkedList<Pedido>();
	
	/**
	 * Semaforo de exclusion para modificar el buffer
	 */
	private static Semaphore exclusion = new Semaphore(1);
	
	/**
	 * Semaforo para establecer los elemento disponibles para los consumidores
	 */
	private static Semaphore elementos = new Semaphore(0);
	
	
	/**
	 * Agrega un pedido al buffer
	 * 
	 * @param pedido El pedido a agregar
	 */
	public static void agregarPedido(Pedido pedido) {
		try {
			exclusion.acquire();
			pedidos.add(pedido);
			mandarEstadistica();
			exclusion.release();
			elementos.release();
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
			elementos.acquire();
			exclusion.acquire();
			pedido = pedidos.removeFirst();
			mandarEstadistica();
			exclusion.release();
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
