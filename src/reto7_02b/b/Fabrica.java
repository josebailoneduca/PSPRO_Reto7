package reto7_02b.b;

import java.util.LinkedList;



/**
 * Clase que controla el acceso a la caja vacia y el almacen donde se guardan las cajas llenas.
 * Controla el acceso de embotellador y empaquetador metodos sincronizados.
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Fabrica {
	
	/**
	 * Caja para ser completada por el embotellador
	 */
	private Caja cajaParaLLenar = new Caja(0);
	
	/**
	 * Almacena las cajas llenas
	 */
	public LinkedList<Caja> almacen = new LinkedList<Caja>();
	
	/**
	 * Id a usar para la proxima botella
	 */
	public int idProximaBotella=1;
	
	/**
	 * Id a usar para la proxima caja
	 */
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
	
	
	/**
	 * Devuelve una referencia a la caja que hay actualmente para ser llenada
	 * @return La caja
	 */
	public synchronized Caja getCajaParaLLenar() {
		return cajaParaLLenar;
	}
 
}
