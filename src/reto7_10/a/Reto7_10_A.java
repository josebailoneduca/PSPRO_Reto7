package reto7_10.a;
public class Reto7_10_A {

	// 10 ). Una barbería tiene una sala de espera con N sillas y la butaca del barbero. Si no hay
	// clientes, el barbero duerme en la butaca. Si un cliente entra en la barbería y encuentra todas las
	// sillas ocupadas se marcha. Si el barbero duerme el cliente lo despierta y si está ocupado y hay
	// sillas disponibles el cliente se sienta. 
	
	
	public static void main(String[] args) {
	
		new Barbero().start();
		new GeneradorClientes().start();
		
		
		
		/*
		while(true) {
			Estadistica.mostrarEstadistica();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
		
	}
}
