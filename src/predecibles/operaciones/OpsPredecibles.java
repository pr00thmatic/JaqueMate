package predecibles.operaciones;

import estructuras.operaciones.discriminantes.TipoDeSimilitud;
import predecibles.operadores.Comparador;
import predecibles.patrones.Predecible;

public class OpsPredecibles {
	public static <TDato extends Predecible<TDato>, TSecundario> boolean sonSemejantes(
			TDato uno, TSecundario otro,
			Comparador<TDato, TSecundario> criterio,
			TipoDeSimilitud tipoSimilitud) {
		switch (tipoSimilitud) {
		case IGUAL:
			return criterio.comparacion(uno, otro) == 0;
		case MAYOR:
			return criterio.comparacion(uno, otro) > 0;
		case MENOR:
			return criterio.comparacion(uno, otro) < 0;
		default:
			throw new NoSuchMethodError();
		}
	}
}
