package reto7_02a.a; 

public class Pizza {
	private int id;
	private String nombre;
	public Pizza(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	@Override
	public String toString() {
		return "Pizza " + id + "(" + nombre + ")";
	}	
	
	
}