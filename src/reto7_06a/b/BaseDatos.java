package reto7_06a.b;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Simula una base de datos. Almacena un listado de numeros enteros en un
 * archivo binario de manera secuencial. Se le puede pedir que devuelve el valor
 * de una de las posiciones guardadas o que incremente en 1 el valor de alguna
 * de las posiciones de archivo.
 * </p>
 * <p>
 * Controla el acceso con un algoritmo derivado del de W. Stallins con
 * prioridad para los lectores.
 * </p>
 * <p>
 * En vez de usar semáforos para la MUTEX se usan banderas  y metodos sincronizados que identifican
 * cuando puede un lector o un escritor avanzar en el codigo.
 * </p>
 * <p>
 * Las banderas son lecotresPermitidos y escritoresPermitidos. Los metodos que comprueban y modifican 
 * esas banderas son:  bloquearLectores(),liberarLectores(), bloquearEscritores(), liberarEscritores()
 * </p>
 * <p>
 * @author Jose Javier Bailon Ortiz
 */
public class BaseDatos {
	/**
	 * lectores activos
	 */
	private int numeroLectores = 0;

	/**
	 * Booleano para controlar el paso de lectores
	 */
	private boolean lectoresPermitidos;
	/**
	 * Booleano para controlar el paso de escritores
	 */
	private boolean escritoresPermitidos;

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
	 * @param ruta         Ruta del archivo en disco
	 * @param numeroTuplas Numero de tuplas de la base de datos
	 */
	public BaseDatos(String ruta, int numeroTuplas) {

		// inicializar valores
		this.lectoresPermitidos = true;
		this.escritoresPermitidos = true;
		this.f = new File(ruta);
		this.numeroTuplas = numeroTuplas;

		// crear base de datos
		crearBaseDatos();
	}

	/**
	 * Incrementa el valor de una tupla en +1
	 * 
	 * Controla el mutex de la seccion critica segun lo descrito en los metodos bloquearEscritores() y liberarEscritores()
	 * 
	 * @param id Id de la tupla a editar
	 * @see #bloqueaEscritores()
	 * @see #liberarEscritores()
	 */
	public void update(int id) {
		// escritores esperan
		bloqueaEscritores();

		// escritura a base de datos
		// >>seccion critica
		escribirADisco(id);
		// <<fin seccion critica

		// liberacion de nuevo escritor
		liberarEscritores();
	}

	/**
	 * Devuelve el valor de una tupla.  Controla el mutex de la seccion critica según
	 * lo descrito en los metodos bloquearLectores(), bloquearEscritores, liberarLectores() y liberarEscritores()
	 * 
	 * @param id Id de la tupla
	 * @return El valor de la tupla
	 * 
	 * @see #bloqueaEscritores()
	 * @see #liberarEscritores()
	 * @see #bloqueaLectores()
	 * @see #liberarEscritores()
	 */
	public int select(int id) {
		// lectores esperan
		bloqueaLectores();

		// registrar lector activo
		numeroLectores++;
		// bloquear escritores si se es el primer lector
		if (numeroLectores == 1)
			bloqueaEscritores();

		// liberacion de un lector
		liberarLectores();

		// lectura de la base de datos
		// >>seccion critica
		int leido = leerDeDisco(id);
		// <<fin seccion critica

		// letores esperan

		bloqueaLectores();

		// decrementar el nuemro de lectores activos
		numeroLectores--;

		// si no quedan lectores se da paso a los escritores
		if (numeroLectores == 0)
			liberarEscritores();
		// liberar lector
		liberarLectores();

		// devolver valor leido
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
			// ir a inicio de archivo
			raf.seek(0);
			// recoger todos los valores hasta EOF
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

		// devolver lista
		return listaValores;
	}

	/**
	 * Devuelve la ruta del archivo de la base de datos
	 * 
	 * @return La ruta absoluta del archivo
	 */
	public String getRuta() {
		return this.f.getAbsolutePath();
	}

	/**
	 * Bloqueo de los lectores esperando hasta tener la vez
	 */
	synchronized private void bloqueaLectores() {
		while (!lectoresPermitidos)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		lectoresPermitidos = false;
	}

	/**
	 * Libera los lectores
	 */
	synchronized private void liberarLectores() {
		lectoresPermitidos = true;
		this.notifyAll();
	}

	/**
	 * Bloquea los escritores esperando hasta tener la vez
	 */
	synchronized private void bloqueaEscritores() {
		while (!escritoresPermitidos)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		escritoresPermitidos = false;
	}

	/**
	 * Libera los escritores
	 */
	synchronized private void liberarEscritores() {
		escritoresPermitidos = true;
		this.notifyAll();
	}

	/**
	 * Crea la base de datos en disco con el numero de tuplas especificado con valor
	 * 0 cada una. Si el archivo ya existe es borrado
	 */
	private void crearBaseDatos() {
		// borrado
		if (f.exists()) {
			if (!f.isDirectory())
				f.delete();
			else {
				System.err.println("Debe especificar la ruta de un archivo en la configuracion de MainBaseDatos.java");
				System.exit(0);
			}
		}

		// creacion
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// rellenar archivo con ceros
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(f, "rw");
			for (int i = 0; i < numeroTuplas; i++) {
				raf.writeInt(0);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Archivo " + this.getRuta() + " no encontrado");
			System.exit(0);
		} catch (IOException e) {
			System.out.println("Error accediendo a" + this.getRuta());
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
	 * Lee el valor de una tupla del disco y actualiza su valor a +1 El acceso a la
	 * posicion en el archivo es realizado con id*4 debido a que almacena enteros
	 * los cuales ocupan 4 bytes.
	 * 
	 * @param id Id de la tupla
	 */
	private void escribirADisco(int id) {
		RandomAccessFile raf = null;
		try {
			// recoger valor actual
			int actual = this.leerDeDisco(id);
			raf = new RandomAccessFile(f, "rw");
			// desfase hasta la posicion de la tupla
			raf.seek(id * 4);
			// escribir nuevo valor
			raf.writeInt(actual + 1);

		} catch (FileNotFoundException e) {
			System.out.println("Archivo " + this.getRuta() + " no encontrado");
			System.exit(0);
		} catch (IOException e) {
			System.out.println("Error escribiendo en " + this.getRuta());
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
	 * Lee el valor de una tupla del disco El acceso a la posicion en el archivo es
	 * realizado con id*4 debido a que almacena enteros los cuales ocupan 4 bytes.
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
			System.out.println("Archivo " + this.getRuta() + " no encontrado");
			System.exit(0);
		} catch (IOException e) {
			System.out.println("Error leyendo de " + this.getRuta());
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
