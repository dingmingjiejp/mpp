package lesson9.labs.prob4.partA;

import java.util.stream.Stream;

/* To begin, create a final variable Stream<Integer> primes that contains all prime
numbers (in particular, the Stream is infinite). Generate the primes using the iterate
method of Stream – do not use the map or filter Stream operations. */

public class PrimeStream {
	public static void main(String args[]) {
		final Stream<Integer> primes = Stream.iterate(2, r -> {
			int t = r + 1;
			while (true) {
				boolean isPrime = true;
				for (int i = 2; i < t; i++) {
					if (t % i == 0) {
						isPrime = false;
						break;
					}
				}

				if (isPrime) {
					return t;
				} else {
					t++;
				}
			}
		});
	}

}