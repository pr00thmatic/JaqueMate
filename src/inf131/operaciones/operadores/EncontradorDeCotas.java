package inf131.operaciones.operadores;

import predecibles.operadores.Comparador;
import predecibles.operadores.Selector;
import predecibles.patrones.Predecible;
import estructuras.conexiones.Nodo;
import estructuras.operaciones.discriminantes.TipoDeCota;
import estructuras.operaciones.discriminantes.TipoDeSimilitud;

public class EncontradorDeCotas<TDato extends Predecible<TDato>, TNodo extends Nodo<TDato>>
		extends Encontrador<TDato, TDato, TNodo, TDato> {
	// el modelo a comparar cambia constantemente, y el tipo de similitud
	// debería ser siempre MAYOR o MENOR dependiendo del tipo de cota
	public EncontradorDeCotas(TipoDeCota tipoCota,
			Comparador<TDato, TDato> criterio) {
		super(new Selector<TDato, TDato>(null, criterio, null));
		switch (tipoCota) {
		case INFERIOR:
			selector.setTipoDeSimilitud(TipoDeSimilitud.MENOR);
			break;
		case SUPERIOR:
			selector.setTipoDeSimilitud(TipoDeSimilitud.MAYOR);
			break;
		}
	}

	@Override
	public boolean esSimilarAlModelo(TNodo unNodo) {
		if (selector.getModelo() == null)
			if (unNodo == null)
				return false;
			else
				return true;
		else if (unNodo == null)
			return false;
		else
			return super.esSimilarAlModelo(unNodo);
	}

	@Override
	public TDato ejecucion(TNodo nodoActual) {
		if (esSimilarAlModelo(nodoActual))
			selector.setModelo(nodoActual.getDato());
		return selector.getModelo();
	}
}
