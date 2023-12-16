package reto7_06b.b;

public class Config {
	/**
	 * Milisegundos maximos que cada hebra espera entre cada ciclo. 
	 * Se puede dejar a 0 o aumentar si se quiere aleatoriedad en la frecuecia en la que cada hilo 
	 * intenta acceder a la base de datos
	 */
	public static final int esperaAleatoriaEnCiclosDeHebras = 0;
	
	/**
	 * Mostrar los mensajes de lectura de base de datos
	 */
	public static final boolean debugSelect=true;
	
	/**
	 * Mostrar los mensajes de escritura de base de datos
	 */
	public static final boolean debugUpdate=true;
	
	/**
	 * Mostrar mensajes de progreso de ciclos de los hilos de lectura 
	 */
	public static final boolean debugProgresoSelect=false;
	
	/**
	 * Mostrar mensajes de progreso de ciclos de los hilos de escritura 
	 */
	public static final boolean debugProgresoUpdate=false;
	
	
	
	//ruta del archivo de la base de datos
	public static final String rutaArchivo = "basedatos.dat";
	
	//catindad de hebras que leeran
	public static final int hebrasLectura = 30;
	
	//cantidad de hebras que escribiran
	public static final int hebrasEscritura = 30;
	
	//cantidad de lecturas o escrituras por hebra 
	public static final int ciclos = 50;
	
	//cantidad de tuplas existentes en la base de datos
	public static final int numeroTuplas = 20;
	
	//iniciar las hebras en un orden aleatorio
	public static final boolean startAleatorioDeHebras = true;
}
