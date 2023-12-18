package reto7_02a.a;


/**
 * DTO de pedido usado entre el generador de pedidos y los cocineros
 *  
 * @author Jose Javier Bailon Ortiz
 */
public class Pedido {
	
	/**
	 * Id del pedido
	 */
	private int id;
	
	/**
	 * Nombre de la pizza del pedido
	 */
	private String pizza; 

	
	/**
	 * Constructor
	 * @param id La id
	 * @param pizza El nombre de pizza
	 */
	public Pedido(int id, String pizza) {
		super();
		this.id = id;
		this.pizza = pizza;
	}

	/**
	 * Devuelve la id
	 * @return La id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Devuelve el nombre de pizza
	 * @return El nombre
	 */
	public String getNombrePizza() {
		return pizza;
	}

	
	@Override
	public String toString() {
		return "Pedido "+ id + "(" + pizza + ")";
	}
}