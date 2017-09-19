package lesson7.labs.prob1.partE2;

public interface IfaceC extends IfaceA {
	default int method(int x) {
		return 2 * x;
	}
}
