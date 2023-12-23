package reto7_07.b;
public class Reto7_7_B {

	/**
	 * <p>
	 * Main del Reto7_7_B.
	 * </p>
	 * <p>
	 * Simula el uso de un recurso por parte de multiples hebras de tipo A y B. Las hebras de tipo A solo pueden acceder a usar 
	 * el recurso si almenos el doble de hebras de tipo B estan dentro.  Hay un limite maximo de hebras que pueden usarlo de manera simultanea.
	 * </p>
	 * <p>
	 * Para lograr ese comportamiento la clase Recurso que representa el recurso contabiliza las hebras que estan usandolo y cuando alguno intenta acceder
	 * pone en espera ocupada las hebra si no se da la condicion descrita. Hace el mutex para los contadores usando un cerrojo.
	 * </p>
	 * <p>
	 * Esta clase Main crea el recurso y lanza los hilos de tipo A y B segun lo descrito en la configuracion. 
	 * Tras eso inicia un bucle que muestra en pantalla el estado de los hilos cada 500ms 
	 * </p>
	 * 
	 * @author Jose Javier Bailon Ortiz
	 * 
	 */
	public static void main(String[] args) {

		//crear recurso e hilos
		Recurso r = new Recurso();
		Thread[] hilos = new Thread[Config.N_A+Config.N_B];
		for (int i=0;i<Config.N_A;i++) {
			hilos[i]=new A(i,r);
		}
		for (int i=0;i< Config.N_B;i++) {
			hilos[i+Config.N_A]=new B(i,r);
		}
		
		for (Thread hilo : hilos) {
			hilo.start();
		}
		
		
		//bucle para mostrar estado del sistema
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
