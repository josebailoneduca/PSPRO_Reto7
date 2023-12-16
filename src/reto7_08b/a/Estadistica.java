package reto7_08b.a;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class Estadistica {
	public static final int ESPERANDO=0;
	public static final int COMIENDO=1;
	private static String[]etiquetas = {"Esperando","Comiendo --------"};
	
	private static int mafiosoPasta =0;
	private static int mafiosoTomate =0;
	private static int mafiosoQueso =0;
	
	private static AtomicInteger nComiendo=new AtomicInteger(0);
	
	private static LinkedBlockingDeque<Ingrediente> ing1=new LinkedBlockingDeque<Ingrediente>();
	private static  LinkedBlockingDeque<Ingrediente> ing2=new LinkedBlockingDeque<Ingrediente>();

	public static void setEstado(Ingrediente ingrediente, int estado) {
		switch (ingrediente) {
		case PASTA ->{mafiosoPasta=estado;}
		case TOMATE->{mafiosoTomate=estado;}
		case QUESO ->{mafiosoQueso=estado;}
		default->{}
		}
		if (estado==COMIENDO)
				nComiendo.incrementAndGet();		
	}
	public static void ponerIngredientes(Ingrediente ingred1, Ingrediente ingred2) {
		ing1.add(ingred1);
		ing2.add(ingred2);
	}
	
	
	public static void quitarIngredientes() {
		ing1.pop();
		ing2.pop();
		nComiendo.decrementAndGet();
	}
	
	public static void mostrarEstadistica() {
		Iterator<Ingrediente> itIng1=ing1.iterator();
		Iterator<Ingrediente> itIng2=ing2.iterator();
		
		System.out.println("\n".repeat(30));
		System.out.println("RETO 7 8B A\n"+("-".repeat(20)));
		System.out.println("Mafioso pasta: "+etiquetas[mafiosoPasta]);
		System.out.println("Mafioso tomate: "+etiquetas[mafiosoTomate]);
		System.out.println("Mafioso queso: "+etiquetas[mafiosoQueso]);
		System.out.println();
		System.out.println("Ultimos ingredientes:");
		int comiendo = nComiendo.get();
		System.out.println("Recogido\t".repeat(comiendo)+"En la mesa");
		System.out.println("-".repeat(60));
		while(itIng1.hasNext())
			System.out.print(itIng1.next()+"\t\t");
		System.out.println();
		while(itIng2.hasNext())
			System.out.print(itIng2.next()+"\t\t");
	}

}



