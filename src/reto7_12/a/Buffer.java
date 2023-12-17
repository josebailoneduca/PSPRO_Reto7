package reto7_12.a;

import java.util.concurrent.Semaphore;

public class Buffer {
	Semaphore sHuecos = new Semaphore(Config.TAM_BUFFER);
	Semaphore sExclusion= new Semaphore(1);
	Semaphore[] sElementosDisponibles = new Semaphore[Config.N_CONSUMIDORES];
	Elemento[] buffer = new Elemento[Config.TAM_BUFFER];
	int posEscritura = 0;
	int[] posLecturas = new int[Config.N_CONSUMIDORES];
	
	public Buffer() {
		for(int i=0;i<Config.N_CONSUMIDORES;i++) {
		this.sElementosDisponibles[i]=new Semaphore(0);
		}
	}

	public void agregar(Elemento el) {

		try {
			sHuecos.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
 			Estadistica.agregaBuffer(el.getId());
			buffer[posEscritura] = el;
			posEscritura = (posEscritura + 1) % Config.TAM_BUFFER;
 		for (Semaphore s : sElementosDisponibles) {
			s.release();
		}
	}

	public Elemento consumir(int idConsumidor) {
		int posLectura = posLecturas[idConsumidor]%Config.TAM_BUFFER;
		Elemento leido = null;
		try {
			sElementosDisponibles[idConsumidor].acquire();
			sExclusion.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		leido = leer(posLectura);

		sExclusion.release();
		posLecturas[idConsumidor]++;
		return leido;
	}

	private Elemento leer(int ind) {
		Elemento elemento = buffer[ind];
		if (elemento.gastarLectura() == 0) {
			Estadistica.eliminaBuffer();
			sHuecos.release();
		}
		return elemento;
	}
}



