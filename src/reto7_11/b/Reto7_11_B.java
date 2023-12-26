package reto7_11.b;


/**
 * <p>
 * Main del Reto7_11_B.
 * </p>
 * <p>
 * En esta clase main se crea el canal y un generador de barcos el cual funciona de manera infinita creando barcos a velocidades cambiantes. 
 * </p>
 * <p>
 * Se usa la clase Canal como control de acceso de los barcos al canal. 
 * </p>
 * <p>
 * Esta clase controla el flujo de barcos mediante cerrojo y la direccionActual, la proximaDireccion y capacidadRestante asi como la cantidad de barcos que hay esperando en cada entrada.
 * </p>
 * <p>
 * Cuando un barco intenta entrar se le hace esperar si su direccion no es la actual o  se va a cambiar de direccion(actual y proxima no coinciden) 
 * o el canal esta lleno(capacidadrestante = 0).
 * </p>
 * <p>
 * Cuando un barco sale del canal deja una plaza libre en el. 
 * En caso de que la direccion actual y proxima sean la misma se recalcula cual sera la proxima direccion  dando el paso a la entrada 
 * con mas del doble de barcos esperando que la otra. En caso de no haber ninguna en esa situacion la direccion no cambia.
 * </p>
 * <p>
 * Si cuando un barco va a salir es el ultimo en el canal entonces actualiza la direccion actual con la direccion proxima
 * </p>
 * <p>
 * En caso de que la direccion actual y proxima sean la misma al final del metodo de salir, se despierta a los hilos que esperan en la 
 * puerta origen de esa direccion. Por ejemplo si al final del metodo salir la direccion actual es Este->oeste y la proxima direccion
 * es tambien Este->oeste, se despiertan las hebras que hay esperando en la condicion puertaEste. 
 * </p>
 * 
 * @author Jose Javier Bailon Ortiz
 * @see GeneradorDeBarcos
 * @see Canal
 * @see Barco
 */
public class Reto7_11_B {
 
	//El Canal de Panamá lo pueden atravesar barcos desde ambos extremos. 
	//Como el canal tiene un solo carril, en un momento dado sólo puede 
	//haber barcos cruzándolo en un único sentido. El canal tiene una capacidad 
	//máxima de cuatro barcos simultáneamente. 
	
	public static void main(String[] args) {
		//crear canal
		Canal canal =  new Canal();
		
		//generar barcos
		new GeneradorDeBarcos(canal).start();
	}
}
