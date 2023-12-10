package reto7_2b.b;

import java.util.Arrays;

public class Caja {
	int id;
  int capacidadDisponible=Config.TAMANO_CAJA;
  int[] botellas=new int[Config.TAMANO_CAJA];
  
  public Caja(int id) {
	this.id = id;
}

public int getId() {
	return id;
}

public int getCapacidadDisponible() {
	return capacidadDisponible;
}

public boolean ponerBotella(int botella) {
	  	if ((capacidadDisponible-1)>=0)
	  		botellas[capacidadDisponible-1]=botella;
	  	return --capacidadDisponible>0;
  }

@Override
public String toString() {
	return "Caja " + id;
}

public String contenido() {
	return "Caja " + id + ", botellas=" + Arrays.toString(botellas).replace(",0",",X");

}

}