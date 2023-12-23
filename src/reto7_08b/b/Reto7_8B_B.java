package reto7_08b.b;

 
/**
 * <p>
 * Main del Reto7_8B_B.
 * </p>
 * <p>
 * En esta implementacion el control de acceso a los ingredientes es realizado por la clase Mesa con dos semaforos que controlan 
 * cuando se ha puesto ingrediente y cuando han retirado los ingredientes puestos en la mesa.
 * </p>
 * <p>
 * La Mesa contiene dos semaforos. 
 * Uno para asegurar que se han puesto los ingredientes en la mesa y otro para asegurar que hay que poner ingredientes en la mesa.
 * </p>
 * <p>
 * Cuando el proveedor entra a colocar ingredientes en la mesa espera el semaforo de proveedor. Una vez adquirido pone los ingredientes y 
 * libera un permiso para acceder a los ingredientes. Cuando los mafiosos van comprobando los ingredientes deben ir esperando el semaforo 
 * de ingredientes. Si son los que necesita retira los ingredientes y libera el semaforo de proveedor. Si no son los que necesit libera un
 * permiso del semaforo de ingredientes.
 * </p>
 * 
 * @author Jose Javier Bailon Ortiz
 * @see Mesa
 */
public class Reto7_8B_B {

 

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
