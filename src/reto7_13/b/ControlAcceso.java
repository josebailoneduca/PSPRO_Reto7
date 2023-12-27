/**
 * 
 */
package reto7_13.b;

/**
 * Monitor de control de acceso a las entradas y salidas del bote.
 * Tambien controla el momento en el que se realiza el viaje.
 * 
 * Tiene contadores de cuantas entradas y salidas hay permitidas.
 * Si un viajero intenta entrar y no hay entradas para los de su tipo queda en espera.
 * Cuando Bote habilita entradas o salidas aumenta los contadores y los viajeros en espera despiertan y comprueban
 * si ya pueden pasar decrementando los contadores tras pasar.
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class ControlAcceso {
	/**
	 * Numero de entradas disponibles para matematicos
	 */
	private int nEntradaMat = 0;

	/**
	 * Numero de entradas disponibles para NO matematicos
	 */
	private int nEntradaNoMat = 0;

	/**
	 * Numero de salidas disponibles para matematicos
	 */
	private int nSalidaMat = 0;

	/**
	 * Numero de salidas disponibles para NO matematicos
	 */
	private int nSalidaNoMat = 0;

	/**
	 * True si tiene que viajar False si no tiene que viajar
	 */
	private boolean darViaje = false;

	/**
	 * Habilita n entradas para matematicos.
	 * @param n Numero de entradas a habilitar
	 */
	public synchronized void darEntradaMatematico(int n) {
		nEntradaMat += n;
		notifyAll();
	}

	/**
	 * Habilita n entradas para NO matematicos.
	 * @param n Numero de entradas a habilitar
	 */
	public synchronized void darEntradaNoMatematico(int n) {
		nEntradaNoMat += n;
		notifyAll();
	}

	
	/**
	 * Coger entrada en el bote
	 * @param v El viajero a entrar
	 */
	public synchronized void entrar(Viajero v) {
		if (v.isMatematico()) {
			while (nEntradaMat < 1)
				esperar();
			nEntradaMat--;
		} else {
			while (nEntradaNoMat < 1)
				esperar();
			nEntradaNoMat--;
		}
	}

	
	/**
	 * Coger salida del bote
	 * @param v El viajero a entrar
	 */
	public synchronized void salir(Viajero v) {
		if (v.isMatematico()) {
			while (nSalidaMat < 1)
				esperar();
			nSalidaMat--;
		} else {
			while (nSalidaNoMat < 1)
				esperar();
			nSalidaNoMat--;
		}
	}
	
	
	/**
	 * Habilita n salidas de matematicos
	 * @param nMatematicos
	 */
	public synchronized void darSalidaMatematico(int n) {
		nSalidaMat += n;
		notifyAll();
	}

	/**
	 * Habilita n salidas de NO matematicos
	 * @param nMatematicos
	 */
	public synchronized void darSalidaNoMatematico(int n) {
		nSalidaNoMat += n;
		notifyAll();
	}

	
	

	/**
	 * Esperar a tener permiso para viajar
	 */
	public synchronized void viajar() {
		while (!darViaje)
			esperar();
		darViaje=false;
	}

	/**
	 * Permite viajar 
	 */
	public synchronized void permitirViaje() {
		darViaje=true;
		notifyAll();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Pone la hebra en espera
	 */
	private synchronized void esperar() {
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}





}
