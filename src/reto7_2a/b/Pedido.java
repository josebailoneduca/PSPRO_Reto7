package reto7_2a.b;

public class Pedido {
	private int id;
	private String pizza; 

	public Pedido(int id, String pizza) {
		super();
		this.id = id;
		this.pizza = pizza;
	}

	public int getId() {
		return id;
	}

	public String getNombrePizza() {
		return pizza;
	}

	@Override
	public String toString() {
		return "Pedido "+ id + "(" + pizza + ")";
	}

	
}
