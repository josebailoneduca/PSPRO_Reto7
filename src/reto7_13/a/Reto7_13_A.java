package reto7_13.a;


/**
 * <p>
 * Main del Reto7_13_A.
 * </p>
 * <p>
 * En esta clase main se comprueba si la configuracion es correcta (debe haber
 * al menos dos no matematicos si hay matematicos), crea el bote, crea los
 * viajeros e inicia los hilos de los viajeros y el del bote.
 * </p>
 * <p>
 * Los viajeros durante su carrera piden subir al bote y una vez que han
 * accedido piden bajar de el.
 * </p>
 * <p>
 * El bote controla el flujo de entrada y salida de viajeros. En la
 * orilla deja entrar viajeros y les va dejando salir del bote en la orilla 
 * destino siguiendo unas normas para garantizar que no se quede nadie sin viajar. 
 * Cuando ya no quedan viajeros que trasladar el programa termina.
 * </p>
 * 
 * @author Jose Javier Bailon Ortiz
 * @see Bote
 * @see Viajero
 * 
 */
public class Reto7_13_A {


	
	
	public static void main(String[] args) {
		
		//comprobar que la configuracion es valida
		if (Config.N_MATEMATICOS>1&&Config.N_NO_MATEMATICOS==1) {
			System.out.println("No puede haber un solo no matematico y mas de un matematico");
			System.out.println("Cambie los parametros en el archivo de configuracion reto7_13.a.Config.java");
			System.exit(0);
		}
		
		//crear bote
		Bote bote = new Bote();
		
		//crear e iniciar viajeros
		for (int i=0;i<Config.N_MATEMATICOS;i++) {
			new Viajero(true,"ma"+i,bote).start();
		}
		
		for (int i=0;i<Config.N_NO_MATEMATICOS;i++) {
			new Viajero(false,"no"+i,bote).start();
		}
		
		//Iniciar bote
		bote.start();

		
		
		// bucle que muestra el estado del sistema en pantalla cada 500 ms
		while (true) {
			Estadistica.mostrarEstadistica();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}// fin de main
}//fin de clase
