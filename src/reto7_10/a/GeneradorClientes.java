package reto7_10.a;

import java.util.Random;

public class GeneradorClientes extends Thread {
	int ultimaId=0;
	int tiempoEsperar=Config.T_RAPIDO_NUEVO_CLIENTE;
	int clientesGenerados=0;
	@Override
	public void run() {
		while(true) {
			
			esperar();
			new Cliente(++ultimaId).start();
		}
	}
	private void esperar() {
		clientesGenerados=(clientesGenerados+1)%Config.CAMBIO_VELOCIDAD_CADA;
		try {
			sleep(tiempoEsperar);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (clientesGenerados==0) {
			tiempoEsperar=(tiempoEsperar==Config.T_RAPIDO_NUEVO_CLIENTE)?Config.T_LENTO_NUEVO_CLIENTE:Config.T_RAPIDO_NUEVO_CLIENTE;
			Estadistica.setVelocidadClientes(tiempoEsperar);
		}
	}

}
