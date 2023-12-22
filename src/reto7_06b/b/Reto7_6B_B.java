package reto7_06b.b;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



/**
 * <p>
 * Main del Reto7_6B_B.
 * </p>
 * <p>
 * BaseDatos Controla el acceso de lectores con un monitor inspirado en las condiciones de funcionamiento del algoritmo de lector escritor de J. Bacon con 
 * prioridad para los escritores.
 * </p>
 * <p>
 * El monitor permite controlar el flujo de lectores y escritores adquiriendo permiso de lectura o escritura y liberando el permiso adquirido.
 * Funciona de la siguiente manera:
 * </p>
 * <p>
 * Los escritores son contabilizados conforme entran a pedir paso y son descontabilizados cuando terminan su accion.
 * Los lectores son contabilizados conforme adquieren el paso y son descontabilizados cuando terminan su accion.
 * </p>
 * <p>
 * Cuando entra un lector a pedir paso, si encuentra un numero de escritores contabilizados mayor que cero, entra en espera hasta que se rompa esa condicion.
 * Cuando entra un escritor a pedir paso, si encuentra la lectura o la escritura activa, espera hasta que se rompa esa condicion.
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
 * <p>
 * En cuanto hay un escritor esperando tiene prioridad sobre el resto de lectores. 
 * Este comportamiento esta implementado con semaforos. 
 * </p>
 * @author Jose Javier Bailon Ortiz
 * 
 * @see BaseDatos
 * @see MonitorLecturaEscritura
 */
public class Reto7_6B_B {
	
	
	/**
	 * Funcion Main del programa
	 * @param args
	 */
	public static void main(String[] args) {
		
 
		
		//CREACION DE BASE DE DATOS E HILOS
		
		//registro del tiempo de inicio para informe final
		long tiempoInicio = System.currentTimeMillis();
		
		//calculo del total de hebras
		int totalHebras = Config.hebrasLectura + Config.hebrasEscritura;
		
		//creacion de la base de datos
		BaseDatos bd = new BaseDatos(Config.rutaArchivo, Config.numeroTuplas);

		// crear los hilos
		ArrayList<HiloConsultaBD> hilos = new ArrayList<HiloConsultaBD>();
		//crear hebras lectores
		int i = 0;
		for (i = 0; i < Config.hebrasLectura; i++) {
			hilos.add(new HiloLector(i, bd, Config.ciclos));
		}
		
		//crear hebras escritoras
		for (i = Config.hebrasLectura; i < totalHebras; i++) {
			hilos.add(new HiloEscritor(i, bd, Config.ciclos));
		}
		
		
		//start de hilos en una secuencia aleatoria
		if (Config.startAleatorioDeHebras) {
			ArrayList<HiloConsultaBD> listaInicioHilos = new ArrayList<HiloConsultaBD>(hilos);
			while (listaInicioHilos.size() > 0)
				listaInicioHilos.remove(new Random().nextInt(listaInicioHilos.size())).start();
		//start de los hilos de manera ordenada
		} else
			for (HiloConsultaBD hilo : hilos)
				hilo.start();
		
		
		// esperar a que terminen todos los hilos
		System.out.println("Realizando consultas a la base de datos:");
		for (int j = 0; j < totalHebras; j++) {
			try {
				hilos.get(j).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Mostrar informe de resultado de los datos almacenados en la base de datos
		List<Integer> tuplas = bd.getTodasLasTuplas();
		System.out.println("************************************");
		System.out.println("RETO 7 6B B");
		System.out.println("Contenido de la base de datos. Ruta: "+bd.getRuta());
		System.out.println("************************************");
		System.out.println(tuplas);
		System.out.println("Suma total de tuplas esperada:\t" + (Config.hebrasEscritura * Config.ciclos));
		System.out.println("Suma real de tuplas en archivo:\t"+ tuplas.stream().reduce(0, (a, b) -> a + b));
		System.out.println("Total de tiempo: " + ((System.currentTimeMillis() - tiempoInicio) / 1000) + " segundos");
		System.out.println("Hebras de lectura: "+Config.hebrasLectura);
		System.out.println("Hebras de escritura: "+Config.hebrasEscritura);
		System.out.println("ConsulTas realizadas por cada hebra: "+Config.ciclos);
	}

}
