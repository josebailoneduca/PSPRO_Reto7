package reto7_08a.b;
public class Reto7_8A_B {

	// En una mesa hay cuatro personas: tres mafiosos y un proveedor. Cada mafioso come platos uno
	// detrás de otro. Para poder comerse un plato se necesitan tres ingredientes: pasta, tomate y queso. Uno de
	// los mafiosos tiene pasta, el otro tomate y el otro queso. El proveedor tiene una cantidad infinita de los tres
	// ingredientes.
	// Inicialmente, el agente coloca dos de los ingredientes en la mesa, escogidos al azar. El mafioso
	// que tiene el ingrediente que falta toma lo que hay en la mesa, cocina un plato y se lo come.
	// Cuando termina, se lo indica al proveedor. El proveedor entonces coloca otros dos ingredientes y
	// el ciclo se repite una y otra vez. 

	// . Se pide construir un programa compuesto por cuatro hilos (tres mafiosos y el proveedor) que
	// estén correctamente sincronizados. 

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
