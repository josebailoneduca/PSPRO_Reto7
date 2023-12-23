package reto7_08b.a;

/**
 * <p>
 * Main del Reto7_8B_A.
 * </p>
 * <p>
 * En esta implementacion el control de acceso a los ingredientes es realizado por un monitor implementado en la clase Mesa.
 * </p>
 * <p>
 * Tanto los mafiosos como el proveedor quedan a la espera durante los ciclos. Los mafiosos si no son los ingredientes que necesitan,
 * y el proveedor una vez ha puesto los ingredientes
 * </p>
 * <p>
 * Cuando un mafioso termina recoge los ingredientes notifica a los hilos en espera despertando asi al proveedor. Cuando el proveedor pone ingredientes 
 * notifica a los hilos en espera despertando asi a los mafiosos
 * </p>
 * 
 * @author Jose Javier Bailon Ortiz
 * @see Mesa
 */
public class Reto7_8B_A {

 
	public static void main(String[] args) {
		//crear mesa, mafisosos y proveedor
		Mesa mesa = new Mesa();
		Mafioso mafiosoA=new Mafioso(Ingrediente.PASTA, mesa);
		Mafioso mafiosoB=new Mafioso(Ingrediente.TOMATE, mesa);
		Mafioso mafiosoC=new Mafioso(Ingrediente.QUESO, mesa);
		Proveedor proveedor = new Proveedor(mesa);
		
		//poner los hilos a funcionar
		mafiosoA.start();
		mafiosoB.start();
		mafiosoC.start();
		proveedor.start();
		
		//mostrar el estado del sistema cada 500 ms
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
