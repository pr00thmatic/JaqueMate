package predecibles.entidades;

import predecibles.operadores.Comparador;
import predecibles.operadores.Condicionante;
import predecibles.operadores.entidades.ComparadorPorIndice;
import predecibles.patrones.Predecible;
import unexpected.Fabrica;

public class ItemIndexado<TItem extends Predecible<TItem>, TIndice extends Predecible<TIndice>>
		implements Predecible<ItemIndexado<TItem, TIndice>> {
	private TIndice indice;
	private TItem item;

	public ItemIndexado(Fabrica<TItem> fabricaDeItems,
			Fabrica<TIndice> fabricaDeIndices) {
		indice = fabricaDeIndices.crear();
		item = fabricaDeItems.crear();
	}

	public TIndice getIndice() {
		return indice;
	}

	@Override
	public <TModelo> int comparar(TModelo otro,
			Comparador<ItemIndexado<TItem, TIndice>, TModelo> criterio) {
		return criterio.comparacion(this, otro);
	}

	@Override
	public void leer() {
		System.out.println("leyendo el índice del ítem...");
		indice.leer();
		System.out.println("leyendo el item...");
		item.leer();
	}

	@Override
	public boolean estado(Condicionante<ItemIndexado<TItem, TIndice>> criterio) {
		return criterio.condicion(this);
	}

	public Comparador<ItemIndexado<TItem, TIndice>, TIndice> porIndice(
			Comparador<TIndice, TIndice> criterioDeIndices) {
		return new ComparadorPorIndice<TItem, TIndice>(criterioDeIndices);
	}

	public TItem getContenido() {
		return item;
	}

	public String toString() {
		return "**************************INDICE*************************\n"
				+ indice.toString()
				+ "\n***************************************************\n"
				+ item.toString();
	}

}
