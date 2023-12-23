package reto7_07.b;

import java.util.concurrent.locks.ReentrantLock;


/**
 * <p>
 * Recurso compartido al que acceden los hilos. 
 * Contabiliza cuantos hilos hay usandolo y en funcion de eso, cuando un hilo intenta usarlo lo mantiene en espera ocupada segun lo siguiente:
 * </p>
 * <ul>
 * <li>Si es hilo de tipo A y los hilos de tipo B no suman el menos el doble de la cantidad de hilos tipo A actuales +1 espera</li>
 * <li>Si es hilo del tipo que sea y se ha llegado al maximo de hilos permitidos dentro del recurso espera</li>
 * </ul>
 * 
 * Usa un ReentrantLock para MUTEX de contadores
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Recurso {

	/**
	 * Contador de hilos de tipo A usando el recurso
	 */
	private int nA = 0;
	
	/**
	 * Contador de hilos de tipo B usando el recurso
	 */
	private int nB = 0;
	
	/**
	 * MUTEX para modificar y comprobar contadores
	 */
	private ReentrantLock lock = new ReentrantLock();

	
	/**
	 * Inicio de uso por parte de un hilo de tipo A
	 */
	public  void usarA() {
		try {
			lock.lock();
			//espera ocupada mientras queden plazas disponibles y haya almenos el doble de B que de A
			while (nB - 2 * (nA + 1) < 0 || nA + nB == Config.N_MAX_EN_RECURSO) {
				lock.unlock();
				Estadistica.nA = nA;
				Estadistica.nB = nB;
				lock.lock();
			}
			//si se llega aqui puede usar el recurso, se contabiliza el hilo de tipo A
			nA++;
			Estadistica.nA = nA;
		} finally {
			//liberar el candado
			lock.unlock();
		}

	}

	/**
	 * Deja de usar el recurso como hilo de tipo A. Decrementa la cantidad de hilos contados de tipo A.
	 */
	public  void dejarDeUsarA() {
		try {
			lock.lock();
			nA--;
			Estadistica.nA = nA;
		} finally {
			lock.unlock();
		}
	}

	/**
	 * Inicio del uso del recuroso como hilo de tipo B
	 */
	public  void usarB() {
		try {
		lock.lock();
		//espera ocupada mientras se este en el limite de hilos que pueden estar en el recurso
		while (nA+nB==Config.N_MAX_EN_RECURSO) {
				lock.unlock();
				Estadistica.nA=nA;
				Estadistica.nB=nB;
				lock.lock();
		}
		
		//si se llega aqui puede usar el recurso, se contabiliza el hilo de tipo B
		nB++;
		Estadistica.nB=nB;
		}finally {lock.unlock();}
	}

	/**
	 * Deja de usar el recurso como hilo de tipo B. Decrementa la cantidad de hilos contados de tipo B.
	 */
	public  void dejarDeUsarB() {
		try {
			lock.lock();
			nB--;
			Estadistica.nB=nB;
		} finally {
			lock.unlock();
		}
	}

}
