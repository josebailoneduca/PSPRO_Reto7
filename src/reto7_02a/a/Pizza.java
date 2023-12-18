package reto7_02a.a; 


/**
 * DTO de pizza usado entre los cocineros y los repartidores
 *  
 * @author Jose Javier Bailon Ortiz
 */
public class Pizza {
	
	/**
	 * Id de la pizza
	 */
	private int id;
	
	/**
	 * Nombre de la pizza
	 */
	private String nombre;
	
	/**
	 * Constructor
	 * @param id Id de la pizza
	 * @param nombre Nombre de la pizza
	 */
	public Pizza(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
	/**
	 * Devuelve la id
	 * @return La id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Devuelve el nombre
	 * @return El nombre
	 */
	public String getNombre() {
		return nombre;
	}
	@Override
	public String toString() {
		return "Pizza " + id + "(" + nombre + ")";
	}	
	
	
}
