package inf131.operaciones.operadores;

import predecibles.operadores.Selector;
import predecibles.patrones.Predecible;
import estructuras.conexiones.Nodo;
import estructuras.operaciones.operadores.Ejecutor;

public abstract class Encontrador<TDato extends Predecible<TDato>, TModelo, TNodo extends Nodo<TDato>, TRes>
		implements Ejecutor<TNodo, TDato, TRes> {
	protected Selector<TDato, TModelo> selector;

	public Encontrador(Selector<TDato, TModelo> elSelectorDeDatosAEncontrar) {
		selector = elSelectorDeDatosAEncontrar;
	}

	public boolean esSimilarAlModelo(TNodo unNodo) {
		if (unNodo == null)
			return false;
		return selector.selecciona(unNodo.getDato());
	}

}
