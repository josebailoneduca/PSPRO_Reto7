package reto7_6A.b;

import java.util.Random;
/**
 * Consulta de lectura de la base de datos
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class HiloLector extends HiloConsultaBD {
	
	/**
	 * Constructor 
	 * @param indice Indice id del hilo
	 * @param bd Base de datos a la que realizar las consultas
	 * @param ciclos Cantidad de veces que debe ejecutar consulta
	 */
	public HiloLector(int indice, BaseDatos bd, int ciclos) {
		super(indice, bd, ciclos);
	}

	/**
	 * Implementacion de los ciclos de lectura
	 */
	@Override
	public void run() {
		for (int c = 0; c < ciclos; c++) {
			//seleccion de una tupla aleatoria
			int idTupla = new Random().nextInt(bd.getNumeroTuplas());
			//lectura de la base de datos
			int valorLeido=bd.select(idTupla);
			
			//mensajes para debug
			if (Config.debugSelect) 
				System.out.println(String.format("Hilo %2d lee tupla %2d y obtiene valor: " + valorLeido,this.indice,idTupla));
			
			if (Config.debugProgresoSelect)
				System.out.println("Progreso del hilo lector id "+this.indice+": "  + (c * 100 / (ciclos-1)) + "%");
			
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
