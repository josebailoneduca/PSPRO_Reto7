package reto7_02b.b;

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
public class Reto7_2B_B {

	public static void main(String[] args) {
		
		Fabrica fabrica= new Fabrica();
		Estadistica.setFabrica(fabrica);
		Thread embotellador = new Thread(new Embotellador(fabrica));
		Thread empaquetador= new Thread(new Empaquetador(fabrica));
		

		embotellador.start();
		empaquetador.start();
		
		
		
	}

}
