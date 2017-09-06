package lesson3.labs.prob4;

public class Admin {
	public static void main(String args[]) {
		Condo c = new Condo(3);
		House h = new House(2.5);
		Trailer t = new Trailer();
		Object[] properties = {c, h, t};
		System.out.println(computeTotalRent(properties));
	}

	public static double computeTotalRent(Object[] properties) {
		double totalRent = 0;
		for (Object o : properties) {
			if (o instanceof Building) {
				Building b = (Building) o;
				totalRent += b.computeRent();
			}
		}
		/*for (Object o : properties) {
			if (o instanceof House) {
				House h = (House) o;
				totalRent += h.computeRent();
			}
			else if (o instanceof Condo) {
				Condo h = (Condo) o;
				totalRent += h.computeRent();
			}
			else if (o instanceof Trailer) {
				Trailer h = (Trailer) o;
				totalRent += h.computeRent();
			}
		}*/
		return totalRent;
	}
}
