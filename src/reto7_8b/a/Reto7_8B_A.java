package reto7_8b.a;
public class Reto7_8B_A {

	// En una mesa hay cuatro personas: tres mafiosos y un proveedor. Cada mafioso come platos uno
	// detrás de otro. Para poder comerse un plato se necesitan tres ingredientes: pasta, tomate y queso. Uno de
	// los mafiosos tiene pasta, el otro tomate y el otro queso. El proveedor tiene una cantidad infinita de los tres
	// ingredientes.
	// Inicialmente, el agente coloca dos de los ingredientes en la mesa, escogidos al azar. El mafioso
	// que tiene el ingrediente que falta toma lo que hay en la mesa, cocina un plato y se lo come.
	// Cuando termina, se lo indica al proveedor. El proveedor entonces coloca otros dos ingredientes y
	// el ciclo se repite una y otra vez. 

	// . Cuando un mafioso toma lo que hay en la mesa y se pone a comer, el proveedor
	//   inmediatamente toma dos ingredientes y los coloca en la mesa. Así podemos tener varios
	//	 mafiosos comiendo al mismo tiempo. (Aunque esto no ocurrirá si el proveedor coloca justo el par
	//   de ingredientes que no le sirven a los mafiosos en espera, pero esto es azar). 


	public static void main(String[] args) {
		Mesa mesa = new Mesa();
		Mafioso mafiosoA=new Mafioso(Ingrediente.PASTA, mesa);
		Mafioso mafiosoB=new Mafioso(Ingrediente.TOMATE, mesa);
		Mafioso mafiosoC=new Mafioso(Ingrediente.QUESO, mesa);
		Proveedor proveedor = new Proveedor(mesa);
		
		mafiosoA.start();
		mafiosoB.start();
		mafiosoC.start();
		proveedor.start();
		
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
