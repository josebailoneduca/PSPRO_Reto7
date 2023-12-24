package reto7_10.a;


/**
 * <p>
 * Main del Reto7_10_A.
 * </p>
 * <p>
 * El problema esta resuelto usando semaforos que controlan el comportamiento y espera de clientes y barbero. 
 * </p>
 * <p>
 * Los semaforos se encuentran en la clase Barberia y son utilizados por los hilos de Cliente y Barbero en su funcion run. 
 * </p>
 * <p>
 * Un generador de cliente se encarga de ir creando y haciendo correr a nuevos clientes a un ritmo definido que 
 * va variando segun lo especificado en el archivo de configuracion 
 * </p>
 * 
 * 
 * @author Jose Javier Bailon Ortiz
 * @see Barberia
 * @see Barbero
 * @see Cliente
 * @see GeneradorClientes
 * @see Config
 */
public class Reto7_10_A {
 
	
	
	public static void main(String[] args) {
		
		//iniciar barbero
		new Barbero().start();
		
		//iniciar generador de clientes
		new GeneradorClientes().start();
 
		
	}
}
