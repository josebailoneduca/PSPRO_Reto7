package reto7_4.b;


public class Estadistica {



	public static String[] estadoEmbotelladores=new String[Config.N_EMBOTELLADORES];
	public static String estadoEmpaquetador="";
	private static Fabrica fabrica;
	
	
	
	public static void setFabrica(Fabrica fabrica) {
		Estadistica.fabrica = fabrica;
	}
	public static void setEmbotellador(int id,String estado) {
		estadoEmbotelladores[id]=estado;
 			
	}
	
	public static void setEmpaquetador(String estado) {
		estadoEmpaquetador=estado;
 	}
	
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
