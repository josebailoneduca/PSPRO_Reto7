package reto7_12.b;

 

public class Productor extends Thread{
	Buffer buffer;
	int ultimaId=-1;
	int tiempoEsperar=Config.T_RAPIDO_GENERAR;
	int elementossGenerados=0;
	
	public Productor(Buffer buffer) {
		this.buffer = buffer;
	}
	@Override
	public void run() {
		while(true) {
			Estadistica.setEstadoProductor(Estadistica.PREPARANDO);
			esperar();
			Estadistica.setEstadoProductor(Estadistica.ESPERANDO);
			buffer.agregar(new Elemento(++ultimaId));
		}
	}
	private void esperar() {
		elementossGenerados=(elementossGenerados+1)%Config.CAMBIO_VELOCIDAD_CADA;
		try {
			sleep(tiempoEsperar);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (elementossGenerados==0) {
			tiempoEsperar=(tiempoEsperar==Config.T_RAPIDO_GENERAR)?Config.T_LENTO_GENERAR:Config.T_RAPIDO_GENERAR;
			Estadistica.setVelocidadGenerador(tiempoEsperar);
		}
	}

}
