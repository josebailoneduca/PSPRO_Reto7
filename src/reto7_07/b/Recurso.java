package reto7_07.b;

import java.util.concurrent.locks.ReentrantLock;

public class Recurso {
	int nA = 0;
	int nB = 0;
	int plazasDisponibles = Config.N_MAX_EN_RECURSO;
	int plazasAdisponibles = 0;
	ReentrantLock lock = new ReentrantLock();

	public  void usarA() {
		try {
			lock.lock();
			while (nB - 2 * (nA + 1) < 0 || nA + nB == Config.N_MAX_EN_RECURSO) {
				lock.unlock();
				Estadistica.nA = nA;
				Estadistica.nB = nB;
				lock.lock();
			}
			nA++;
		} finally {
			lock.unlock();
		}

	}

	public  void dejarDeUsarA() {
		try {
			lock.lock();
			nA--;
		} finally {
			lock.unlock();
		}
	}

	public  void usarB() {
		try {
		lock.lock();
		while (nA+nB==Config.N_MAX_EN_RECURSO) {
				lock.unlock();
				Estadistica.nA=nA;
				Estadistica.nB=nB;
				lock.lock();
		}
		nB++;
		}finally {lock.unlock();}
	}

	public  void dejarDeUsarB() {
		try {
			lock.lock();
			nB--;
		} finally {
			lock.unlock();
		}
	}

}
