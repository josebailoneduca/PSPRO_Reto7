package reto7_04.a;

import java.util.Iterator;

/**
 * b). Se tienen dos procesos que modelan una planta embotelladora de bebidas, y que trabajan en
 * paralelo:
 * • «Embotelladores» se encargan de preparar botellas.
 * • «Empaquetador» se encarga de empaquetar y reponer las cajas donde se van colocando las botellas.
 * 
 * Cada vez que un embotellador prepara una botella, ésta se coloca en una caja, que tiene una
 * capacidad de 10 botellas. Si al colocar la botella la caja queda llena, se envía una señal al
 * empaquetador, que toma la caja, la sella y la guarda en un almacén. El empaquetador deposita una
 * nueva caja, totalmente vacía. Mientras el empaquetador está haciendo su labor, los embotelladores
 * no puede colocar sus botellas, ya que en esos momentos no hay una caja disponible.
 */
public class Reto7_4_A {

	public static void main(String[] args) {
		Thread[]embotelladores = new Thread[Config.N_EMBOTELLADORES];
		for (int i=0;i<Config.N_EMBOTELLADORES;i++) {
		embotelladores[i]= new Thread(new Embotellador(i));
		}
		Thread empaquetador= new Thread(new Empaquetador());
		
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
