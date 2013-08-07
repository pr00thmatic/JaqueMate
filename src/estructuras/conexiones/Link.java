package estructuras.conexiones;

public class Link<TNodo extends Nodo<?>> {
	private TNodo nodoPadre;
	private Link<TNodo> link;

	public Link(TNodo elNodoPadre) {
		nodoPadre = elNodoPadre;
		link = null;
	}

	public void conectar(Link<TNodo> otro) {
		link = otro;
	}

	public TNodo getDestino(int grado) {
		Link<TNodo> response = this;
		for (int i = 0; i < grado; i++) {
			response = response.link;
		}
		try {
			return response.nodoPadre;
		} catch (NullPointerException e) {
			return null;
		}
	}

	public TNodo getDestino() {
		return getDestino(1);
	}

	public void desconectar() {
		link = null;
	}

	public boolean hasDestino(int grado) {
		return getDestino(grado) != null;
	}

	public boolean hasDestino() {
		return getDestino() != null;
	}
}
