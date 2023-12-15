package reto7_7.b;

import java.util.Iterator;

public class Estadistica {
	public static final int ESPERANDO=0;
	public static final int TRABAJANDO=1;
	
	
	private static int[] estadosA =new int[Config.N_A];
	private static int[] estadosB =new int[Config.N_B];
	private static String[] etiquetas= {"Esperando","Trabajado----------------"};
	public static int nA=0;
	public static int nB=0;
	public static void setEstadoA(int id, int e) {
		estadosA[id]=e;
	}
	public static void setEstadoB(int id, int e) {
		estadosB[id]=e;
	}
	
	public static void mostrarEstadistica() {
		System.out.println("\n".repeat(30));
		System.out.println("RETO 7 7 B\n"+("-".repeat(20)));
		System.out.println("A\n"+("-".repeat(20)));
		for(int i=0;i<estadosA.length;i++) {
			System.out.println("Hilo A("+i+"): "+etiquetas[estadosA[i]]);
		}
		System.out.println("B\n"+("-".repeat(20)));
		for(int i=0;i<estadosB.length;i++) {
			System.out.println("Hilo B("+i+"): "+etiquetas[estadosB[i]]);
		}
		
		System.out.println("\nNumero de hilos trabajando\n"+("-".repeat(20)));
		System.out.println("A: "+nA );
		System.out.println("B: "+nB );
	}
}



