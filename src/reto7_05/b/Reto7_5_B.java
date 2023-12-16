package reto7_05.b;
public class Reto7_5_B {

//	Tres hebras
//	h1:					h2:					h3;
//	while(true){   		while(true){		while(true){
//		a;					c;					e;
//		b;					d;					f;
//	}					}					}
//
//	Por cada ejecución de «a» o de «e» se debe permitir ejecutar una iteración de «d». Es decir, si
//	hasta ahora hemos ejecutado dos veces «a» y cinco veces «e», entonces podremos ejecutar hasta
//	siete veces la instrucción «d». 
	

	public static void main(String[] args) {
		H1 h1=new H1();
		H2 h2=new H2();
		H3 h3=new H3();
		
		
		h1.start();
		h2.start();
		h3.start();
		
		
		
		while(true) {
			Estadistica.mostrarEstadistica();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}
