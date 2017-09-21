package lesson9.labs.prob4.partB;

import java.util.stream.Stream;

public class PrimeStream {
	
	public static void main(String[] args) {
		PrimeStream ps = new PrimeStream(); //PrimeStream is enclosing class
		ps.printFirstNPrimes(10);
		System.out.println("====");
		ps.printFirstNPrimes(5);
	}
	
	public void printFirstNPrimes(int firstN) {
		Stream<Integer> primes = Stream.iterate(2, r -> {
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
		}).limit(firstN);
		
		primes.forEach(System.out::println);
	}

}