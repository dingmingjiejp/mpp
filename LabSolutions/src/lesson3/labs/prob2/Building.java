package lesson3.labs.prob2;

import java.util.ArrayList;
import java.util.List;

public class Building {

	String name;
	int cost;
	
	List<Apartment> apartmentList=new ArrayList<Apartment>();
	
	public Building(String n, int c)
	{
		name=n;
		cost=c;
	}
	
	public void addApartMent(String name, int rent)
	{
		apartmentList.add(new Apartment(name,rent));
	}
	
	public int getProfit()
	{
		int sum=0;
		for (Apartment apartment:apartmentList)
			sum+=apartment.rent;

		return sum-cost;
	}
	
}
