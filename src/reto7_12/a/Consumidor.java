package reto7_12.a;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Consumidor extends Thread {
	Buffer buffer;
	int id;
	int velocidadLectura;

	public Consumidor(int id, Buffer buffer) {
		this.id = id;
		this.buffer = buffer;
		velocidadLectura = new Random().nextInt(Config.T_RAPIDO_LEER, Config.T_LENTO_LEER);
		Estadistica.registrarTiempoLetura(id, velocidadLectura);
	}

	@Override
	public void run() {
		while (true) {
	 
			Estadistica.setEstadoConsumidor(id, Estadistica.ESPERANDO);
			Elemento el = buffer.consumir(id);
			Estadistica.setEstadoConsumidor(id, Estadistica.LEYENDO);
			Estadistica.addLectura(id, el.getId());
			try {
				sleep(velocidadLectura);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
