package reto7_06b.a;


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
 * Controla el acceso con un algoritmo de lector escritor de J. Bacon con 
 * prioridad para los escritores.
 * 
 * En cuanto hay un escritor esperando tiene prioridad sobre el resto de lectores. 
 * Este comportamiento esta implementado con semaforos. 
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class BaseDatos {

	//semaforos
	/**
	 * Semaforo de lecturas pendientes
	 */
	private Semaphore sLP; //lecturas pendientes
	
	/**
	 * Semaforo de escrituras pendientes
	 */
	private Semaphore sEP; //escrituras pendientes
	
	/**
	 * Semaforo de escrutura exclusiva
	 */
	private Semaphore sEEX;//escritura esclusiva
	
	/**
	 * Semaforo de mutex para contadores
	 */
	private Semaphore sCEX;//mutex para acceso a contadores
	
	//contadores
	/**
	 * Lectores activos
	 */
	private int la;
	
	/**
	 * Lectores leyendo
	 */
	private int ll;
	
	/**
	 * Escritores activos
	 */
	private int ea;
	
	/**
	 * Escritores escribiendo
	 */
	private int ee;

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
		la = 0;
		ll = 0;
		ea = 0;
		ee = 0;
		sLP = new Semaphore(0);
		sEP = new Semaphore(0);
		sEEX = new Semaphore(1);
		sCEX = new Semaphore(1);
		this.f = new File(ruta);
		this.numeroTuplas = numeroTuplas;
		
		//crear base de datos
		crearBaseDatos();
	}

	/**
	 * Incrementa el valor de una tupla en +1
	 * 
	 * Controla el mutex de la seccion critica según la parte correspondiente a 
	 * los escritores del algoritmo de J. Bacon con  prioridad para los escritores
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
	 * los lectores del algoritmo de J. Bacon con  prioridad para los escritores
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
	 * Adquirir lector segun algoritmo de J. Bacon con prioridad para los escritores
	 */
	private void adquirir_lector() {
		//mutex de contadores
		try {
			sCEX.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//registrar lector activo
		la++;
		//si no hay escritores activos incrementar los lectores leyendo
		//y liberar un permiso de lecturas pendientes
		if (ea == 0) {
			ll++;
			sLP.release();
		}
		//fin de mutex de contadores
		sCEX.release();
		
		//esperar al semaforo de lecturas pendientes
		try {
			sLP.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Liberar lector segun algoritmo de J. Bacon con prioridad para los escritores
	 */
	private void liberar_lector() {
		//mutex contadores
		try {
			sCEX.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//decrementar lectores leyendo
		ll--;
		//decrementar lectores activos
		la--;
		//si no hay lectores leyendo incrementar
		//los lectores escribiendo  y escrituras pendientes tantos como escritores activos 
		//falten respecto a escritores escribiendo
		if (ll==0) 
			while(ee<ea) {
				ee++;
				sEP.release();
			}
		//fin mutex contadores
		sCEX.release();
	}
	
	/**
	 * Adquirir escritor  segun algoritmo de J. Bacon con prioridad para los escritores
	 */
	private void adquirir_escritor()  {
		//mutex contadores
		try {
			sCEX.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//incrementar escritor activo
		ea++;
		//si no hay lectores leyendo incrementar escriotres escribiendo
		//y dar permiso en semaforo de escrituras pendientes
		if (ll==0) {
			ee++;
			sEP.release();
		}
		//fin mutex contadores
		sCEX.release();
		
		//esperar semaforos de escrituras pendientes y escritura exclusiva
		try {
			sEP.acquire();
			sEEX.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Liberar escritor segun algoritmo de J. Bacon con prioridad para los escritores
	 */
	private void liberar_escritor() {
		//liberar escritura exclusiva
		sEEX.release();
		//mutex contadores
		try {
			sCEX.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//decremetnar escritores escribiendo
		ee--;
		//decrementar escritores activos
		ea--;
		//si no quedan escritores liberar tantos lectores leyendo
		//como lectores activos haya
		if (ea==0) {
			while(ll<la) {
				ll++;
				sLP.release();
			}
		}
		//fin mutex contadores
		sCEX.release();
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
