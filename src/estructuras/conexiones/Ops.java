package estructuras.conexiones;

import predecibles.patrones.Predecible;

public class Ops {
	public static <TDato extends Predecible<TDato>> void intercambiar(
			NodoLista<TDato> a, NodoLista<TDato> b) {
		TDato aux = a.getDato();
		a.setDato(b.getDato());
		b.setDato(aux);
	}
}
