package reto7_10.b;

 

/**
 * <p>
 * Main del Reto7_10_B.
 * </p>
 * <p>
 * El problema esta resuelto usando un monitor que controla el comportamiento y espera de clientes y barbero. 
 * </p>
 * <p>
 * La clase Barberia contiene el monitor que controla el flujo de hilos y es utilizada por los hilos de Cliente y Barbero en su funcion run. 
 * Ver la clase Barberia, Barbero y Cliente para conocer los detalles del control.
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
public class Reto7_10_B {

 
	public static void main(String[] args) {
		//instanciar barberia
		Barberia barberia = new Barberia();
		
		//iniciar barbero
		new Barbero(barberia).start();
		
		//iniciar generador de clientes
		new GeneradorClientes(barberia).start();
		
		

	}
}
