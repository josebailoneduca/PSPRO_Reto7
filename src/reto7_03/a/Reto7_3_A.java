package reto7_03.a;

import reto7_03.a.Config;
import reto7_03.a.Loro;

/**
 * 1 En una pajarería están teniendo problemas para tener a todos sus loros felices. Los loros 
 * comparten una jaula en la que hay un plato con comida y un columpio para hacer ejercicio. Todos 
 * los loros repiten la misma rutina una y otra vez: primero comen del plato y después se columpian. 
 * Pero se encuentran con el inconveniente de que sólo tres de ellos pueden comer del plato al 
 * mismo tiempo y que en el columpio solo dos pájaros teniendo que ser cada uno de un sexo. 
 */
public class Reto7_3_A {

	public static void main(String[] args) {
		Thread[] loros = new Thread[Config.N_LOROS];
		
		for (int i=0;i<loros.length;i++)
			loros[i]=new Thread(new Loro(i));
		
		
		
		for (int i=0;i<loros.length;i++)
			loros[i].start();
		
		
		
	}

}
