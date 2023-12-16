package reto7_02b.b;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Fabrica {
	private Caja cajaParaLLenar = new Caja(0);
	public LinkedList<Caja> almacen = new LinkedList<Caja>();
	public int idProximaBotella=1;
	public int idProximaCaja=1;
 

	/**
	 * Poner una caja vacia para ser llenada
	 * 
	 * @param caja La caja a poner
	 */
	public synchronized void ponerCajaVacia(Caja caja) {
		try {
			// esperar a que falta caja
			while (cajaParaLLenar!=null)
				this.wait();
			// poner la caja
			cajaParaLLenar = caja;
			// avisar a quienes esten esperando
			this.notify();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Retirar una caja llena
	 * 
	 * @return la caja retirada
	 */
	public synchronized Caja retirarCajaLlena() {
		try {
			// esperar a que la caja este llena
			while (cajaParaLLenar.getCapacidadDisponible()!=0)
				this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//quitar la caja y devolverla
		Caja aux = cajaParaLLenar;
		cajaParaLLenar = null;
		//aviso de que continue el resto
		this.notify();
		return aux;
	}

	/**
	 * Poner una botella en una caja vacia
	 * 
	 * @param botella La botella a poner
	 */
	public synchronized void ponerBotellaEnCaja(int botella) {
		try {
			//esperar a caja con plazas
			while (cajaParaLLenar==null||cajaParaLLenar.getCapacidadDisponible()==0)
				this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//poner botella y comprobar si quedan huecos.  
		cajaParaLLenar.ponerBotella(botella);
		this.notify();
	}

	public Caja getCajaParaLLenar() {
		return cajaParaLLenar;
	}
 
}
