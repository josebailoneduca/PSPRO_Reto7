package reto7_09.b;

import java.util.ArrayList;
import java.util.Arrays;
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
		System.out.println("RETO 7 9 B\n"+("-".repeat(20)));
		System.out.println("Preparador panecillos: "+etiquetas[estadoPrepPanecillos]);
		System.out.println( "CestaPanecillos:");
		for (int p : Fabrica.getCestaPanecillos()) {
			if (p>0)
			System.out.print(p+",");
		}
		System.out.println();
		System.out.println("Preparador ingredientes: "+etiquetas[estadoPrepIngredientes]);
		System.out.println( "CestaIngredientes:");
		for (int i : Fabrica.getCestaIngredientes()) {
			if (i>0)
			System.out.print(i+",");
		}
		System.out.println();
		System.out.println("Preparador haburguesas: "+etiquetas[estadoPrepHamburguesas]);
		System.out.println( "Hamburguesas preparadas: "+Fabrica.getBandejaHamburguesas().stream().map(Object::toString).collect(Collectors.joining(", ")));
		}catch(Exception ex) {
			
		}

	}
}



