package predecibles.operadores.entidades;

import predecibles.entidades.ItemIndexado;
import predecibles.operadores.Comparador;
import predecibles.patrones.Predecible;

public class ComparadorPorIndice<TItem extends Predecible<TItem>, TIndice extends Predecible<TIndice>>
		implements Comparador<ItemIndexado<TItem, TIndice>, TIndice> {
	Comparador<TIndice, TIndice> criterioIndices;

	public ComparadorPorIndice(Comparador<TIndice, TIndice> criterioIndices) {
		this.criterioIndices = criterioIndices;
	}

	@Override
	public int comparacion(ItemIndexado<TItem, TIndice> a, TIndice b) {
		return a.getIndice().comparar(b, criterioIndices);
	}

}
