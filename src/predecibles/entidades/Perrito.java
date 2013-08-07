package predecibles.entidades;

import inf131.ListaSimple;
import inf131.operaciones.OpsLectores;
import inf131.operaciones.OpsListasPredecibles;

import java.util.Scanner;

import estructuras.operaciones.discriminantes.TipoDeSimilitud;

import predecibles.operadores.Comparador;
import predecibles.operadores.Condicionante;
import predecibles.operadores.Selector;
import predecibles.patrones.Predecible;
import unexpected.Fabrica;

public class Perrito implements Predecible<Perrito> {
	private String nombre;
	private int edad;

	public static void main(String[] args) {
		ListaSimple<Perrito> x = new ListaSimple<Perrito>(
				Perrito.fabricaDePerritos());
		x.leer();
		System.out.println(x.toString("\n->\n"));
		System.out.print("y el más viejo es: ");
		System.out.println(OpsLectores.cotaSuperior(x.getRecorredor(),
				porEdades()));
		System.out.print("y el más joven es: ");
		System.out.println(OpsLectores.cotaInferior(x.getRecorredor(),
				porEdades()));
		System.out.print("dime un número!");
		int edad = (new Scanner(System.in)).nextInt();
		System.out.println("Este es un perrito con esa edad: ");
		ListaSimple<Perrito> recipiente = new ListaSimple<Perrito>(
				Perrito.fabricaDePerritos());
		OpsLectores.encontrarTodos(x.getRecorredor(),
				new Selector<Perrito, Integer>(edad, porEdad(),
						TipoDeSimilitud.IGUAL), recipiente);
		System.out.println(recipiente.cima());
		System.out.println("por cierto, la estructura alberga "
				+ x.calcularTamano() + " perritos");
		System.out.print("ahora, dime otro número!");
		edad = (new Scanner(System.in)).nextInt();
		System.out.println(OpsLectores.contar(x.getRecorredor(),
				new Selector<Perrito, Integer>(edad, porEdad(),
						TipoDeSimilitud.IGUAL))
				+ " perritos en la estructura tienen esa edad");
		System.out
				.println("ahora voy a ordenar la estructura en orden de edades  (descendentemente)");
		OpsListasPredecibles.ordenarDescendentemente(x, porEdades());
		System.out.println(x.toString("\n->\n"));

		System.out
				.println("ahora voy a ordenarla por orden alfabético (descendentemente)");
		OpsListasPredecibles.ordenarDescendentemente(x, porNombres());
		System.out.println(x.toString("\n->\n"));

		System.out.println("y ahora, de nuevo por edad pero ascendentemente");
		OpsListasPredecibles.ordenarAscendentemente(x, porEdades());
		System.out.println(x.toString("\n->\n"));

		System.out.println("y ahora por orden alfabético...");
		OpsListasPredecibles.ordenarAscendentemente(x, porNombres());
		System.out.println(x.toString("\n->\n"));

		System.out.println("see you later, thinking being...");
	}

	public String toString() {
		return "~~~~PERRITO~~~~" + "\n\tNOMBRE: " + nombre + "\n\tEDAD: "
				+ edad;
	}

	public static Condicionante<Perrito> esCachorro() {
		return new Condicionante<Perrito>() {

			@Override
			public boolean condicion(Perrito otro) {
				return otro.edad < 2;
			}
		};
	}

	public static Condicionante<Perrito> esJoven() {
		return new Condicionante<Perrito>() {

			@Override
			public boolean condicion(Perrito otro) {
				return !otro.estado(esCachorro()) & !otro.estado(esAdulto())
						& !otro.estado(esViejo());
			}
		};
	}

	public static Condicionante<Perrito> esAdulto() {
		return new Condicionante<Perrito>() {

			@Override
			public boolean condicion(Perrito otro) {
				return !esViejo().condicion(otro) & otro.edad > 5;
			}
		};
	}

	public static Condicionante<Perrito> esViejo() {
		return new Condicionante<Perrito>() {

			@Override
			public boolean condicion(Perrito otro) {
				return otro.edad > 7;
			}
		};
	}

	public static Comparador<Perrito, String> porNombre() {
		return new Comparador<Perrito, String>() {

			@Override
			public int comparacion(Perrito a, String b) {
				return a.nombre.compareTo(b);
			}
		};
	}

	public static Comparador<Perrito, Perrito> porNombres() {
		return new Comparador<Perrito, Perrito>() {

			@Override
			public int comparacion(Perrito a, Perrito b) {
				return a.nombre.compareTo(b.nombre);
			}
		};
	}

	public static Comparador<Perrito, Integer> porEdad() {
		return new Comparador<Perrito, Integer>() {

			@Override
			public int comparacion(Perrito a, Integer b) {
				return a.edad - b;
			}
		};
	}

	public static Comparador<Perrito, Perrito> porEdades() {
		return new Comparador<Perrito, Perrito>() {

			@Override
			public int comparacion(Perrito a, Perrito b) {
				return a.edad - b.edad;
			}
		};
	}

	public static Comparador<Perrito, Perrito> porTodo() {
		return new Comparador<Perrito, Perrito>() {

			@Override
			public int comparacion(Perrito a, Perrito b) {
				int coefComparacion = 0;
				if (a.nombre != b.nombre)
					coefComparacion++;
				if (a.edad != b.edad)
					coefComparacion++;
				return coefComparacion;
			}
		};
	}

	@Override
	public void leer() {
		System.out.print("Cómo se llama el perrito? ");
		Scanner input = new Scanner(System.in);
		nombre = input.next();
		System.out.print("aawwwwww X3 y cuántos años tiene? ");
		edad = input.nextInt();
	}

	@Override
	public boolean estado(Condicionante<Perrito> criterio) {
		return criterio.condicion(this);
	}

	@Override
	public <TModelo> int comparar(TModelo otro,
			Comparador<Perrito, TModelo> criterio) {
		return criterio.comparacion(this, otro);
	}

	public static Fabrica<Perrito> fabricaDePerritos() {
		return new Fabrica<Perrito>() {

			@Override
			public Perrito crear() {
				return new Perrito();
			}
		};
	}

}
