package reto7_12.a;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Collectors;


public class Estadistica {
	public static final int PREPARANDO=0;
	public static final int LEYENDO=1;
	public static final int ESPERANDO=2;
	private static final String[]etiquetas = {"Preparando","Leyendo","Esperando"};

	private static int velocidadGeneracionElementos=Config.T_RAPIDO_GENERAR;
	private static LinkedList<Integer> contenidoBuffer=new LinkedList<>();
	private static LinkedList<Integer>[] lecturas =new LinkedList[Config.N_CONSUMIDORES];
	private static int[] velocidadLecturaElementos=new int[Config.N_CONSUMIDORES];
	
	
	private static int estadoProductor=0;
	private static int[] estadoConsumidores =new int[Config.N_CONSUMIDORES];
	public static String debug="";
	public static void inicializar() {
		for (int i =0;i<Config.N_CONSUMIDORES;i++) {
			lecturas[i]=new LinkedList<Integer>();
		}
		
	}
	public static void registrarTiempoLetura(int id, int tiempo) {
		velocidadLecturaElementos[id]=tiempo;
	}
	public static synchronized void agregaBuffer(int elemento) {
		contenidoBuffer.add(elemento);
		mostrarEstadistica();
	}
	public static synchronized void eliminaBuffer() {
		contenidoBuffer.remove();
		mostrarEstadistica();
	}

	public static synchronized void setEstadoProductor(int estado) {
		estadoProductor=estado;
		mostrarEstadistica();
	}
	public static synchronized void setEstadoConsumidor(int id, int estado) {
		estadoConsumidores[id]=estado;
		mostrarEstadistica();
	}
	public static synchronized void addLectura(int id, int lectura) {
		lecturas[id].add(lectura);
		if (lecturas[id].size()>5)
			lecturas[id].remove();
		mostrarEstadistica();
	}
	
	public synchronized static void setVelocidadGenerador(int tiempo) {
		velocidadGeneracionElementos=tiempo;
		mostrarEstadistica();
	}
	
	public static void mostrarEstadistica() {
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
			System.out.println(debug);
		}
		
	}

}



