package reto7_08a.a;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


/**
 * Se encarga de ir colocando ingredientes diferentes sobre la mesa. 
 * Tiene un bucle infinito consistente en poner ingreientes diferentes de manera
 * indefinida sobre la mesa.
 */
public class Proveedor extends Thread {
	
	/**
	 * Referencia a la mesa
	 */
	Mesa mesa;
	
	/**
	 * Constructor 
	 * @param mesa La mesa en la que poner los ingredientes
	 */
	public Proveedor(Mesa mesa) {
		this.mesa = mesa;
	}

	@Override
	public void run() {
		Random r = new Random();
		while(true) {
			//seleccionar ingredientes
			List<Ingrediente> listaIng =Arrays.asList(Ingrediente.values()); 
			Collections.shuffle(listaIng);
			Iterator<Ingrediente> it =listaIng.listIterator();
			Ingrediente ing1 = it.next();
			Ingrediente ing2 = it.next();
			
			//poner ingredientes en la mesa
			mesa.poner(ing1, ing2);
		}
	}

}
