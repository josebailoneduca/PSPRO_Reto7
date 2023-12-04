package reto7_2a.a;

public class Cocinero implements Runnable{
private Pedidos pedidos;
private int id;

	
	public Cocinero(Pedidos pedidos, int id) {
	this.pedidos = pedidos;
	this.id = id;
}


	@Override
	public void run() {
		while(true) {
		Pedido pedido = Pedidos.cogerPedido();
		Pizza pizza = cocinar(pedido);
		Bandeja.ponerPizza();
		}
	}


	private Pizza cocinar(Pedido pedido) {
		return new Pizza(pedido.getId(), pedido.getNombrePizza());
	}


	public int getId() {
		return id;
	}

}
