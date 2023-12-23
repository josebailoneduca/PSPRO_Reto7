package reto7_08a.b;


/**
 * <p>
 * Main del Reto7_8A_A.
 * </p>
 * <p>
 * En esta implementacion el control de acceso a los ingredientes es realizado por la clase Mesa con dos CyclicBarrier que controlan cuando se ha puesto ingrediente 
 * y cuando han comprobado y en su caso consumido los ingredientes puestos en la mesa.
 * </p>
 * <p>
 * La Mesa contiene dos barreras. Una para asegurar que se han puesto los ingredientes en la mesa y todos los mafiosos estan en 
 * situacion de comprobar los ingredientes y otra de salida que asegura que los ingredientes se han comprobado y consumido.
 * </p>
 * <p>
 * Cuando el proveedor coloca ingredientes en la mesa y todos los mafiosos estan listos para comprobarlos se levanta la barrera de entrada. Entonces
 * los mafiosos comprueban los alimentos y acceden a la barrera de salida junto con el proveedor excepto el mafioso que come. Ese mafioso
 * llega a la barrera de salida una vez ha comido, momento en el que se levanta la barrera de salida y el ciclo empieza de nuevo.
 * </p>
 * 
 * @author Jose Javier Bailon Ortiz
 * @see Mesa
 */
public class Reto7_8A_B {


	public static void main(String[] args) {
		//preparar mesa, mafiosos y proveedor
		Mesa mesa = new Mesa();
		Mafioso mafiosoA=new Mafioso(Ingrediente.PASTA, mesa);
		Mafioso mafiosoB=new Mafioso(Ingrediente.TOMATE, mesa);
		Mafioso mafiosoC=new Mafioso(Ingrediente.QUESO, mesa);
		Proveedor proveedor = new Proveedor(mesa);
		
		//iniciar los hilos
		mafiosoA.start();
		mafiosoB.start();
		mafiosoC.start();
		proveedor.start();
		
		
		//bucle que muestra el estado cada 500 ms
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
