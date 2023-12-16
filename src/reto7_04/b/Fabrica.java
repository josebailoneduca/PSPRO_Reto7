package reto7_04.b;

import java.util.LinkedList;

public class Fabrica {
	private Caja cajaParaLLenar = new Caja(0);
	public LinkedList<Caja> almacen = new LinkedList<Caja>();
	private int idProximaBotella=1;
	private int idProximaCaja=1;
 

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
			this.notifyAll();
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
		this.notifyAll();
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
		this.notifyAll();
	}

	public Caja getCajaParaLLenar() {
		return cajaParaLLenar;
	}

	synchronized public int getIdProximaBotella() {
		
		return idProximaBotella++;
	}

	public int getIdProximaCaja() {
		return idProximaCaja++;
	}
 
}
