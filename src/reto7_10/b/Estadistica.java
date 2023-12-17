package reto7_10.b;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class Estadistica {
	public static final int DURMIENDO=0;
	public static final int TRABAJANDO=1;
	
	private static int velocidadGeneracionClientes=Config.T_RAPIDO_NUEVO_CLIENTE;
	private static LinkedList<Cliente> esperando = new LinkedList<>();
	private static Cliente afeitandose;
	private static int estadoBarbero=0;
	
	private static String[] etiquetas = {"Durmiendo","Trabajando"};
	public static synchronized void esperar() {
			esperando.add((Cliente) Thread.currentThread());
			mostrarEstadistica();
	}
	public static synchronized void dejarDeEsperar() {
		esperando.remove((Cliente) Thread.currentThread());
		afeitandose=(Cliente) Thread.currentThread();
		mostrarEstadistica();
	}
	
	public static synchronized void desistir() {
		System.out.println("El cliente "+Thread.currentThread()+" se va porque no hay sitio");
	}
	public static synchronized void setEstadoBarbero(int e) {
		if (e==DURMIENDO)
			afeitandose=null;
		estadoBarbero=e;
		mostrarEstadistica();
	}
	public synchronized static void setVelocidadClientes(int tiempo) {
		velocidadGeneracionClientes=tiempo;
		mostrarEstadistica();
	}

	
	private static void mostrarEstadistica() {
		System.out.println("\n".repeat(30));
		System.out.println("RETO 7 10 B\n"+("-".repeat(20)));
		System.out.println("Llega un nuevo cliente cada "+velocidadGeneracionClientes+" ms");
		System.out.println("Sala de espera(max:"+Config.N_PLAZAS+"): "+esperando.stream().map(Object::toString).collect(Collectors.joining(", ")));
		System.out.println("Barbero: "+etiquetas[estadoBarbero]);
		System.out.println( "Afetiandose: "+((afeitandose!=null)?afeitandose:"nadie"));
	 

	}

}


