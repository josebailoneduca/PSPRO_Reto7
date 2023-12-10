package reto7_6a.b;

import java.util.Random;

/**
 * Consulta de escritura a la base de datos
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class HiloEscritor extends HiloConsultaBD {
	
	/**
	 * Constructor 
	 * @param indice Indice id del hilo
	 * @param bd Base de datos a la que realizar las consultas
	 * @param ciclos Cantidad de veces que debe ejecutar consulta
	 */
	public HiloEscritor(int indice, BaseDatos bd, int ciclos) {
		super(indice, bd, ciclos);
	}

	/**
	 * Implementacion de los ciclos de escritura
	 */
	@Override
	public void run() {
		for (int c = 0; c < ciclos; c++) {
			//seleccion de una tupla aleatoria
			int idTupla = new Random().nextInt(bd.getNumeroTuplas());
			//update de la tupla
			bd.update(idTupla);
			
			//mensajes para debug
			if (Config.debugUpdate) 
				System.out.println(String.format("Hilo %2d escribe en la tupla " + idTupla,this.indice));
			
			if (Config.debugProgresoUpdate)
				System.out.println("Progreso del hilo escritor id "+this.indice+": " + (c * 100 / (ciclos-1)) + "%");

			// generacion de tiempo aletorio entre consultas
			if (Config.esperaAleatoriaEnCiclosDeHebras > 0)
				try {
					Thread.sleep(new Random().nextInt(Config.esperaAleatoriaEnCiclosDeHebras));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
	}

}
