package reto7_4.a;

public class Estadistica {
	public static String[] estadoEmbotelladores=new String[Config.N_EMBOTELLADORES];
	public static String estadoEmpaquetador="";

	public static void setEmbotellador(int id,String estado) {
		estadoEmbotelladores[id]=estado;
 			
	}
	
	public static void setEmpaquetador(String estado) {
		estadoEmpaquetador=estado;
 	}
	
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
