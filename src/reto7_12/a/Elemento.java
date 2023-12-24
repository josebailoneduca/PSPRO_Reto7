package reto7_12.a;


/**
 * Representa un elemento consumible del buffer.
 * Tiene un identificador y un limite de lecturas que coincide
 * con el numero de consumidores. Conforme lo va leyendo cada consumidor
 * el numero de lecturas restantes disminuye.
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Elemento {

	/**
	 * Identificador
	 */
	private int id;
	
	/**
	 * Cantidad de lecturas restantes
	 */
	private int lecturasRestantes=Config.N_CONSUMIDORES;
	
	
	/**
	 * Constructor 
	 * 
	 * @param id La id del elemento
	 */
	public Elemento(int id) {
		this.id = id;
	}


	/**
	 * Devuelve la id del elemento
	 * 
	 * @return La id
	 */
	public int getId() {
		return id;
	}


	/**
	 * Gasta una lectura
	 * 
	 * @return Las lecturas restantes
	 */
	public int gastarLectura() {
		return --lecturasRestantes;
	}


	@Override
	public String toString() {
		return "El " + id ;
	}
	
}
