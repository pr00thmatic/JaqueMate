package predecibles.patrones;

import predecibles.operadores.Comparador;

public interface Ordenable<MiTipo> {

	public <TModelo> int comparar(TModelo otro,
			Comparador<MiTipo, TModelo> criterio);
}
