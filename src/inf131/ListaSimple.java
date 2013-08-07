package inf131;

import inf131.operaciones.OpsLectores;
import inf131.operaciones.OpsListasPredecibles;
import estructuras.Pila;
import estructuras.conexiones.NodoLista;
import predecibles.operadores.Comparador;
import predecibles.operadores.Condicionante;
import predecibles.operadores.Selector;
import predecibles.patrones.Predecible;
import unexpected.Fabrica;

public class ListaSimple<TDato extends Predecible<TDato>> extends
		Pila<TDato, NodoLista<TDato>> implements Predecible<ListaSimple<TDato>> {
	private Fabrica<TDato> creadorDeDatos;

	public TDato cima() {
		return getPrimero().getDato();
	}

	public ListaSimple(Fabrica<TDato> creadorDeDatos) {
		super(new Fabrica<NodoLista<TDato>>() {

			@Override
			public NodoLista<TDato> crear() {
				return new NodoLista<TDato>();
			}
		});
		this.creadorDeDatos = creadorDeDatos;
	}

	public String toString() {
		return toString(" -> ");
	}

	@Override
	public NodoLista<TDato> eliminarNodo() {
		return super.eliminarNodo();
	}

	public void recibirElementos(ListaSimple<TDato> otra) {
		while (!otra.estaVacia()) {
			agregar(otra.eliminar());
		}
	}

	@Override
	public void leer() {
		OpsListasPredecibles.leer(this, creadorDeDatos);
	}

	@Override
	public boolean estado(Condicionante<ListaSimple<TDato>> criterio) {
		return criterio.condicion(this);
	}

	public <TModelo> Comparador<ListaSimple<TDato>, TModelo> porNumeroDeElementos(
			final Selector<TDato, TModelo> selectorDeDatos) {
		return new Comparador<ListaSimple<TDato>, TModelo>() {

			@Override
			public int comparacion(ListaSimple<TDato> a, TModelo b) {
				int elementosEnA = OpsLectores.contar(a.getRecorredor(),
						selectorDeDatos);
				int elementosEnB = OpsLectores.contar(a.getRecorredor(),
						selectorDeDatos);
				return elementosEnA - elementosEnB;
			}
		};
	}

	public Comparador<ListaSimple<TDato>, ListaSimple<TDato>> porNumeroDeElementos() {
		return new Comparador<ListaSimple<TDato>, ListaSimple<TDato>>() {

			@Override
			public int comparacion(ListaSimple<TDato> a, ListaSimple<TDato> b) {
				return a.calcularTamano() - b.calcularTamano();
			}
		};
	}

	@Override
	public <TModelo> int comparar(TModelo otro,
			Comparador<ListaSimple<TDato>, TModelo> criterio) {
		return criterio.comparacion(this, otro);
	}

}
