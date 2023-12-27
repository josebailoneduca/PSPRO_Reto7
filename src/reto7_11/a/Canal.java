/**
 * 
 */
package reto7_11.a;


/**
 * <p>
 * Monitor para controlar el paso de barcos por el canal.
 * </p>
 * <p>
 * Controla la entrada de barcos gracias principalmente a los atributos
 * direccionActual, proximaDireccion y capacidadRestante.
 * </p>
 * <p>
 * Los barcos acceden al canal ejecutando el metodo entrar() y deben avisar de
 * su salida del canal con el metodo salir()
 * </p>
 * <p>
 * Cuando un barco intenta entrar se le hace esperar si su direccion no es la
 * actual o se va a cambiar de direccion(actual y proxima no coinciden) o el
 * canal esta lleno(capacidadrestante = 0).
 * </p>
 * <p>
 * Cuando un barco sale del canal deja una plaza libre en el. En caso de que la
 * direccion actual y proxima sean la misma se recalcula cual sera la proxima
 * direccion dando el paso a la entrada con mas del doble de barcos esperando
 * que la otra. En caso de no haber ninguna en esa situacion la direccion no
 * cambia.
 * </p>
 * <p>
 * Si cuando un barco va a salir es el ultimo en el canal entonces actualiza la
 * direccion actual con la direccion proxima
 * </p>
 * <p>
 * Así el canal se vacía el canal y se cambia de dirección de uso cuando el
 * recalculo de dirección establece que la próxima dirección va a ser una
 * diferente a la actual.
 * </p>
 * 
 * 
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Canal {
	
	/**
	 * Constante de direccion ESTE->OESTE
	 */
	private final boolean ESTE_OESTE = true;
	
	/**
	 * Constante de direccion OESTE->ESTE
	 */
	private final boolean OESTE_ESTE = false;
	
	/**
	 * Direccion de uso actual
	 */
	private boolean direccionActual = ESTE_OESTE;
	
	/**
	 * Proxima direccion a establecer cuando el canal este vacio
	 */
	private boolean proximaDireccion=ESTE_OESTE;
	
	/**
	 * Capacidad restante en el canal
	 */
	private int capacidadRestante = Config.CAPACIDAD_MAXIMA_CANAL;
	
	/**
	 * Cantidad de barcos esperando en la entrada este
	 */
	private int colaEste=0;
	
	/**
	 * Cantidad de barcos esperando en la entrada oeste
	 */
	private int colaOeste=0;
	


	
	/**
	 * <p>
	 * Entrar al canal. Registra el barco como esperando en la entrada sumando la
	 * cantidad de la entrada correspondiente
	 * </p>
	 * <p>
	 * Hace esperar si: No lleva la direccion permitida o se va a cambiar de
	 * direccion o el canal esta lleno
	 * </p>
	 * <p>
	 * Una vez consigue la entrada se resta de la espera y se resta de la
	 * capacidadHace esperar si: No lleva la direccion permitida o se va a cambiar
	 * de direccion o el canal esta lleno
	 * </p>
	 */
	public synchronized void entrar() {
		Barco b = (Barco) Thread.currentThread();
		
		//sumar cantidad a la cola correspondiente
		if (b.isDireccionEO())
			colaEste++;
		else
			colaOeste++;
		
		//hacer esperar si:
		//-No se es de la direccion actual
		//-Se esta en mitad de un cambio de direccion
		//-El canal no tiene capacidad
		while (direccionActual!=b.isDireccionEO() || direccionActual!=proximaDireccion || capacidadRestante==0)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		//simular maniobra de entrada al canal
		try {
			Thread.sleep(Config.T_MANIOBRA_ENTRADA);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//restar uno de la cola correspondiente
		if (b.isDireccionEO())
			colaEste--;
		else
			colaOeste--;
		
		//ocupar capacidad del canal
		capacidadRestante--; 
	}

	
	/**
	 * <p>
	 * Salir del canal. Si no se esta en un cambio de direccion se recalcula la
	 * direccion por si hay que cambiarla
	 * </p>
	 * <p>
	 * Deja una plaza libre en la capacidad del canal.
	 * </p>
	 * <p>
	 * Si no queda nadie mas en el canal actualiza la direccion actual con la
	 * direccion proxima
	 * </p>
	 * <p>
	 * Notifica a los hilos en espera
	 * </p>
	 */
	public synchronized void salir() {
		//recalcular direccion 
		if (direccionActual==proximaDireccion)
			recalcularDireccion();
		
		//dejar plaza libre en el canal
		capacidadRestante++;
		
		//actualizar la direccion actual cuando el canal se quede vacio
		if (capacidadRestante==Config.CAPACIDAD_MAXIMA_CANAL)
			direccionActual=proximaDireccion;
		
		
		Estadistica.setDireccion(direccionActual,proximaDireccion);
		notifyAll();
	}

	/**
	 * Recalcula la proxima direccion. Si la cantidad esperando en una entrada es 
	 * mas del doble de grande que en la otra
	 * esa entrada sera el origen de la proxima direccion 
	 */
	private void recalcularDireccion() {
 			if (colaEste > colaOeste*2)
				proximaDireccion = ESTE_OESTE;
			else if (colaOeste> colaEste*2)
				proximaDireccion = OESTE_ESTE;
 			
	}

}
