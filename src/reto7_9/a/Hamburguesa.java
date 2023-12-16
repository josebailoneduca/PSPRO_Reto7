package reto7_9.a;

public class Hamburguesa {
 int id;
 int panecillo1;
 int panecillo2;
 int ingrediente;
public Hamburguesa(int id, int panecillo1, int panecillo2, int ingrediente) {
	super();
	this.id = id;
	this.panecillo1 = panecillo1;
	this.panecillo2 = panecillo2;
	this.ingrediente = ingrediente;
}
@Override
public String toString() {
	return "Ham." + id + "(P" + panecillo1 + "I"+ingrediente+"P" + panecillo2 + ")";
}
 
}
