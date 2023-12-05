package reto7_2a.a;

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
			exclusion.release();
			elementos.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	public static Pedido cogerPedido() {
		Pedido pedido=null;
		try {
			elementos.acquire();
			exclusion.acquire();
			pedido = pedidos.removeFirst();
			exclusion.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 return pedido;
	}
	
	private static void mandarEstadistica() {
		String s="";
		for (Pedido pedido : pedidos) {
			s+=pedido;
		}
		Estadistica.setBandeja(s);
	}
}
