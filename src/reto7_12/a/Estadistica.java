package reto7_12.a;

import java.util.LinkedList;
import java.util.stream.Collectors;


/**
 * Muestra en pantalla el estado del sistema
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Estadistica {
	
	/**
	 * Estado de preparando
	 */
	public static final int PREPARANDO=0;
	
	/**
	 * Estado de leyendo
	 */
	public static final int LEYENDO=1;
	
	/**
	 * Estado de esperando
	 */
	public static final int ESPERANDO=2;
	
	/**
	 * Etiquetas de los estados
	 */
	private static final String[]etiquetas = {"Preparando","Leyendo","Esperando"};

	/**
	 * Velocidad actual de generacion de elementos
	 */
	private static int velocidadGeneracionElementos=Config.T_RAPIDO_GENERAR;
	
	/**
	 * Contenido actual del buffer
	 */
	private static LinkedList<Integer> contenidoBuffer=new LinkedList<>();
	
	/**
	 * Lecturas realiadas por cada consumidor
	 */
	private static LinkedList<Integer>[] lecturas =new LinkedList[Config.N_CONSUMIDORES];
	
	/**
	 * Velocidad de lectura de los consumidores
	 */
	private static int[] velocidadLecturaElementos=new int[Config.N_CONSUMIDORES];
	
	/**
	 * Estado del productor
	 */
	private static int estadoProductor=0;
	
	/**
	 * Estado de los consumidores
	 */
	private static int[] estadoConsumidores =new int[Config.N_CONSUMIDORES];
	
	

	
	/**
	 * Inicializa la estadistica creando tantas listas de lectura como consumidores
	 */
	public static void inicializar() {
		for (int i =0;i<Config.N_CONSUMIDORES;i++) {
			lecturas[i]=new LinkedList<Integer>();
		}
		
	}
	
	/**
	 * Registra el tiempo de lectura para un consumidor
	 * @param id La id del consumidor
	 * @param tiempo El tiempo que emplea en leer
	 */
	public static void registrarTiempoLetura(int id, int tiempo) {
		velocidadLecturaElementos[id]=tiempo;
	}
	
	/**
	 * agrega un elemento al buffer de bisualizacion
	 * 
	 * @param elemento El elemento a agregar
	 */
	public static synchronized void agregaBuffer(int elemento) {
		contenidoBuffer.add(elemento);
		mostrarEstadistica();
	}
	
	/**
	 * elmina un elemento del buffer de visualizacion
	 */
	public static synchronized void eliminaBuffer() {
		contenidoBuffer.remove();
		mostrarEstadistica();
	}

	
	/**
	 * Define el estado del productor
	 * 
	 * @param estado El estado
	 */
	public static synchronized void setEstadoProductor(int estado) {
		estadoProductor=estado;
		mostrarEstadistica();
	}
	
	/**
	 * Define el estado de un consumidor
	 * 
	 * @param id La id del consumidor
	 * @param estado El estado
	 */
	public static synchronized void setEstadoConsumidor(int id, int estado) {
		estadoConsumidores[id]=estado;
		mostrarEstadistica();
	}
	
	/**
	 * Agrega una lectura al listado de lecturas de un consumidor limitando
	 * el total a 5
	 * 
	 * @param id Id del consumidor
	 * @param lectura Lectura a agregar
	 */
	public static synchronized void addLectura(int id, int lectura) {
		lecturas[id].add(lectura);
		if (lecturas[id].size()>5)
			lecturas[id].remove();
		mostrarEstadistica();
	}
	
	/**
	 * Recoger la velocidad actual del generador de elementos
	 * 
	 * @param tiempo El tiempo empleado entre generaciones
	 */
	public synchronized static void setVelocidadGenerador(int tiempo) {
		velocidadGeneracionElementos=tiempo;
		mostrarEstadistica();
	}
	
	
	/**
	 * Muestra los datos por pantalla
	 */
	private static void mostrarEstadistica() {
		System.out.println("\n".repeat(30));
		System.out.println("RETO 7 12 A\n"+("-".repeat(20)));
		System.out.println("Elemento producido cada cada "+velocidadGeneracionElementos+" ms  |  Cambio de velocidad cada  "+Config.CAMBIO_VELOCIDAD_CADA+" elementos");
		System.out.println( "Buffer("+contenidoBuffer.size()+"/"+Config.TAM_BUFFER+"): "+contenidoBuffer.stream().map(Object::toString).collect(Collectors.joining(", ")));
		System.out.println("Productor: "+etiquetas[estadoProductor]);
		System.out.println();
		for (int i=0;i<lecturas.length;i++) {
			System.out.println("Consumidor "+i+"(Vel. "+velocidadLecturaElementos[i]+"ms): "+etiquetas[estadoConsumidores[i]]);
			LinkedList<Integer> lectura=lecturas[i];
			System.out.println( "Ultimas lecturas :"+lectura.stream().map(Object::toString).collect(Collectors.joining(", ")));
		}
		
	}

}



