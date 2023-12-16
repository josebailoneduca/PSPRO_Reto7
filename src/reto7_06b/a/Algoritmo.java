package reto7_06b.a;

import java.util.concurrent.Semaphore;

public class Algoritmo {
	//semaforos
	Semaphore sLP; //lecturas pendientes
	Semaphore sEP; //escrituras pendientes
	Semaphore sEEX;//escritura esclusiva
	Semaphore sCEX;//mutex para acceso a contadores
	
	//contadores
	int la;
	int ll;
	int ea;
	int ee;

	public Algoritmo() {
		la = 0;
		ll = 0;
		ea = 0;
		ee = 0;
		sLP = new Semaphore(0);
		sEP = new Semaphore(0);
		sEEX = new Semaphore(1);
		sCEX = new Semaphore(1);

	}

	public void adquirir_lector() throws InterruptedException {
		sCEX.acquire();
		la++;
		if (ea == 0) {
			ll++;
			sLP.release();
		}
		sCEX.release();
		sLP.acquire();
	}
	
	public void liberar_lector() throws InterruptedException {
		sCEX.acquire();
		ll--;
		la--;
		if (ll==0) 
			while(ee<ea) {
				ee++;
				sEP.release();
			}
		sCEX.release();
	}
	
	public void adquirir_escritor() throws InterruptedException {
		sCEX.acquire();
		ea++;
		if (ll==0) {
			ee++;
			sEP.release();
		}
		sCEX.release();
		sEP.acquire();
		sEEX.acquire();
	}
	
	public void liberar_escritor() throws InterruptedException {
		sEEX.release();
		sCEX.acquire();
		ee--;
		ea--;
		if (ea==0) {
			while(ll<la) {
				ll++;
				sLP.release();
			}
		}
		sCEX.release();
	}

}