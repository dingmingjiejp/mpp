package lesson3.labs.prob2;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Building b1=new Building("Building1",800);
		b1.addApartMent("apartment1", 400);
		b1.addApartMent("apartment2", 300);
		b1.addApartMent("apartment3", 600);
		
		Building b2=new Building("Building2",700);
		b2.addApartMent("apartment1", 300);
		b2.addApartMent("apartment2", 500);
		b2.addApartMent("apartment3", 900);
		
		LandLord tom=new LandLord("Tom");
		tom.addBuilding(b1);
		tom.addBuilding(b2);
		
		System.out.println("landlord "+tom.name+" profit="+tom.getProfit());
		
	}

}
