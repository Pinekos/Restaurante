package examen;

public class Pedido {
int mesa;
String primero, segundo, postre, observacion, fecha;

public Pedido(int mesa, String primero, String segundo, String postre, String observacion, String fecha) {
this.mesa = mesa;
this.primero = primero;
this.segundo = segundo;
this.postre = postre;
this.observacion = observacion;
this.fecha = fecha;
}

public int getMesa() {
	return mesa;
}

public void setMesa(int mesa) {
	this.mesa = mesa;
}

public String getPrimero() {
	return primero;
}

public void setPrimero(String primero) {
	this.primero = primero;
}

public String getSegundo() {
	return segundo;
}

public void setSegundo(String segundo) {
	this.segundo = segundo;
}

public String getPostre() {
	return postre;
}

public void setPostre(String postre) {
	this.postre = postre;
}

public String getObservacion() {
	return observacion;
}

public void setObservacion(String observacion) {
	this.observacion = observacion;
}

public String getFecha() {
	return fecha;
}

public void setFecha(String fecha) {
	this.fecha = fecha;
}
}


