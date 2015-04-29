package countryComponents;

import java.util.Comparator;

/**
 *Compares two Cities lexicographically first by the states they're in, then by their names
 */
public class CompareStateThenCity implements Comparator<City> 
{

	public int compare(City city1, City city2) 
	{
		int stateNameComparison=city1.getState().getName().compareToIgnoreCase(city2.getState().getName());
		if (stateNameComparison!=0)
			return stateNameComparison;
		else
			return city1.getName().compareToIgnoreCase(city2.getName());
	}
}
