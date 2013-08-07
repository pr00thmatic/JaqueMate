package estructuras;

import java.util.NoSuchElementException;

import unexpected.Fabrica;

import estructuras.conexiones.NodoLista;
import estructuras.operaciones.operadores.Ejecutor;
import estructuras.operaciones.operadores.Lector;
import estructuras.operaciones.operadores.RecorredorListas;

public abstract class Lista<TDato, TNodo extends NodoLista<TDato>> implements
		Bolsa<TDato> {
	private Fabrica<TNodo> fabricaDeNodos;

	public Lector<TNodo, TDato> getRecorredor() {
		System.err
				.println("ADVERTENCIA: utilizando el algoritmo de recorrido por defecto\n\t"
						+ "el recorredor de las listas por defecto, es el Recorredor.java "
						+ "contenido en el paquete listas.");

		System.err.flush();
		return new RecorredorListas<TNodo, TDato>(getPrimero(), getUltimo());
	}

	public <T extends Lista<TDato, ?>> void recibirElementos(T otra) {
		while (!otra.estaVacia()) {
			agregar(otra.eliminar());
		}
	}

	public abstract TNodo getPrimero();

	public abstract TNodo getUltimo();

	protected abstract void agregar(TNodo unNodo);

	protected abstract TNodo eliminarNodo();

	public abstract boolean estaVacia();

	public int calcularTamano() {
		Lector<TNodo, TDato> reader = getRecorredor();
		return reader.<Integer> leer(new Ejecutor<TNodo, TDato, Integer>() {
			int tamano;

			@Override
			public Integer ejecucion(TNodo nodoActual) {
				if (null != nodoActual)
					tamano++;
				return tamano;
			}
		});
	}

	public Lista(Fabrica<TNodo> crearUnNodo) {
		fabricaDeNodos = crearUnNodo;
	}

	public void agregar(TDato unDato) {
		TNodo unNodo = fabricaDeNodos.crear();
		unNodo.setDato(unDato);
		agregar(unNodo);
	}

	public TDato eliminar() {
		try {
			return eliminarNodo().getDato();
		} catch (NullPointerException e) {
			throw new NoSuchElementException();
		}
	}

	public String toString(final String separador) {
		Lector<TNodo, TDato> reader = getRecorredor();
		return reader.<String> leer(new Ejecutor<TNodo, TDato, String>() {
			String response = "";

			@Override
			public String ejecucion(TNodo nodoActual) {
				try {
					response += nodoActual.getDato() + separador;
				} catch (NullPointerException thisIsNotBad) {
					if (!response.equals(""))
						response += "null";
				}
				return response;
			}
		});

	}
}
