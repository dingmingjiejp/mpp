package lesson7.labs.prob1.partE1;

public interface IfaceD extends IfaceB, IfaceC {
	//implementation or redeclaration required
	@Override
	default int method(int x) {
		// TODO Auto-generated method stub
		return IfaceB.super.method(x);
	}
	
//	abstract int method(int x);
	
}
	

