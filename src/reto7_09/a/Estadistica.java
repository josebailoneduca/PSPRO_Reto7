package reto7_09.a;

import java.util.Iterator;
import java.util.stream.Collectors;

public class Estadistica {
	public static final int PREP_PANECILLOS=0;
	public static final int PREP_INGREDIENTES=1;
	public static final int PREP_HAMBURGUESAS=2;
	public static final int PREPARANDO=0;
	public static final int ESPERANDO=1;
	private static String[]etiquetas = {"Preparando","Esperando"};
	
	private static int estadoPrepPanecillos=0;
	private static int estadoPrepIngredientes =0;
	private static int estadoPrepHamburguesas =0;
 

	public static void setEstado(int tipo, int estado) {
		switch (tipo) {
		case PREP_PANECILLOS->{estadoPrepPanecillos=estado;}
		case PREP_INGREDIENTES->{estadoPrepIngredientes=estado;}
		case PREP_HAMBURGUESAS->{estadoPrepHamburguesas=estado;}
		default->{}
		}
	}
 
	
	public static void mostrarEstadistica() {
		try {
		System.out.println("\n".repeat(30));
		System.out.println("RETO 7 9 A\n"+("-".repeat(20)));
		System.out.println("Preparador panecillos: "+etiquetas[estadoPrepPanecillos]);
		System.out.println( "CestaPanecillos:"+Fabrica.getCestaPanecillos().stream().map(Object::toString).collect(Collectors.joining(", ")));
		System.out.println("Preparador ingredientes: "+etiquetas[estadoPrepIngredientes]);
		System.out.println( "CestaIngredientes:"+Fabrica.getCestaIngredientes().stream().map(Object::toString).collect(Collectors.joining(", ")));
		System.out.println("Preparador haburguesas: "+etiquetas[estadoPrepHamburguesas]);
		System.out.println( "Hamburguesas preparadas: "+Fabrica.getBandejaHamburguesas().stream().map(Object::toString).collect(Collectors.joining(", ")));
		}catch(Exception ex) {
			
		}

	}
}



