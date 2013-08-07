package predecibles.operadores;

import estructuras.operaciones.discriminantes.TipoDeSimilitud;
import predecibles.patrones.Predecible;

/**
 * Un objeto que permite seleccionar o descartar entidades Predecibles que
 * cumplan una condición al ser comparados con algún modelo.
 * 
 * El selector surge porque muchas operaciones necesitan comparar muchos datos
 * distintos a un modelo que no cambia, bajo cierto criterio de comparación y
 * bajo cierto tipo de similitud. Entonces, para hacer más fácil la construcción
 * de estas operaciones, en vez de molestar al usuario siempre con un
 * "modelo, criterioComparacion, tipoSimilitud" (argumentos que suelen tornarse
 * confusos para un programador con la mente opacada por el sueño), se le pide
 * un objeto selector, para que el usuario sepa que estos tres argumentos
 * (modelo, criterio y tipo) serán usados con fines de selección de objetos.
 * 
 * @author VG
 * 
 * @param <TDato>
 *            el tipo de dato principal. Debe ser un tipo de dato predecible.
 * @param <TModelo>
 *            el tipo de dato del modelo. No tiene boundles (ataduras), por lo
 *            que puedes elegir cualquier tipo de datos (como Integer, String,
 *            etc...)
 */
public class Selector<TDato extends Predecible<TDato>, TModelo> {
	private TModelo modelo;
	private Comparador<TDato, TModelo> criterio;
	private TipoDeSimilitud tipoSimilitud;

	/**
	 * Un selector sirve para seleccionar elementos que cumplan alguna condición
	 * bajo determinado criterio al ser comparados con un modelo
	 * 
	 * @param modeloDeSeleccion
	 *            el modelo con el que se compararán los datos que el Selector
	 *            reciba.
	 * @param criterioComparacion
	 *            es un Comparador, que es una interfaz. Es un objeto que provee
	 *            una forma de comparar al dato (TDato: Predecible<TDato>) con
	 *            el modelo (TModelo).
	 * @param tipoSimilitud
	 *            toda comparación recopilada mediante la interfaz del
	 *            Comparador, tiene tres opciones: mayor que (MAYOR), menor que
	 *            (MENOR) o igual que (IGUAL), todas estas enumeraciones se
	 *            encuentran en TipoDeSimilitud).
	 */
	public Selector(TModelo modeloDeSeleccion,
			Comparador<TDato, TModelo> criterioComparacion,
			TipoDeSimilitud tipoSimilitud) {

		this.modelo = modeloDeSeleccion;
		this.criterio = criterioComparacion;
		this.tipoSimilitud = tipoSimilitud;
	}

	public TModelo getModelo() {
		return modelo;
	}

	public void setModelo(TModelo modeloNuevo) {
		modelo = modeloNuevo;
	}

	public void setTipoDeSimilitud(TipoDeSimilitud nuevoTipoSimilitud) {
		tipoSimilitud = nuevoTipoSimilitud;
	}

	/**
	 * Decide si un dato selecciona o no, de acuerdo al critero de comparación y
	 * al modelo con el que fue creado inicialmente el Selector.
	 * 
	 * @param aUnDato
	 * @return
	 */
	public boolean selecciona(TDato aUnDato) {
		int resComparacion = criterio.comparacion(aUnDato, modelo);
		switch (tipoSimilitud) {
		case IGUAL:
			return resComparacion == 0;
		case MAYOR:
			return resComparacion > 0;
		case MENOR:
			return resComparacion < 0;
		default:
			throw new NoSuchMethodError();
		}
	}
}
