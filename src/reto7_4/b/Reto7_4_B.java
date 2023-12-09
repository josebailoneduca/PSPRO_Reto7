package reto7_4.b;
 
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
public class Reto7_4_B {

	public static void main(String[] args) {
		Fabrica fabrica=new Fabrica();
		Estadistica.setFabrica(fabrica);
		Thread[]embotelladores = new Thread[Config.N_EMBOTELLADORES];
		for (int i=0;i<Config.N_EMBOTELLADORES;i++) {
		embotelladores[i]= new Thread(new Embotellador(fabrica,i));
		}
		Thread empaquetador= new Thread(new Empaquetador(fabrica));
		
		empaquetador.start();
		for (Thread embotellador : embotelladores) {
			embotellador.start();
		}
		
		while(true) {
			Estadistica.mostrarEstadistica();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
