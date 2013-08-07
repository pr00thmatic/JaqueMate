package estructuras.operaciones.operadores;

import estructuras.conexiones.Nodo;

public interface Ejecutor<TNodo extends Nodo<TDato>, TDato, TRes> {
	public abstract TRes ejecucion(TNodo nodoActual);
}
