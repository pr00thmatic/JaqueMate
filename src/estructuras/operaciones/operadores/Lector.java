package estructuras.operaciones.operadores;

import estructuras.conexiones.Nodo;

public interface Lector<TNodo extends Nodo<TDato>, TDato> {
	public <TRes> TRes leer(Ejecutor<TNodo, TDato, TRes> proceso);
}
