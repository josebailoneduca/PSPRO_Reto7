package reto7_7.a;


public class Recurso {
	int nA = 0;
	int nB = 0;

	public synchronized void usarA() {
		while (nB - 2 * (nA + 1) <0 || nA+nB==Config.N_EN_RECURSO)
			try {
				Estadistica.nA=nA;
				Estadistica.nB=nB;
				notify();
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		nA++;
	}

	public synchronized void dejarDeUsarA() {
		nA--;
		notify();
	}

	public synchronized void usarB() {
		while (nA+nB==Config.N_EN_RECURSO)
			try {
				Estadistica.nA=nA;
				Estadistica.nB=nB;
				notify();
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		nB++;
	}

	public synchronized void dejarDeUsarB() {
		nB--;
		notify();
	}

}
