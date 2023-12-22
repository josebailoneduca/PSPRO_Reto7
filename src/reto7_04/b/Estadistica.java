package reto7_04.b;


/**
 * Imprime en pantalla el estado del sistema
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Estadistica {


	/**
	 * Estado de los embotelladores
	 */
	private static String[] estadoEmbotelladores=new String[Config.N_EMBOTELLADORES];
	
	/**
	 * Estado del empaquetador
	 */
	private static String estadoEmpaquetador="";
	
	/**
	 * Referencia a la fabrica
	 */
	private static Fabrica fabrica;
	
	
	/**
	 * Define la fabrica
	 * @param fabrica La fabrica a definira
	 */
	public static void setFabrica(Fabrica fabrica) {
		Estadistica.fabrica = fabrica;
	}
	
	/**
	 * Define el estado de un embotellador
	 * @param id Id del embotellador
	 * @param estado El estado
	 */
	public static void setEmbotellador(int id,String estado) {
		estadoEmbotelladores[id]=estado;
 			
	}
	
	/**
	 * Define el estado del empaquetador
	 * @param estado El estado
	 */
	public static void setEmpaquetador(String estado) {
		estadoEmpaquetador=estado;
 	}
	
	/**
	 * Muestra el estado general del sistema
	 */
	public static void mostrarEstadistica() {
		
		System.out.println("\n".repeat(30));
		System.out.println("RETO 7 4 B\n"+("-".repeat(20)));
		for(int i=0;i<Config.N_EMBOTELLADORES;i++) 
			System.out.println("EMBOTELLADOR "+i+": "+estadoEmbotelladores[i]);
		System.out.println("CAJA LLENANDOSE: "+((fabrica.getCajaParaLLenar()==null)?"Sin caja":fabrica.getCajaParaLLenar().contenido()));
		System.out.println("EMPAQUETADOR: "+estadoEmpaquetador);
		System.out.println("ALMACEN: "+fabrica.almacen);
	}
}
