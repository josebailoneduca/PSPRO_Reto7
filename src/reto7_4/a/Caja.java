package reto7_4.a;

import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Caja {
	private int id;
  private int capacidad=Config.TAMANO_CAJA;
  private int[] botellas=new int[Config.TAMANO_CAJA];
  private ReentrantLock accesoBotellas = new ReentrantLock(true);
  public Caja(int id) {
	this.id = id;
}

public int getId() {
	return id;
}

 

public boolean ponerBotella(int botella) {
	  	try {
			accesoBotellas.lock();
			botellas[capacidad-1]=botella;
			capacidad--;
		}finally {
			accesoBotellas.unlock();
		}
	  	return capacidad>0;
  }

@Override
public String toString() {
	return "Caja " + id;
}

public String contenido() {
	return "Caja " + id + ", botellas=" + Arrays.toString(botellas).replace(",0",",X");

}

}
