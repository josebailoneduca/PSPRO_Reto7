package reto7_14.a;

/**
 * <p>
 * Main del Reto7_14_A.
 * </p>
 * <p>
 * Se crea el aparcamiento y se inicia el generador de coches. Tras eso inicia
 * un bucle que muestra el estado del aparcamiento cada 500ms.
 * </p>
 * <p>
 * El generador de coches ira creando hebras Coche que entraran y saldran del
 * aparcamiento.
 * </p>
 * <p>
 * El aparcamiento en si es un monitor que controla la entrada y salida de
 * vehiculos. Utiliza tres listas:
 * <ul>
 * <li>Una cola completa de todos los coches(residentes y no residentes)
 * esperando a entrar</li>
 * <li>Una cola con solo los residentes que esperan a entrar</li>
 * <li>Un listado de coches en el interior del aparcamiento</li>
 * </ul>
 * </p>
 * <p>
 * Ademas lleva un seguimiento de las plazas libres que quedan en el
 * aparcamiento.
 * </p>
 * <p>
 * Cuando un coche intenta entrar se le adjudica un numero de ticket. Tras eso
 * debe pasar la comprobacion de que quedan plazas libres y de que su ticket es
 * al que le toca entrar.
 * </p>
 * <p>
 * Elcritero para decidir a que ticket le toca entrar al aparcamiento es el
 * siguiente:
 * <ul>
 * <li>Si la cantidad de residentes esperando es mayor o igual que la cantidad
 * de plazas libres el ticket sera el del residente que lleva mas tiempo
 * esperando.</li>
 * <li>En otro caso le toca al coche que lleve mas tiempo esperando sea
 * residente o no</li>
 * </ul>
 * </p>
 * 
 * @author Jose Javier Bailon Ortiz
 * @see Aparcamiento
 * @see GeneradorDeCoches
 * @see Coche
 * 
 */
public class Reto7_14_A {

	public static void main(String[] args) {
		
		//crear aparcamiento
		Aparcamiento aparcamiento = new Aparcamiento();

		//iniciar generador de coches
		new GeneradorDeCoches(aparcamiento).start();

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
}// fin de clase
