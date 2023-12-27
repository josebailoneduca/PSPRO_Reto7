package reto7_13.a;


/**
 * <p>
 * Main del Reto7_8B_A.
 * </p>
 * <p>
 * En esta clase main se crea un buffer, un productor y varios consumidores. El productor y cada consumidor son hebras.
 * </p>
 * <p>
 * El productor va introduciendo a velocidad variable elementos al buffer y estos son leidos a diferente 
 * velocidad por cada consumidor. Cuando todos los consumidores lo han leido se considera eliminado.
 * </p>
 * <p>
 * El control de insercion y borrado se realiza en el propio buffer. A traves de semaforos, cabeza escritora y 
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
public class Reto7_13_A {


	
	
	public static void main(String[] args) {
		if (Config.N_MATEMATICOS>1&&Config.N_NO_MATEMATICOS==1) {
			
			System.out.println("No puede haber un solo no matematico y mas de un matematico");
			System.out.println("Cambie los parametros en el archivo de configuracion reto7_13.a.Config.java");
			System.exit(0);
		}
		
		
		
		
		
		Bote bote = new Bote();
		
		for (int i=0;i<Config.N_MATEMATICOS;i++) {
			new Viajero(true,"ma"+i,bote).start();
		}
		
		for (int i=0;i<Config.N_NO_MATEMATICOS;i++) {
			new Viajero(false,"no"+i,bote).start();
		}
		
		bote.start();

		while (true) {
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
