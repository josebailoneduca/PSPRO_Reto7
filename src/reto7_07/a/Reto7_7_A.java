package reto7_07.a;
public class Reto7_7_A {

	//A y B compiten por utilizar cierto recurso. En cualquier momento puede haber un máximo
	//de N hilos de cualquier tipo usando el recurso (N es constante). Por otro lado, para que un hilo de
	//tipo A pueda entrar a emplear el recurso, debe haber al menos el doble de tipo B dentro del recurso. 

	

	public static void main(String[] args) {
		Recurso r = new Recurso();
		Thread[] hilos = new Thread[Config.N_A+Config.N_B];
		for (int i=0;i<Config.N_A;i++) {
			hilos[i]=new A(i,r);
		}
		for (int i=0;i< Config.N_B;i++) {
			hilos[i+Config.N_A]=new B(i,r);
		}
		
		for (Thread hilo : hilos) {
			hilo.start();
		}
		
		
		
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
