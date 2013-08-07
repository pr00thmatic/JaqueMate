package estructuras.operaciones.operadores;

import estructuras.conexiones.Nodo;

public interface LectorParcial<TNodo extends Nodo<TDato>, TDato> extends
		Lector<TNodo, TDato> {

	public boolean revisarSecuenciaDeSalida();

}
