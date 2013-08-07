package predecibles.entidades;

import java.util.Scanner;

import predecibles.operadores.Comparador;
import predecibles.operadores.Condicionante;
import predecibles.patrones.Predecible;
import unexpected.Fabrica;

public class Entero implements Predecible<Entero> {
	public int valor;

	public Entero(int unNumero) {
		valor = unNumero;
	}

	public Entero() {
	}

	@Override
	public <TModelo> int comparar(TModelo otro,
			Comparador<Entero, TModelo> criterio) {
		return criterio.comparacion(this, otro);
	}

	@Override
	public void leer() {
		System.out.print("ingrese un número entero: ");
		Scanner input = new Scanner(System.in);
		valor = input.nextInt();
	}

	@Override
	public boolean estado(Condicionante<Entero> criterio) {
		return criterio.condicion(this);
	}

	public static Fabrica<Entero> fabricaDeNumeros() {
		return new Fabrica<Entero>() {

			@Override
			public Entero crear() {
				return new Entero();
			}

		};
	}

	public static Comparador<Entero, Entero> porValor() {
		return new Comparador<Entero, Entero>() {

			@Override
			public int comparacion(Entero a, Entero b) {
				return a.valor - b.valor;
			}
		};
	}

}
