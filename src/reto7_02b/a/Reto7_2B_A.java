package reto7_02b.a;

/**
 * b). Se tienen dos procesos que modelan una planta embotelladora de bebidas, y que trabajan en
 * paralelo:
 * • «Embotellador» se encarga de preparar botellas.
 * • «Empaquetador» se encarga de empaquetar y reponer las cajas donde se van colocando las botellas.
 * 
 * Cada vez que el embotellador prepara una botella, ésta se coloca en una caja, que tiene una
 * capacidad de 10 botellas. Si al colocar la botella la caja queda llena, se envía una señal al
 * empaquetador, que toma la caja, la sella y la guarda en un almacén. El empaquetador deposita una
 * nueva caja, totalmente vacía. Mientras el empaquetador está haciendo su labor, el embotellador
 * no puede colocar sus botellas, ya que en esos momentos no hay una caja disponible.
 */
public class Reto7_2B_A {

	public static void main(String[] args) {
		Thread embotellador = new Thread(new Embotellador());
		Thread empaquetador= new Thread(new Empaquetador());
		

		embotellador.start();
		empaquetador.start();
		
		
		
	}

}
