package reto7_08b.b;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
 
/**
 * Se encarga de ir colocando ingredientes diferentes sobre la mesa. 
 * Tiene un bucle infinito consistente en poner ingredientes diferentes de manera
 * indefinida sobre la mesa.
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Proveedor extends Thread {
	
	/**
	 * Referencia a la mesa
	 */
	private Mesa mesa;
	
	
	/**
	 * Constructor 
	 * @param mesa La mesa en la que poner los ingredientes
	 */
	public Proveedor(Mesa mesa) {
		this.mesa = mesa;
	}

	@Override
	public void run() {
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
