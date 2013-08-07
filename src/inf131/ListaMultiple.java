package inf131;

import java.util.Scanner;

import inf131.operaciones.operadores.LectorHastaPrimeraCoincidencia;
import estructuras.conexiones.NodoLista;
import inf131.operaciones.OpsLectores;
import estructuras.operaciones.discriminantes.TipoDeSimilitud;
import estructuras.operaciones.operadores.Ejecutor;
import predecibles.entidades.Entero;
import predecibles.entidades.ItemIndexado;
import predecibles.entidades.Perrito;
import predecibles.operadores.Comparador;
import predecibles.operadores.entidades.ComparadorPorIndice;
import predecibles.patrones.Predecible;
import unexpected.Fabrica;

public class ListaMultiple<TDato extends Predecible<TDato>, TIndice extends Predecible<TIndice>> {
	private ListaSimple<ItemIndexado<ListaSimple<TDato>, TIndice>> listas;

	public ListaMultiple(final Fabrica<TDato> creadorDeDatos,
			final Fabrica<TIndice> creadorDeIndices) {
		listas = new ListaSimple<ItemIndexado<ListaSimple<TDato>, TIndice>>(
				new Fabrica<ItemIndexado<ListaSimple<TDato>, TIndice>>() {

					@Override
					public ItemIndexado<ListaSimple<TDato>, TIndice> crear() {
						return new ItemIndexado<ListaSimple<TDato>, TIndice>(
								new Fabrica<ListaSimple<TDato>>() {

									@Override
									public ListaSimple<TDato> crear() {
										return new ListaSimple<TDato>(
												creadorDeDatos);
									}

								}, new Fabrica<TIndice>() {

									@Override
									public TIndice crear() {
										return creadorDeIndices.crear();
									}
								});
					}
				});
	}

	public static void main(String[] args) {
		ListaMultiple<Perrito, Entero> x = new ListaMultiple<Perrito, Entero>(
				Perrito.fabricaDePerritos(), Entero.fabricaDeNumeros());
		x.leer();
		Scanner input = new Scanner(System.in);
		System.out.println("Qué lista quieres visualizar?");
		ListaSimple<Perrito> encontrada = x.encontrarLista(
				new Entero(input.nextInt()), Entero.porValor());
		System.out.println(encontrada);
		System.out.println("y la lista con más datos es...");
		System.out.println(x.encontrarListaMasGrande());
		System.out.println("y la lista con menos datos es...");
		System.out.println(x.encontrarListaMasPequeña());
	}

	public ItemIndexado<ListaSimple<TDato>, TIndice> encontrarListaMasGrande() {
		return OpsLectores
				.cotaSuperior(
						listas.getRecorredor(),
						new Comparador<ItemIndexado<ListaSimple<TDato>, TIndice>, ItemIndexado<ListaSimple<TDato>, TIndice>>() {

							@Override
							public int comparacion(
									ItemIndexado<ListaSimple<TDato>, TIndice> a,
									ItemIndexado<ListaSimple<TDato>, TIndice> b) {
								return a.getContenido().calcularTamano()
										- b.getContenido().calcularTamano();
							}
						});
	}

	public ItemIndexado<ListaSimple<TDato>, TIndice> encontrarListaMasPequeña() {
		return OpsLectores
				.cotaInferior(
						listas.getRecorredor(),
						new Comparador<ItemIndexado<ListaSimple<TDato>, TIndice>, ItemIndexado<ListaSimple<TDato>, TIndice>>() {

							@Override
							public int comparacion(
									ItemIndexado<ListaSimple<TDato>, TIndice> a,
									ItemIndexado<ListaSimple<TDato>, TIndice> b) {
								return a.getContenido().calcularTamano()
										- b.getContenido().calcularTamano();
							}
						});
	}

	public void leer() {
		listas.leer();
	}

	public void agregarEn(TIndice unIndice,
			Comparador<TIndice, TIndice> criterio, TDato elDato) {
		encontrarLista(unIndice, criterio).agregar(elDato);
	}

	public ListaSimple<TDato> encontrarLista(TIndice unIndice,
			Comparador<TIndice, TIndice> criterio) {
		return new LectorHastaPrimeraCoincidencia<ItemIndexado<ListaSimple<TDato>, TIndice>, TIndice>(
				unIndice, new ComparadorPorIndice<ListaSimple<TDato>, TIndice>(
						criterio), TipoDeSimilitud.IGUAL, listas.getPrimero())
				.leer(new Ejecutor<NodoLista<ItemIndexado<ListaSimple<TDato>, TIndice>>, ItemIndexado<ListaSimple<TDato>, TIndice>, ListaSimple<TDato>>() {
					@Override
					public ListaSimple<TDato> ejecucion(
							NodoLista<ItemIndexado<ListaSimple<TDato>, TIndice>> nodoActual) {
						try {
							return nodoActual.getDato().getContenido();
						} catch (NullPointerException e) {
							return null;
						}
					}
				});
	}

	public String toString() {
		return listas.toString("\n");
	}
}
