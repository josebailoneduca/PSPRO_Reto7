package reto7_06b.b;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Simula una base de datos. Almacena un listado de numeros enteros en un
 * archivo binario de manera secuencial. Se le puede pedir que devuelve el valor
 * de una de las posiciones guardadas o que incremente en 1 el valor de alguna
 * de las posiciones de archivo.
 * 
 * Controla el acceso con monitor. Los lectores y escritores piden paso para hacer su operacion y sera dado en funcion de lo siguiente:
 * Los escritores son contabilizados conforme entran a pedir paso y son descontabilizados cuando terminan su accion.
 * Los lectores son contabilizados conforme adquieren el paso y son descontabilizados cuando terminan su accion.

 * Cuando entra un lector a pedir paso si encuentra un numero de escritores contabilizados mayor que cero entra en espera hasta que se rompa esa condicion.
 * Cuando entra un escritor a pedir paso si encuentra la lectura o la escritura activa espera hasta que se rompa esa condicion.

 * Cuando un escritor ha obtenido el paso marca la escritura como activa.
 * Cuando un lector ha obtenido el paso marca la lectura como activa y es contabilizado.
 * 
 * Cuando un escritor termina su accion desactiva el estado de escritura y notifica a los hilos en espera.
 * Cuando un lector termina su accion, si es el ultimo lector contabilizado desactiva el estado de escritura. Siempre notifica a los hilos en espera.
 * 
 * El resultado es que que se puede producir varias lecturas o una escritura de manera simultanea y en cuanto hay un escritor esperando 
 * tiene prioridad sobre el resto de lectores y pasa en cuanto no haya nadie en el proceso de lectura ni escritura
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class BaseDatos {

	//contadores
	/**
	 * Numero de escritores en cola
	 */
	int ne;
	/**
	 * Numero de lectores leyendo
	 */
	int nll; 
	/**
	 * Escritura produciendose
	 */
	boolean escrituraActiva; 
	/**
	 * Lectura produciendose
	 */
	boolean lecturaActiva; 

	/**
	 * Cantidad de tuplas de la base de datos
	 */
	private int numeroTuplas;

	/**
	 * File que apunta al archivo de la base de datos
	 */
	private File f;

	/**
	 * Constructor
	 * 
	 * @param ruta                     Ruta del archivo en disco
	 * @param numeroTuplas             Numero de tuplas de la base de datos
	 */
	public BaseDatos(String ruta, int numeroTuplas) {
		
		//inicializar valores
		ne=0;
		nll=0;
		escrituraActiva=false;
		lecturaActiva=false;
		this.f = new File(ruta);
		this.numeroTuplas = numeroTuplas;
		
		//crear base de datos
		crearBaseDatos();
	}

	/**
	 * Incrementa el valor de una tupla en +1
	 * 
	 * Controla el mutex de la seccion critica según la parte correspondiente a 
	 * los escritores del algoritmo de W. Stallins con modificaciones
	 * para evitar prioridad de lectores si asi se ha configurado
	 * 
	 * @param id Id de la tupla a editar
	 */
	public void update(int id) {
		 
		adquirir_escritor();
		//escritura a base de datos
		//>>seccion critica
		escribirADisco(id);
		//<<fin seccion critica
		liberar_escritor();
	}

	/**
	 * Devuelve el valor de una tupla
	 * Controla el mutex de la seccion critica según la parte correspondiente a 
	 * los lectores del algoritmo de W. Stallins con modificaciones
	 * para evitar prioridad de lectores si asi se ha configurado
	 * 
	 * @param id Id de la tupla
	 * @return El valor de la tupla
	 */
	public int select(int id) {
 		 adquirir_lector();
		
 		 //lectura de la base de datos
		//>>seccion critica
		int leido = leerDeDisco(id);
		//<<fin seccion critica


		liberar_lector();
		//devolver valor leido
		return leido;
	}

	
	
	
	
	/**
	 * Comprobar si hay escritores registrados. Si los hay espera.
	 * En cuanto no haya escritores registrados se pasa. Se contabiliza
	 * el lector y se marca la lectura como activa.
	 *  
	 */
	private synchronized void adquirir_lector() {
		while (ne>0)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		nll++;
		lecturaActiva=true;
	}
	
	/**
	 * Descuenta el lector y si es el ultimo de los que hay leyendo desactiva
	 * el estado de lectura activa
	 */
	private synchronized void liberar_lector() {
		if (--nll==0)
			lecturaActiva=false;
		notifyAll();
	}
	
	/**
	 * Contabiliza un nuevo escritor y lo hace esperar
	 * hasta que no haya ni escritura ni lectura activa.
	 * En cuanto obtiene el paso marca la escritura como activa
	 */
	private synchronized void adquirir_escritor()  {
		ne++;
		while (escrituraActiva || lecturaActiva)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		escrituraActiva=true;
	}
	
	
	/**
	 * Decuenta el escritor y marca la escritura como no activa
	 */
	private synchronized void liberar_escritor() {
		ne--;
		escrituraActiva=false;
		notifyAll();
	}
	
	
	
	
	/**
	 * Devuelve el numero de tuplas disponibles en la base de datos
	 * 
	 * @return El numero de tuplas
	 */
	public int getNumeroTuplas() {
		return numeroTuplas;
	}

	/**
	 * Devuelve una lista con todos los valores de las tuplas
	 * 
	 * @return La lista de valores
	 */
	public List<Integer> getTodasLasTuplas() {
		ArrayList<Integer> listaValores = new ArrayList<Integer>();
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(f, "r");
			//ir a inicio de archivo
			raf.seek(0);
			//recoger todos los valores hasta EOF
			while (true)
				listaValores.add(raf.readInt());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (raf != null)
					raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//devolver lista
		return listaValores;
	}

	/**
	 * Devuelve la ruta del archivo de la base de datos
	 * @return La ruta absoluta del archivo
	 */
	public String getRuta() {
		return this.f.getAbsolutePath();
	}

	/**
	 * Crea la base de datos en disco con el numero de tuplas especificado con valor
	 * 0 cada una. Si el archivo ya existe es borrado
	 */
	private void crearBaseDatos() {
		//borrado
		if (f.exists()) {
			if (!f.isDirectory())
				f.delete();
			else {
				System.err.println("Debe especificar la ruta de un archivo en la configuracion de MainBaseDatos.java");
				System.exit(0);
			}
		}
		
		//creacion
		try {
			f.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//rellenar archivo con ceros
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(f, "rw");
			for (int i = 0; i < numeroTuplas; i++) {
				raf.writeInt(0);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Archivo "+this.getRuta()+" no encontrado");
			System.exit(0);
		} catch (IOException e) {
			System.out.println("Error accediendo a"+this.getRuta());
			System.exit(0);
		} finally {
			try {
				if (raf != null)
					raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Base de datos creada en " + f.getAbsolutePath() + " con " + numeroTuplas + " registros");
	}

	/**
	 * Lee el valor de una tupla del disco y actualiza su valor a +1
	 * El acceso a la posicion en el archivo es realizado con id*4 debido a que almacena enteros los cuales ocupan 4 bytes.
	 * @param id Id de la tupla
	 */
	private void escribirADisco(int id) {
		RandomAccessFile raf = null;
		try {
			//recoger valor actual
			int actual = this.leerDeDisco(id);
			raf = new RandomAccessFile(f, "rw");
			//desfase hasta la posicion de la tupla
			raf.seek(id * 4);
			//escribir nuevo valor
			raf.writeInt(actual + 1);

		} catch (FileNotFoundException e) {
			System.out.println("Archivo "+this.getRuta()+" no encontrado");
			System.exit(0);
		} catch (IOException e) {
			System.out.println("Error escribiendo en "+this.getRuta());
			System.exit(0);
		} finally {
			try {
				if (raf != null)
					raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Lee el valor de una tupla del disco
	 * El acceso a la posicion en el archivo es realizado con id*4 debido a que almacena enteros los cuales ocupan 4 bytes.
	 * 
	 * @param id Id de la tupla
	 * @return valor de la tupla
	 */
	private int leerDeDisco(int id) {
		int salida = 0;
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(f, "r");
			raf.seek(id * 4);
			salida = raf.readInt();

		} catch (FileNotFoundException e) {
			System.out.println("Archivo "+this.getRuta()+" no encontrado");
			System.exit(0);
		} catch (IOException e) {
			System.out.println("Error leyendo de "+this.getRuta());
			System.exit(0);
		} finally {
			try {
				if (raf != null)
					raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return salida;
	}

}
