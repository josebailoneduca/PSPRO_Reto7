package reto7_04.a;


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
		System.out.println("RETO 7 4 A\n"+("-".repeat(20)));
		for(int i=0;i<Config.N_EMBOTELLADORES;i++) 
			System.out.println("EMBOTELLADOR "+i+": "+estadoEmbotelladores[i]);
		System.out.println("CAJA LLENANDOSE: "+((Fabrica.getCajaParaLLenar()==null)?"Sin caja":Fabrica.getCajaParaLLenar().contenido()));
		System.out.println("EMPAQUETADOR: "+estadoEmpaquetador);
		System.out.println("ALMACEN: "+Fabrica.almacen);
	}
}
