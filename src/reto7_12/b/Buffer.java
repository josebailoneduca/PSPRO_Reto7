package reto7_12.b;


public class Buffer {
	int huecos = Config.TAM_BUFFER;
	int[] elementosDisponibles = new int[Config.N_CONSUMIDORES];
	Elemento[] buffer = new Elemento[Config.TAM_BUFFER];
	int posEscritura = 0;
	int[] posLecturas = new int[Config.N_CONSUMIDORES];
	
 

	public synchronized void agregar(Elemento el) {

		while (huecos==0) {
			notify();
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
 			Estadistica.agregaBuffer(el.getId());
			buffer[posEscritura] = el;
			huecos--;
			posEscritura = (posEscritura + 1) % Config.TAM_BUFFER;
 		for (int i = 0;i<elementosDisponibles.length;i++){
			elementosDisponibles[i]++;
		}
 		notify();
	}

	public synchronized Elemento consumir(int idConsumidor) {
		int posLectura = posLecturas[idConsumidor]%Config.TAM_BUFFER;
		Elemento leido = null;
		while (elementosDisponibles[idConsumidor]==0) {
			try {
				notify();
				wait();
			} catch (InterruptedException e) {
			e.printStackTrace();
			}
		}
 
		leido = leer(posLectura);
		elementosDisponibles[idConsumidor]--;
		posLecturas[idConsumidor]++;
		notify();
		return leido;
	}

	private synchronized Elemento leer(int ind) {
		Elemento elemento = buffer[ind];
		if (elemento.gastarLectura() == 0) {
			Estadistica.eliminaBuffer();
			huecos++;
		}
		return elemento;
	}
}



