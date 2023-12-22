package reto7_02b.b;

public class Estadistica {
	/**
	 * Estado del embotellador
	 */
	private static String estadoEmbotellador="";
	
	/**
	 * Estado del empaquetador
	 */
	private static String estadoEmpaquetador="";
	
	/**
	 * Referencia a la fabrica
	 */
	private static Fabrica fabrica;
	
	
	/**
	 * Define la referencia a la fabrica
	 * @param fabrica la Fabrica
	 */
	public static void setFabrica(Fabrica fabrica) {
		Estadistica.fabrica = fabrica;
	}


	/**
	 * Define el estado del embotellador
	 * @param estado El estado
	 */
	public static void setEmbotellador(String estado) {
		estadoEmbotellador=estado;
		mostrarEstadistica();
			
	}
	
	/**
	 * Define el estado del empaquetador
	 * @param estado El estado
	 */
	public static void setEmpaquetador(String estado) {
		estadoEmpaquetador=estado;
		mostrarEstadistica();
	}
	
	
	/**
	 * Muestra el estado general del sistema
	 */
	public static void mostrarEstadistica() {
		
		System.out.println("\n".repeat(30));
		System.out.println("\n\n\n\n\n\nRETO 7 2B B\n######################################################################");
		System.out.println("EMBOTELLADOR: "+estadoEmbotellador);
		System.out.println("CAJA LLENANDOSE: "+((fabrica.getCajaParaLLenar()==null)?"Sin caja":fabrica.getCajaParaLLenar().contenido()));
		System.out.println("EMPAQUETADOR: "+estadoEmpaquetador);
		System.out.println("ALMACEN: "+fabrica.almacen);
	}
}
