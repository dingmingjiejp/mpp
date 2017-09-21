package lesson9.labs.prob9;

import java.util.stream.IntStream;

public class Print {

	public static void printSquares(int num) {
		IntStream streams = IntStream.iterate(1, n -> ((int) Math.pow(Math.sqrt(n) + 1, 2))).limit(num);
		streams.forEach(System.out::println);
	}

	public static void main(String args[]) {
		Print.printSquares(10);
	}

}
