package reto7_06b.b;

/**
 * <p>
 * Monitor de control de lectura/escritura con prioridad de escritura
 * 
 * </p>
 * <p>
 * Controla el acceso con metodos sincroinizados. Los lectores y escritores piden paso para hacer su operacion y sera dado en funcion de lo siguiente:
 * Los escritores son contabilizados conforme entran a pedir paso y son descontabilizados cuando terminan su accion.
 * Los lectores son contabilizados conforme adquieren el paso y son descontabilizados cuando terminan su accion.
 * </p>
 * <p>
 * Cuando entra un lector a pedir paso si encuentra un numero de escritores contabilizados mayor que cero entra en espera hasta que se rompa esa condicion.
 * Cuando entra un escritor a pedir paso si encuentra la lectura o la escritura activa espera hasta que se rompa esa condicion.
 * </p>
 * <p>
 * Cuando un escritor ha obtenido el paso marca la escritura como activa.
 * Cuando un lector ha obtenido el paso marca la lectura como activa y es contabilizado.
 * </p>
 * <p>
 * Cuando un escritor termina su accion desactiva el estado de escritura y notifica a los hilos en espera.
 * Cuando un lector termina su accion, si es el ultimo lector contabilizado desactiva el estado de escritura. Siempre notifica a los hilos en espera.
 * </p>
 * <p>
 * El resultado es que que se puede producir varias lecturas o una escritura de manera simultanea y en cuanto hay un escritor esperando 
 * tiene prioridad sobre el resto de lectores y pasa en cuanto no haya nadie en el proceso de lectura ni escritura
 * </p>
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class MonitorLecturaEscritura {

	/**
	 * Numero de escritores en cola
	 */
	private int ne;
	/**
	 * Numero de lectores leyendo
	 */
	private int nll; 
	/**
	 * Escritura produciendose
	 */
	private boolean escrituraActiva; 
	/**
	 * Lectura produciendose
	 */
	private boolean lecturaActiva;
	
	
	/**
	 * Constructor
	 */
	public MonitorLecturaEscritura() {
		ne=0;
		nll=0;
		escrituraActiva=false;
		lecturaActiva=false;
	} 

	/**
	 * Comprobar si hay escritores registrados. Si los hay espera.
	 * En cuanto no haya escritores registrados se pasa. Se contabiliza
	 * el lector y se marca la lectura como activa.
	 *  
	 */
	public synchronized void adquirir_lector() {
		while (ne>0)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		nll++;
		lecturaActiva=true;
	}

	
	/**
	 * Descuenta el lector y si es el ultimo de los que hay leyendo desactiva
	 * el estado de lectura activa
	 */
	public synchronized void liberar_lector() {
		if (--nll==0)
			lecturaActiva=false;
		notifyAll();
	}
	
	/**
	 * Contabiliza un nuevo escritor y lo hace esperar
	 * hasta que no haya ni escritura ni lectura activa.
	 * En cuanto obtiene el paso marca la escritura como activa
	 */
	public synchronized void adquirir_escritor()  {
		ne++;
		while (escrituraActiva || lecturaActiva)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		escrituraActiva=true;
	}
	
	
	/**
	 * Decuenta el escritor y marca la escritura como no activa
	 */
	public synchronized void liberar_escritor() {
		ne--;
		escrituraActiva=false;
		notifyAll();
	}
	
}
