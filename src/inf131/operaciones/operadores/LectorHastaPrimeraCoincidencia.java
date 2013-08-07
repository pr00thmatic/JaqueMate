package inf131.operaciones.operadores;

import predecibles.operaciones.OpsPredecibles;
import predecibles.operadores.Comparador;
import predecibles.patrones.Predecible;
import estructuras.conexiones.NodoLista;
import estructuras.operaciones.discriminantes.TipoDeSimilitud;
import estructuras.operaciones.operadores.LectorLineal;
import estructuras.operaciones.operadores.LectorParcial;

/**
 * 
 * @author VG
 * 
 * @param <TDato>
 *            tipo de dato de la lista que se va a recorrer.
 * @param <TModelo>
 *            el tipo del modelo con el que se van a comparar las listas.
 */
public class LectorHastaPrimeraCoincidencia<TDato extends Predecible<TDato>, TModelo extends Predecible<TModelo>>
		extends LectorLineal<NodoLista<TDato>, TDato> implements
		LectorParcial<NodoLista<TDato>, TDato> {

	protected TModelo modelo;
	protected Comparador<TDato, TModelo> criterio;
	protected TipoDeSimilitud tipo;

	protected NodoLista<TDato> nodoActual;
	protected NodoLista<TDato> principio;

	public LectorHastaPrimeraCoincidencia(TModelo modelo,
			Comparador<TDato, TModelo> criterioComparacion,
			TipoDeSimilitud tipoSimilitud,
			NodoLista<TDato> primerElementoDeBusqueda) {
		this.modelo = modelo;
		this.criterio = criterioComparacion;
		this.tipo = tipoSimilitud;
		this.principio = primerElementoDeBusqueda;
	}

	@Override
	public boolean revisarSecuenciaDeSalida() {
		if (finalizar)
			return true;
		if (!finalizar
				&& OpsPredecibles.sonSemejantes(nodoActual().getDato(), modelo,
						criterio, tipo)) {
			finalizar = true;
			return false;
		} else
			return false;
	}

	@Override
	public void next() {
		if (nodoActual != null)
			nodoActual = nodoActual.getSiguiente();
		else
			System.out
					.println("final de la lista, el lector sigue buscando...");
	}

	@Override
	public void start() {
		nodoActual = principio;
	}

	@Override
	public NodoLista<TDato> fin() {
		return null;
	}

	@Override
	public boolean criterioDeFin() {
		return revisarSecuenciaDeSalida();
	}

	@Override
	public NodoLista<TDato> nodoActual() {
		return nodoActual;
	}
}
