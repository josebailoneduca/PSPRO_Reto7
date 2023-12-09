package reto7_2b.a;

public class Estadistica {
	public static String estadoEmbotellador="";
	public static String estadoEmpaquetador="";

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
		System.out.println("CAJA LLENANDOSE: "+((Fabrica.getCajaParaLLenar()==null)?"Sin caja":Fabrica.getCajaParaLLenar().contenido()));
		System.out.println("EMPAQUETADOR: "+estadoEmpaquetador);
		System.out.println("ALMACEN: "+Fabrica.almacen);
	}
}
