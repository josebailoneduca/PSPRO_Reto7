package reto7_12.b;

import java.util.Vector;

public class Reto7_12_B {

	//  Contamos con un productor de difusión y N consumidores que se comunican por medio de un
	// búfer de capacidad finita. Cada elemento producido es para todos los consumidores: un elemento
	// en el búfer no desaparece hasta que ha sido leído por todos los consumidores. Cada consumidor
	// va leyendo los elementos producidos de forma secuencial con su propio ritmo. 

	
	
	public static void main(String[] args) {
		
		Estadistica.inicializar();
		Buffer buffer = new Buffer();
		Productor productor = new Productor(buffer);
		Consumidor[] consumidores = new Consumidor[Config.N_CONSUMIDORES];
		
		for (int i=0;i<Config.N_CONSUMIDORES;i++) {
			consumidores[i]=new Consumidor(i, buffer);
		}
		
		productor.start();
		for (Consumidor consumidor : consumidores) {
			consumidor.start();
		}
		
		
	 
		
	}
}
