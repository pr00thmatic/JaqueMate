package inf131;

import unexpected.Fabrica;
import estructuras.Cola;
import estructuras.conexiones.NodoLista;

public class ListaCircular<TDato> extends Cola<TDato, NodoLista<TDato>> {

	public ListaCircular() {
		super(new Fabrica<NodoLista<TDato>>() {

			@Override
			public NodoLista<TDato> crear() {
				return new NodoLista<TDato>();
			}
		});
	}

	public String toString() {
		return toString(" -> ");
	}

}
