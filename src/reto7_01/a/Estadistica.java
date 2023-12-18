package reto7_01.a;

import java.util.Iterator;


/**
 * Imprime estadisticas en pantalla sobre la actividad del sistema.
 * Las referencias a esta clase en el resto de clases son con motivo
 * exclusivo de monitorear el estado y no intervienen en la resolucion 
 * del problema
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Estadistica {
	
	/**
	 * Estado comiendo
	 */
	public static final int COMIENDO=0;
	
	/**
	 * Estado esperando columpio
	 */
	public static final int ESPERA_COLUMPIO=1;
	
	/**
	 * Estado columpiandose
	 */
	public static final int COLUMPIANDOSE=2;
	
	/**
	 * Estado esperando para comer
	 */
	public static final int ESPERA_COMER=3;
	
	/**
	 * etiquetas de los estados
	 */
	private static final String[] etiquetas= {"comiendo >>>>>>>>>","esp.columpio","columpiandose ---------","esp.comer"};
	
	
	
	
	
	/**
	 * estado actual de los loros
	 * //0"comiendo", 1 "esperando columpio",2 "columpiandose",3 "esperando comer"
	 */
	private static int[] estadoLoros=new int[Config.N_LOROS];
	
	/**
	 * Veces que han comido
	 */
	private static int[] vecesComidas=new int[Config.N_LOROS];
	/**
	 * Veces que se han columpiado
	 */
	private static int[] vecesCoLumpiado=new int[Config.N_LOROS];

	/**
	 * Espera total para comer
	 */
	private static long[] esperaTotalComida=new long[Config.N_LOROS];

	/**
	 * Espera total para columpiarse
	 */
	private static long[] esperaTotalColumpio=new long[Config.N_LOROS];

	
 


	/**
	 * Define el estado para un loro
	 * @param id Id del loro
	 * @param estado Estado a establecer 
	 */
	public static void setEstado (int id, int estado) {
		estadoLoros[id]=estado;
		imprimirEstadistica();
	}
	
	
	/**
	 * Registra el tiempo que ha estado un loro esperando columpio
	 * @param id Id del loro
	 * @param tiempo Tiempo a agregar
	 */
	public static void columpiarse(int id, long tiempo) {
		vecesCoLumpiado[id]++;
		esperaTotalColumpio[id]+=tiempo;
		setEstado(id, COLUMPIANDOSE);
	}
	/**
	 * Registra el tiempo que ha estado un loro esperando comida
	 * @param id Id del loro
	 * @param tiempo Tiempo a agregar
	 */
	public static void comer(int id, long tiempo) {
		vecesComidas[id]++;
		esperaTotalComida[id]+=tiempo;
		setEstado(id, COMIENDO);
		
	}


  
	
	
	/**
	 * Impime la estadistica segun los datos actuales
	 */
	synchronized private static void imprimirEstadistica() {
		System.out.println("\n\n\n\n\n\nRETO 7 1 A\n######################################################################");
		System.out.println(String.format("Loro\tM. Esp. Comida\t N comido \tM. Esp. Colump \t N colump \t Estado"));
		for (int i = 0; i < estadoLoros.length; i++) {
			int mediaHambriento=(int) ((vecesComidas[i]==0)?0:(esperaTotalComida[i]/vecesComidas[i]));
			int mediaColumpio=(int) ((vecesCoLumpiado[i]==0)?0:(esperaTotalColumpio[i]/vecesCoLumpiado[i]));
			System.out.println(
					String.format("Loro %d \t %04dms \t %03d \t\t %04d \t\t %03d \t\t %s",
							i,
							mediaHambriento,
							vecesComidas[i], 
							mediaColumpio,
							vecesCoLumpiado[i],
							etiquetas[estadoLoros[i]]
							));
		}
		
	}
}
