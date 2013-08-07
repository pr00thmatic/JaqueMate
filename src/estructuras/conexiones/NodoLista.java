package estructuras.conexiones;

import java.util.NoSuchElementException;


public class NodoLista<TDato> implements Nodo<TDato> {
	private TDato dato;
	private Link<NodoLista<TDato>> linkAlSiguiente;

	// tiene que ser privado, nadie tiene que poder acceder al atributo de sus
	// conexiones, ni siquiera con protected! porque la forma en la que maneja
	// su conexión es un tanto complicada.

	public NodoLista() {
		dato = null;
		linkAlSiguiente = new Link<NodoLista<TDato>>(this);
	}

	public NodoLista(TDato dato) {
		this.dato = dato;
	}

	public void conectar(NodoLista<TDato> elSiguiente) {
		try {
			linkAlSiguiente.conectar(elSiguiente.linkAlSiguiente);
		} catch (NullPointerException e) {
			linkAlSiguiente.conectar(null);
		}
	}

	public NodoLista<TDato> getSiguiente() {
		return linkAlSiguiente.getDestino();
	}

	public NodoLista<TDato> getSiguiente(int grado) {
		return linkAlSiguiente.getDestino(grado);
	}

	public boolean hasSiguiente() {
		return linkAlSiguiente.hasDestino();
	}

	public boolean hasSiguiente(int grado) {
		return linkAlSiguiente.hasDestino(grado);
	}

	public NodoLista<TDato> eliminarSiguiente() {
		NodoLista<TDato> eliminado = linkAlSiguiente.getDestino();
		if (eliminado == null)
			throw new NoSuchElementException();
		conectar(eliminado.getSiguiente());
		return eliminado;
	}

	@Override
	public TDato getDato() {
		return dato;
	}

	@Override
	public void setDato(TDato nuevoDato) {
		dato = nuevoDato;
	}
}
