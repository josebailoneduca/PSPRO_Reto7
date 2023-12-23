package reto7_08b.b;


import java.util.Iterator;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Muestra el estado general del sistema
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Estadistica {
	/**
	 * Estado esperando
	 */
	public static final int ESPERANDO=0;
	
	/**
	 * Estado comiendo
	 */
	public static final int COMIENDO=1;


	/**
	 * Etiquetas de los estados
	 */
	private static String[]etiquetas = {"Esperando","Comiendo --------"};
	
	/**
	 * Estado del mafioso pasta
	 */
	private static int mafiosoPasta =0;
	
	/**
	 * Estado del mafioso tomate
	 */
	private static int mafiosoTomate =0;
	
	/**
	 * Estado del mafioso queso
	 */
	private static int mafiosoQueso =0;
	
	/**
	 * Cantidad de mafiosos comiendo
	 */
	private static AtomicInteger nComiendo=new AtomicInteger(0);
	
	/**
	 * Historial de ingredientes 1 puestos en la mesa
	 */
	private static LinkedBlockingDeque<Ingrediente> ing1=new LinkedBlockingDeque<Ingrediente>();

	/**
	 * Historial de ingredientes 2 puestos en la mesa
	 */	
	private static  LinkedBlockingDeque<Ingrediente> ing2=new LinkedBlockingDeque<Ingrediente>();

	
	/**
	 * Define el estado de un mafioso
	 * @param ingrediente Ingrediente que tiene
	 * @param estado Estado del mafiosos
	 */
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
	
	/**
	 * Actualiza los ingredientes sobre la mesa
	 * @param ingred1 Ingrediente 1 de la mesa
	 * @param ingred2 Ingrediente 2 de la mesa
	 */
	public static void ponerIngredientes(Ingrediente ingred1, Ingrediente ingred2) {
		ing1.add(ingred1);
		ing2.add(ingred2);
	}
	
	
	/**
	 * Eliminar un ingrediente del historial
	 */
	public static void quitarIngredientes() {
		ing1.pop();
		ing2.pop();
		nComiendo.decrementAndGet();
	}
	
	
	/**
	 * Muestra los datos del sistema 
	 */
	public static void mostrarEstadistica() {
		Iterator<Ingrediente> itIng1=ing1.iterator();
		Iterator<Ingrediente> itIng2=ing2.iterator();
		
		System.out.println("\n".repeat(30));
		System.out.println("RETO 7 8B B\n"+("-".repeat(20)));
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



