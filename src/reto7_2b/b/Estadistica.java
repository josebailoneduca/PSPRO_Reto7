package reto7_2b.b;

public class Estadistica {
	public static String estadoEmbotellador="";
	public static String estadoEmpaquetador="";
	private static Fabrica fabrica;
	
	
	
	public static void setFabrica(Fabrica fabrica) {
		Estadistica.fabrica = fabrica;
	}

	public static void setEmbotellador(String estado) {
		estadoEmbotellador=estado;
		mostrarEstadistica();
			
	}
	
	public static void setEmpaquetador(String estado) {
		estadoEmpaquetador=estado;
		mostrarEstadistica();
	}
	
	public static void mostrarEstadistica() {
		
		System.out.println("\n".repeat(30));
		
		System.out.println("EMBOTELLADOR: "+estadoEmbotellador);
		System.out.println("CAJA LLENANDOSE: "+((fabrica.getCajaParaLLenar()==null)?"Sin caja":fabrica.getCajaParaLLenar().contenido()));
		System.out.println("EMPAQUETADOR: "+estadoEmpaquetador);
		System.out.println("ALMACEN: "+fabrica.almacen);
	}
}
