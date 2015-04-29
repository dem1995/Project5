package sports;

import java.io.Serializable;
import java.util.ArrayList;

import countryComponents.Person;

public class SportsYear implements Serializable{
	int year;
	ArrayList<Person> usedPeople;
	ArrayList<Person> unusedPeople;

	public SportsYear(int theYear)
	{
		year=theYear;
	}
	
	public void removeThesePeopleFromUnusedPeopleList()
	{
		//TODO
	}
	
	public void addThesePeopleToUsedPeopleList()
	{
		//TODO
	}
	
	public int getYear()
	{
		return year;
	}
}
