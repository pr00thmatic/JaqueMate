package inf131;

import java.util.Scanner;

import unexpected.Fabrica;

import estructuras.Cola;
import estructuras.conexiones.NodoBilista;

public class ListaCircularDoblementeEnlazada<TDato> extends
		Cola<TDato, NodoBilista<TDato>> {

	public static void main(String[] args) {
		ListaCircularDoblementeEnlazada<Integer> x = new ListaCircularDoblementeEnlazada<Integer>();
		Scanner input = new Scanner(System.in);
		String sw = "no";
		while (!sw.equals("exit")) {
			System.out.print("push: ");
			x.agregar(input.nextInt());
			System.out.print("escriba 'exit' para finalizar: ");
			sw = input.next();
		}
		System.out.println("la estructura: " + x);
		System.out.println("y de nuevo, la estructura: " + x);
		System.out.println("el tamano de la estructura es: "
				+ x.calcularTamano());
		System.out.println("pop! " + x.eliminar() + " tamano: "
				+ x.calcularTamano());
		System.out.println("pop! " + x.eliminar() + " tamano: "
				+ x.calcularTamano());
		System.out.println("pop! " + x.eliminar() + " tamano: "
				+ x.calcularTamano());
		System.out.println("pop! " + x.eliminar() + " tamano: "
				+ x.calcularTamano());
		System.out.println("pop! " + x.eliminar() + " tamano: "
				+ x.calcularTamano());
		System.out.println("pop! " + x.eliminar() + " tamano: "
				+ x.calcularTamano());
		System.out.println("bye~");
	}

	public ListaCircularDoblementeEnlazada() {
		super(new Fabrica<NodoBilista<TDato>>() {

			@Override
			public NodoBilista<TDato> crear() {
				return new NodoBilista<TDato>();
			}
		});
	}

	public String toString() {
		return toString(" <-> ");
	}

}
