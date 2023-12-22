package reto7_04.b;

import java.util.LinkedList;


/**
 * Clase que controla el acceso a la caja vacia y el almacen donde se guardan las cajas llenas.
 * Controla el acceso de los embotelladores y del empaquetador usando metodos sincronizados.
 * Tambien controla la generacion de id para las botellas.
 * 
 * @author Jose Javier Bailon Ortiz
 */
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
	 * Poner una botella en una caja vacia. Espera si no hay caja o esta llena
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

	/**
	 * Devuele la caja para llenar
	 * @return La caja
	 */
	public Caja getCajaParaLLenar() {
		return cajaParaLLenar;
	}

	/**
	 * Devuelve una nueva botella
	 * 
	 * @return La nueva botella
	 */
	synchronized public int getIdProximaBotella() {
		return idProximaBotella++;
	}

	/**
	 * Devuelve la id de la proxima caja
	 * 
	 * @return La nueva botella
	 */
	public int getIdProximaCaja() {
		return idProximaCaja++;
	}
 
}
