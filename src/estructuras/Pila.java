package estructuras;

import unexpected.Fabrica;
import estructuras.conexiones.NodoLista;

public class Pila<TDato, TNodo extends NodoLista<TDato>> extends
		Lista<TDato, TNodo> {
	TNodo alfa; // el primer elemento de la lista;

	public Pila(Fabrica<TNodo> creadorDeNodos) {
		super(creadorDeNodos);
	}

	@Override
	public TNodo getPrimero() {
		return alfa;
	}

	@Override
	public TNodo getUltimo() {
		return null; // el último elemento de una pila es el null :P
	}

	@Override
	protected void agregar(TNodo unNodo) {
		unNodo.conectar(alfa);
		alfa = unNodo;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected TNodo eliminarNodo() {
		TNodo eliminado = alfa;
		try {
			alfa = (TNodo) alfa.getSiguiente();
		} catch (NullPointerException e) {
			return null;
		}
		return eliminado;
	}

	@Override
	public boolean estaVacia() {
		return alfa == null;
	}

}
