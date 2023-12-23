package reto7_09.b;

/**
 * DTO de hamburguesa. Consta de una id propia y tres enteros. Dos de los
 * enteros son los panecillos usados, el otro es el pack de ingredientes usados
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Hamburguesa {

	/**
	 * id de la hamburguesa
	 */
	private int id;
	
	/**
	 * Panecillo 1 de la hamburguesa
	 */
	private int panecillo1;
	
	/**
	 * Panecillo 2 de la hamburguesa
	 */
	private int panecillo2;
	
	/**
	 * Pack de ingredientes usado
	 */
	private int ingrediente;

	/**
	 * Constructor  
	 * @param id Id de la hamburguesa
	 * @param panecillo1 Panecillo 1
	 * @param panecillo2 Panecillo 2
	 * @param ingrediente Pack de ingredientes
	 */
	public Hamburguesa(int id, int panecillo1, int panecillo2, int ingrediente) {
		this.id = id;
		this.panecillo1 = panecillo1;
		this.panecillo2 = panecillo2;
		this.ingrediente = ingrediente;
	}

	@Override
	public String toString() {
		return "Ham." + id + "(P" + panecillo1 + "I" + ingrediente + "P" + panecillo2 + ")";
	}

}