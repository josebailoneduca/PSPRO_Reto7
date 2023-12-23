package reto7_07.b;


/**
 * Muestra el estado general del sistema
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Estadistica {
	/**
	 * Estado de esperando a poder acceder al recurso
	 */
	public static final int ESPERANDO=0;
	
	/**
	 * Estado de trabajando dentro del recurso
	 */
	public static final int TRABAJANDO=1;
	
	/**
	 * Estado de descansando
	 */
	public static final int DESCANSANDO=2;
	
	
	/**
	 * Numero de hilos tipo A en el recurso
	 */
	public static int nA=0;
	
	/**
	 * Numero de hilos de tipo B en el recurso
	 */
	public static int nB=0;
	
	/**
	 * Estados de los hilos de tipo A
	 */
	private static int[] estadosA =new int[Config.N_A];

	/**
	 * Estados de los hilos de tipo B
	 */
	private static int[] estadosB =new int[Config.N_B];

	/**
	 * Etiquetas de los estados
	 */
	private static String[] etiquetas= {"Esperando","Trabajado----------------","Descansando"};

	/**
	 * Define el estado de un hilo de tipo A
	 * @param id Id del hilo
	 * @param e Estado
	 */
	public static void setEstadoA(int id, int e) {
		estadosA[id]=e;
	}
	
	/**
	 * Define el estado de un hilo de tipo B
	 * @param id Id del hilo
	 * @param e Estado
	 */
	public static void setEstadoB(int id, int e) {
		estadosB[id]=e;
	}
	
	
	/**
	 * Muestra los datos del estado de los hilos
	 */
	public static void mostrarEstadistica() {
		System.out.println("\n".repeat(30));
		System.out.println("RETO 7 7 B\n"+("-".repeat(20)));
		System.out.println("A\n"+("-".repeat(20)));
		for(int i=0;i<estadosA.length;i++) {
			System.out.println("Hilo A("+i+"): "+etiquetas[estadosA[i]]);
		}
		System.out.println("B\n"+("-".repeat(20)));
		for(int i=0;i<estadosB.length;i++) {
			System.out.println("Hilo B("+i+"): "+etiquetas[estadosB[i]]);
		}
		
		System.out.println("\nNumero de hilos trabajando\n"+("-".repeat(20)));
		System.out.println("A: "+nA );
		System.out.println("B: "+nB );
		System.out.println("Total: "+(nB+nA) );
	}
}



