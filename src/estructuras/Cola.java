package estructuras;

import unexpected.Fabrica;
import estructuras.Lista;
import estructuras.conexiones.NodoLista;

public class Cola<TDato, TNodo extends NodoLista<TDato>> extends
		Lista<TDato, TNodo> {

	TNodo omega; // el último elemento de la cola.

	// el elemento 'null' no pertenece a una cola, a menos que la cola esté
	// vacía.

	public Cola(Fabrica<TNodo> creadorDeNodos) {
		super(creadorDeNodos);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TNodo getPrimero() {
		try {
			return (TNodo) omega.getSiguiente();
		} catch (NullPointerException e) {
			return null;
		}
	}

	@Override
	public TNodo getUltimo() {
		return omega;
	}

	@Override
	protected void agregar(TNodo unNodo) {
		try {
			unNodo.conectar(omega.getSiguiente());
			omega.conectar(unNodo);
			omega = unNodo;
		} catch (NullPointerException e) {
			unNodo.conectar(unNodo);
			omega = unNodo;
		}
	}

	@Override
	protected TNodo eliminarNodo() {
		TNodo eliminado = getPrimero();
		if (estaVacia())
			return null;
		if (eliminado.getSiguiente() == eliminado)
			omega = null;
		else
			getUltimo().conectar(eliminado.getSiguiente());
		return eliminado;
	}

	@Override
	public boolean estaVacia() {
		return omega == null;
	}

}
