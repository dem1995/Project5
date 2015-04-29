package countryComponents;

import java.util.Comparator;

/**
 *Compares two cities lexicographically first by city name, then by names of the states they're in
 */
public class CompareCityThenState implements Comparator<City> 
{

	public int compare(City city1, City city2) 
	{
		int cityComparison = city1.getName().compareToIgnoreCase(city2.getName());
		if (cityComparison!=0)
			return cityComparison;
		else
			return city1.getState().getName().compareToIgnoreCase(city2.getState().getName());
	}
}