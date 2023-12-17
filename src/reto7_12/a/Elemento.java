package reto7_12.a;

public class Elemento {

	int id;
	int lecturasRestantes=Config.N_CONSUMIDORES;
	
	
	public Elemento(int id) {
		super();
		this.id = id;
	}


	public int getId() {
		return id;
	}


	public int gastarLectura() {
		return --lecturasRestantes;
	}


	@Override
	public String toString() {
		return "El " + id ;
	}
	
}
