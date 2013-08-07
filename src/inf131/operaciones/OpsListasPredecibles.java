package inf131.operaciones;

import inf131.operaciones.operadores.Encontrador;

import java.util.Scanner;

import estructuras.Bolsa;
import estructuras.Lista;
import estructuras.conexiones.NodoLista;
import estructuras.operaciones.discriminantes.TipoDeOrden;
import estructuras.operaciones.operadores.Ejecutor;
import predecibles.operadores.Comparador;
import predecibles.operadores.Selector;
import predecibles.patrones.Predecible;
import unexpected.Fabrica;

public abstract class OpsListasPredecibles {

	static class EjecucionOrdenadoraBurbuja<TDato extends Predecible<TDato>>
			implements Ejecutor<NodoLista<TDato>, TDato, Void> {
		Comparador<TDato, TDato> criterio;
		TipoDeOrden tipo;

		public EjecucionOrdenadoraBurbuja(Comparador<TDato, TDato> criterio,
				TipoDeOrden tipoDeOrden) {
			this.criterio = criterio;
			this.tipo = tipoDeOrden;
		}

		public boolean unoVaAntesQueOtro(TDato uno, TDato otro) {
			switch (tipo) {
			case ASCENDENTE:
				return unoVaAntesQueOtro_Ascendentemente(uno, otro);
			case DESCENDENTE:
			default:
				return unoVaAntesQueOtro_Descendentemente(uno, otro);
			}
		}

		public boolean unoVaAntesQueOtro_Descendentemente(TDato uno, TDato otro) {
			return uno.comparar(otro, criterio) > 0;
		}

		public boolean unoVaAntesQueOtro_Ascendentemente(TDato uno, TDato otro) {
			return uno.comparar(otro, criterio) < 0;
		}

		public Void ejecucion(NodoLista<TDato> nodoActual) {
			if (nodoActual != null && nodoActual.hasSiguiente()) {
				if (!unoVaAntesQueOtro(nodoActual.getDato(), nodoActual
						.getSiguiente().getDato())) {
					estructuras.conexiones.Ops.intercambiar(nodoActual,
							nodoActual.getSiguiente());
				}
			}
			return null;
		}

	}

	public static <TDato extends Predecible<TDato>> void ordenarAscendentemente(
			Lista<TDato, NodoLista<TDato>> unaLista,
			Comparador<TDato, TDato> criterio) {
		int elementos = unaLista.calcularTamano();
		for (int i = 0; i < elementos; i++) {
			unaLista.getRecorredor().leer(
					new EjecucionOrdenadoraBurbuja<TDato>(criterio,
							TipoDeOrden.ASCENDENTE));
		}
	}

	public static <TDato extends Predecible<TDato>> void ordenarDescendentemente(
			Lista<TDato, NodoLista<TDato>> unaLista,
			Comparador<TDato, TDato> criterio) {
		int elementos = unaLista.calcularTamano();
		for (int i = 0; i < elementos; i++) {
			unaLista.getRecorredor().leer(
					new EjecucionOrdenadoraBurbuja<TDato>(criterio,
							TipoDeOrden.DESCENDENTE));
		}
	}

	public static <TDato extends Predecible<TDato>> void leer(
			Lista<TDato, NodoLista<TDato>> unaLista,
			Fabrica<TDato> creadorDeDatos) {
		String puerta = "no";
		Scanner input = new Scanner(System.in);
		while (!puerta.equals("fin")) {
			TDato leido = null;
			leido = creadorDeDatos.crear();
			leido.leer();
			unaLista.agregar(leido);
			System.out.print("ingresar 'fin' para salir... ");
			puerta = input.next();
		}
	}

	public static <TDato extends Predecible<TDato>, TModelo> void eliminarTodos(
			Selector<TDato, TModelo> selectorEliminar,
			Lista<TDato, NodoLista<TDato>> unaLista,
			final Bolsa<TDato> papeleraReciclaje) {
		unaLista.getRecorredor().leer(
				new Encontrador<TDato, TModelo, NodoLista<TDato>, Void>(
						selectorEliminar) {

					@Override
					public Void ejecucion(NodoLista<TDato> nodoActual) {
						if (esSimilarAlModelo(nodoActual.getSiguiente())) {
							papeleraReciclaje.agregar(nodoActual
									.eliminarSiguiente().getDato());
						}
						return null;
					}
				});
	}
}
