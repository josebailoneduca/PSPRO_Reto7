package reto7_02a.a;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Pedidos {
	private static LinkedList<Pedido> pedidos=new LinkedList<Pedido>();
	private static Semaphore exclusion = new Semaphore(1);
	private static Semaphore elementos = new Semaphore(0);
	
	
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
	
	private static void mandarEstadistica() {
		String p="";
		for (Pedido pedido : pedidos) {
			p+=pedido+", ";
		}
		Estadistica.setPedidos(p);
	}
}
