package estructuras.operaciones.operadores;

import estructuras.conexiones.NodoLista;

public class RecorredorListas<TNodo extends NodoLista<TDato>, TDato> extends
		LectorLineal<TNodo, TDato> {
	protected TNodo principio;
	protected TNodo fin;
	protected TNodo lector;

	public RecorredorListas(TNodo principio, TNodo fin) {
		this.principio = principio;
		this.fin = fin;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void next() {
		try {
			lector = (TNodo) lector.getSiguiente();
		} catch (NullPointerException e) {
		}
	}

	public void start() {
		lector = principio;
	}

	@Override
	public TNodo fin() {
		return fin;
	}

	@Override
	public TNodo nodoActual() {
		return lector;
	}
}
