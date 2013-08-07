package estructuras.operaciones.operadores;

import estructuras.conexiones.Nodo;

public abstract class LectorLineal<TNodo extends Nodo<TDato>, TDato> implements
		Lector<TNodo, TDato> {
	protected boolean finalizar = false;

	/**
	 * lleva al nodoActual a la siguiente posición
	 */
	public abstract void next();

	/**
	 * pone al nodoActual en la posición en la que debería comenzar. (Inicializa
	 * el proceso de lectura)
	 */
	public abstract void start();

	/**
	 * 
	 * @return el último elemento de la secuencia
	 */
	public abstract TNodo fin();

	public abstract TNodo nodoActual();

	public boolean criterioDeFin() {
		if (nodoActual() == fin() & !finalizar) {
			finalizar = true;
			return false;
		} else {
			return finalizar;
		}
	}

	@Override
	public <TRes> TRes leer(Ejecutor<TNodo, TDato, TRes> proceso) {
		TRes response = null;
		start();
		while (!criterioDeFin()) {
			response = proceso.ejecucion(nodoActual());
			if (!finalizar)
				next();
		}
		return response;
	}

}
