package reto7_2b.a;

import java.util.Arrays;

public class Caja {
	private int id;
  private int capacidad=Config.TAMANO_CAJA;
  private int[] botellas=new int[Config.TAMANO_CAJA];
  
  public Caja(int id) {
	this.id = id;
}

public int getId() {
	return id;
}

 

public boolean ponerBotella(int botella) {
	  	if ((capacidad-1)>=0)
	  		botellas[capacidad-1]=botella;
	  	return --capacidad>0;
  }

@Override
public String toString() {
	return "Caja " + id;
}

public String contenido() {
	return "Caja " + id + ", botellas=" + Arrays.toString(botellas).replace(",0",",X");

}

}
