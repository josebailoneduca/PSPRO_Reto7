package reto7_08b.b;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Proveedor extends Thread {
	Mesa mesa;
	
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
