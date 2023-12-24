package reto7_12.b;



/**
 * <p>
 * Main del Reto7_8B_B.
 * </p>
 * <p>
 * En esta clase main se crea un buffer, un productor y varios consumidores. El productor y cada consumidor son hebras.
 * </p>
 * <p>
 * El productor va introduciendo a velocidad variable elementos al buffer y estos son leidos a diferente 
 * velocidad por cada consumidor. Cuando todos los consumidores lo han leido se considera elminado.
 * </p>
 * <p>
 * El control de insercion y borrado se realiza en el propio buffer que es un monitor. A traves de contadores, cabeza escritora y 
 * cabezas lectoras controla cuando y donde se puede escribir y cuando y por donde debe leer cada consumidor.
 * </p>
 * <p>
 * Para ver en detalle ese control vea la documentacion de la clase Buffer
 * </p>
 * @author Jose Javier Bailon Ortiz
 * @see Buffer
 * @see Productor
 * @see Consumidor
 * 
 */
public class Reto7_12_B {
 
	public static void main(String[] args) {
		//inicializar estadistica	
		Estadistica.inicializar();
		
		//crear buffer
		Buffer buffer = new Buffer();
		
		//crear productor
		Productor productor = new Productor(buffer);
		
		//crear consumidores
		Consumidor[] consumidores = new Consumidor[Config.N_CONSUMIDORES];
		for (int i=0;i<Config.N_CONSUMIDORES;i++) {
			consumidores[i]=new Consumidor(i, buffer);
		}
		
		//iniciar carrera de hebras
		productor.start();
		for (Consumidor consumidor : consumidores) {
			consumidor.start();
		}
		
		
	 
		
	}
}
