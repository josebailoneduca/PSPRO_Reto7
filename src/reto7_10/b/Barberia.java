package reto7_10.b;

import java.util.LinkedList;

public class Barberia {
	private int sillasLibres= Config.N_PLAZAS;
	private boolean barberoLibre= false;
	private int clientes=0;
	private LinkedList<Cliente> cola=new LinkedList<Cliente>();
	
	public synchronized boolean cogerPlaza() {
		if (sillasLibres<1)
			return false;
		sillasLibres--;
		cola.add((Cliente)Thread.currentThread());
		clientes++;
		notifyAll();
		return true;
	}
	public synchronized void liberarPlaza() {
		sillasLibres++;
		barberoLibre=false;
		clientes--;
		
	}

	public synchronized void esperarBarbero() {
		while (!barberoLibre || cola.contains(Thread.currentThread()))
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		barberoLibre=false;
	}
	public synchronized void esperarCliente() {
		while(clientes==0)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		cola.pop();
		barberoLibre=true;
		notifyAll();
	}


	
}
