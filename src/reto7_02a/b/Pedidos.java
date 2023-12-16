package reto7_02a.b;

import java.util.concurrent.LinkedBlockingDeque;

public class Pedidos {
	private static LinkedBlockingDeque<Pedido> pedidos=new LinkedBlockingDeque<Pedido>();
	
	
	public static void agregarPedido(Pedido pedido) {
		try {
			pedidos.put(pedido);
			mandarEstadistica();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	public static Pedido cogerPedido() {
		Estadistica.setEstadoCocinero(Estadistica.ESP_COGE_PEDIDO,"");
		Pedido pedido=null;
		do {
			try {
				pedido=pedidos.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}while(pedido==null);
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
