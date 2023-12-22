package reto7_02b.a;


/**
 * Se encarga de mostrar el estado del sistema
 */
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
	private static void mostrarEstadistica() {
		
		System.out.println("\n".repeat(30));
		System.out.println("\n\n\n\n\n\nRETO 7 2B A\n######################################################################");
		System.out.println("EMBOTELLADOR: "+estadoEmbotellador);
		System.out.println("CAJA LLENANDOSE: "+((Fabrica.getCajaParaLLenar()==null)?"Sin caja":Fabrica.getCajaParaLLenar().contenido()));
		System.out.println("EMPAQUETADOR: "+estadoEmpaquetador);
		System.out.println("ALMACEN: "+Fabrica.almacen);
	}
}
