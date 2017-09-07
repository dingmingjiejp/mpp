package lesson3.labs.prob2;

import java.util.ArrayList;
import java.util.List;

public class LandLord {

	String name;
	List<Building> buildList=new ArrayList<Building>();
	
	public LandLord(String n)
	{
		name=n;
	}
	
	public void addBuilding(Building building)
	{
		buildList.add(building);
	}
	public int getProfit()
	{
		int sum=0;
		for (Building building:buildList)
			sum+=building.getProfit();

		return sum;
	}
}
