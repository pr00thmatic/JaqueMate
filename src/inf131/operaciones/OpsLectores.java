package inf131.operaciones;

import inf131.operaciones.operadores.Encontrador;
import inf131.operaciones.operadores.EncontradorDeCotas;
import estructuras.Bolsa;
import estructuras.conexiones.Nodo;
import estructuras.operaciones.discriminantes.TipoDeCota;
import estructuras.operaciones.operadores.Lector;
import predecibles.operadores.Comparador;
import predecibles.operadores.Selector;
import predecibles.patrones.Predecible;

public class OpsLectores {
	public static <TDato extends Predecible<TDato>, TModelo, TNodo extends Nodo<TDato>> int contar(
			Lector<TNodo, TDato> lector, Selector<TDato, TModelo> selectorDatos) {
		return lector.leer(new Encontrador<TDato, TModelo, TNodo, Integer>(
				selectorDatos) {
			int contador = 0;

			@Override
			public Integer ejecucion(TNodo nodoActual) {
				if (esSimilarAlModelo(nodoActual))
					contador++;
				return contador;
			}
		});
	}

	public static <TDato extends Predecible<TDato>, TModelo, TNodo extends Nodo<TDato>> void encontrarTodos(
			Lector<TNodo, TDato> lector,
			Selector<TDato, TModelo> selectorDatos,
			final Bolsa<TDato> recipiente) {
		lector.leer(new Encontrador<TDato, TModelo, TNodo, Void>(selectorDatos) {

			@Override
			public Void ejecucion(TNodo nodoActual) {
				if (esSimilarAlModelo(nodoActual))
					recipiente.agregar(nodoActual.getDato());
				return null;
			}
		});
	}

	public static <TDato extends Predecible<TDato>, TNodo extends Nodo<TDato>> TDato cotaSuperior(
			Lector<TNodo, TDato> lector, Comparador<TDato, TDato> criterio) {
		return lector.leer(new EncontradorDeCotas<TDato, TNodo>(
				TipoDeCota.SUPERIOR, criterio));
	}

	public static <TDato extends Predecible<TDato>, TNodo extends Nodo<TDato>> TDato cotaInferior(
			Lector<TNodo, TDato> lector, Comparador<TDato, TDato> criterio) {
		return lector.leer(new EncontradorDeCotas<TDato, TNodo>(
				TipoDeCota.INFERIOR, criterio));
	}

}
