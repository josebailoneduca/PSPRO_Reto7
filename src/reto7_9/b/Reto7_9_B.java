package reto7_9.b;
public class Reto7_9_B {

	// 9 Tenemos una fábrica de hamburguesas. Tenemos a tres personas trabajando. Una persona
	// está continuamente fabricando panecillo, que va depositando en un cesto. Otra persona está
	// continuamente preparando ingredientes, que también deposita en su correspondiente cesta de
	// capacidad limitada. Una tercera persona se encarga continuamente de introducir los ingredientes
	// en los panecillos, cogiendo en cada caso dos panecillos y un pack de ingredientes. Las capacidades
	// de las cestas son distintas.
	
	
	public static void main(String[] args) {
	
		Thread prepPanecillos=new PrepPanecillos();
		Thread prepIngredientes=new PrepIngredientes();
		Thread prepHamburguesas=new PrepHamburguesas();
		
		prepPanecillos.start();
		prepIngredientes.start();
		prepHamburguesas.start();
		
		
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
