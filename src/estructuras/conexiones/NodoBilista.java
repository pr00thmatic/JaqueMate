package estructuras.conexiones;

import java.util.NoSuchElementException;

public class NodoBilista<TDato> extends NodoLista<TDato> {
	private Link<NodoBilista<TDato>> linkAlAnterior;

	public void conectarAntes(NodoBilista<TDato> elAnterior) {
		linkAlAnterior.conectar(elAnterior.linkAlAnterior);
	}

	public NodoBilista<TDato> getAnterior() {
		return linkAlAnterior.getDestino();
	}

	public NodoBilista<TDato> getAnterior(int grado) {
		return linkAlAnterior.getDestino(grado);
	}

	@Override
	public NodoBilista<TDato> getSiguiente() {
		return (NodoBilista<TDato>) super.getSiguiente();
	}

	public NodoBilista<TDato> eliminarSiguiente() {
		super.eliminarSiguiente();
		NodoBilista<TDato> eliminado = linkAlAnterior.getDestino();
		if (eliminado == null)
			throw new NoSuchElementException();
		conectar(eliminado.getAnterior());
		return eliminado;
	}
}
