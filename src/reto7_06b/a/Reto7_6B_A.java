package reto7_06b.a;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



/**
 * <p>
 * Main del Reto7_6B_A.
 * </p>
 * <p>
 * Controla el acceso con un algoritmo de lector escritor de J. Bacon con 
 * prioridad para los escritores.
 * </p>
 * <p>
 * En cuanto hay un escritor esperando tiene prioridad sobre el resto de lectores. 
 * Este comportamiento esta implementado con semaforos. 
 * </p>
 * @author Jose Javier Bailon Ortiz
 * 
 * @see BaseDatos
 */
public class Reto7_6B_A {
	

	
	
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
		System.out.println("RETO 7 6B A");
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
