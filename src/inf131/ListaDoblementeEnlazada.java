package inf131;

import unexpected.Fabrica;
import estructuras.Pila;
import estructuras.conexiones.NodoBilista;

public class ListaDoblementeEnlazada<TDato> extends
		Pila<TDato, NodoBilista<TDato>> {

	public ListaDoblementeEnlazada() {
		super(new Fabrica<NodoBilista<TDato>>() {

			@Override
			public NodoBilista<TDato> crear() {
				return new NodoBilista<TDato>();
			}
		});
	}

	public String toString() {
		return super.toString(" <-> ");
	}

}
