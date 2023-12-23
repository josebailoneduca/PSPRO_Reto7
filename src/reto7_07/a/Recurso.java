package reto7_07.a;


/**
 * <p>
 * Recurso compartido al que acceden los hilos. 
 * Contabiliza cuantos hilos hay usandolo y en funcion de eso, cuando un hilo intenta usarlo lo hace esperar segun lo siguiente:
 * </p>
 * <ul>
 * <li>Si es hilo de tipo A y los hilos de tipo B no suman el menos el doble de la cantidad de hilos tipo A actuales +1 espera</li>
 * <li>Si es hilo del tipo que sea y se ha llegado al maximo de hilos permitidos dentro del recurso espera</li>
 * </ul>
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Recurso {
	
	/**
	 * Cantidad de hilos tipo A en el recurso
	 */
	private int nA = 0;
	
	/**
	 * Cantidad de hilos de tipo B en el recurso
	 */
	private int nB = 0;

	/**
	 * Usar el recurso como hilo de tipo A
	 */
	public synchronized void usarA() {
		while (nB - 2 * (nA + 1) <0 || nA+nB==Config.N_MAX_EN_RECURSO)
			try {
				Estadistica.nA=nA;
				Estadistica.nB=nB;
				notify();
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		nA++;
		Estadistica.nA=nA;
	}

	/**
	 * Dejar de usar el recurso como hilo de tipo A
	 */
	public synchronized void dejarDeUsarA() {
		nA--;
		Estadistica.nA=nA;
		notify();
	}

	
	/**
	 * Usar el recurso como hilo e tipo B
	 */
	public synchronized void usarB() {
		while (nA+nB==Config.N_MAX_EN_RECURSO)
			try {
				Estadistica.nA=nA;
				Estadistica.nB=nB;
				notify();
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		nB++;
		Estadistica.nB=nB;
	}

	
	/**
	 * Dejar de usar el recurso como hilo de tipo B
	 */
	public synchronized void dejarDeUsarB() {
		nB--;
		Estadistica.nB=nB;
		notify();
	}

}
