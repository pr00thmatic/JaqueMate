package predecibles.patrones;

import predecibles.operadores.Condicionante;

public interface Predecible<MiTipo> extends Ordenable<MiTipo> {
	public void leer();

	@Override
	public String toString();

	public boolean estado(Condicionante<MiTipo> criterio);
}
