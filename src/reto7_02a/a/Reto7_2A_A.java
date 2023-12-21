package reto7_02a.a;


/**
 * <p>
 * Clase main del ejercicio Reto7_2A solucion A
 * </p>
 * 
 * <p>
 * La solucion esta implementada con buffers  cuyo acceso esta controlado por semaforos
 * en un sistema productor consumidores. 
 * </p>
 *
 *<p>
 * GeneradorPedidos(productor) genera Pedidos y usa el buffer Pedidos para pasarselos a los concineros(consumidores). El buffer
 * Pedidos es un buffer infinito.
 * </p>
 * <p> 
 * Tras recoger un pedido el cocinero lo prepara y pasa de consumidor a productor usando el buffer Bandeja para pasarselas
 * a los repartidores(consumidores). El buffer Bandeja es un buffer finito.
 * </p>
 * <p>
 * GeneradorPedidos > Pedidos(DTO Pedido) > Cocinero > Bandeja(DTO pizza) > repartidor
 * </p>
 * 
 *  
 * @author Jose Javier Bailon Ortiz
 * 
 * */
public class Reto7_2A_A {

	/**
	 * Punto de entrada a la aplicacion. Genera los cocineros y los repartidores e inicia su actividad. 
	 * Tras eso inicia el GeneradorPedidos
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		Cocinero[] cocineros = new Cocinero[Config.N_COCINEROS];
		Repartidor[] repartidores= new Repartidor[Config.N_REPARTIDORES];

		//generar concineros y repartidores e iniciar su actividad
		for (int i = 0; i < cocineros.length; i++)
			cocineros[i] = new Cocinero(i);
		
		for (int i = 0; i < repartidores.length; i++)
			repartidores[i] = new Repartidor(i);
 
		for (int i = 0; i < cocineros.length; i++)
			cocineros[i].start();
		
		for (int i = 0; i < repartidores.length; i++)
			repartidores[i].start();
 
		
		
		//GENERAR PEDIDOS
		
		
		new GeneradorPedidos();
			
			
		
		
		
	}

}
