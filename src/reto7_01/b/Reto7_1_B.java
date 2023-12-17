package reto7_01.b;



/**
 * 1 En una pajarería están teniendo problemas para tener a todos sus loros felices. Los loros 
 * comparten una jaula en la que hay un plato con comida y un columpio para hacer ejercicio. Todos 
 * los loros repiten la misma rutina una y otra vez: primero comen del plato y después se columpian. 
 * Pero se encuentran con el inconveniente de que sólo tres de ellos pueden comer del plato al 
 * mismo tiempo y que en el columpio solo cabe un pájaro. 
 */
public class Reto7_1_B {

	public static void main(String[] args) {
		Thread[] loros = new Thread[Config.N_LOROS];
		
		for (int i=0;i<loros.length;i++)
			loros[i]=new Thread(new Loro(i));
		
		
		
		for (int i=0;i<loros.length;i++)
			loros[i].start();
		
		
		
	}

}
