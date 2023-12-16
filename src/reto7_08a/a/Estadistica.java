package reto7_08a.a;

import java.util.Iterator;

public class Estadistica {
	public static final int ESPERANDO=0;
	public static final int COMIENDO=1;
	private static String[]etiquetas = {"Esperando","Comiendo --------"};
	
	private static int mafiosoPasta =0;
	private static int mafiosoTomate =0;
	private static int mafiosoQueso =0;
	private static Ingrediente ing1;
	private static Ingrediente ing2;

	public static void setEstado(Ingrediente ingrediente, int estado) {
		switch (ingrediente) {
		case PASTA ->{mafiosoPasta=estado;}
		case TOMATE->{mafiosoTomate=estado;}
		case QUESO ->{mafiosoQueso=estado;}
		default->{}
		}
	}
	public static void ponerIngredientes(Ingrediente ingred1, Ingrediente ingred2) {
		ing1=ingred1;
		ing2=ingred2;
	}
	
	
	public static void mostrarEstadistica() {
		System.out.println("\n".repeat(30));
		System.out.println("RETO 7 8A A\n"+("-".repeat(20)));
		System.out.println("Mafioso pasta: "+etiquetas[mafiosoPasta]);
		System.out.println("Mafioso tomate: "+etiquetas[mafiosoTomate]);
		System.out.println("Mafioso queso: "+etiquetas[mafiosoQueso]);
		System.out.println("Ultimos ingredientes puestos en la mesa:" + ing1+" , "+ ing2 );
	}
}



