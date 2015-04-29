package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import countryComponents.City;
import countryComponents.State;

public class StateTest {

	@Test
	public void test() {
		State state=new State("Missouri");z
		City city= new City("Town and Country", state);
		City city2=new City("St. Louis City", state);
		City city3=new City("Ballwin", state);
		state.addCity(city);
		state.addCity(city2);
		state.addCity(city3);
		assertEquals(state.findCityOrAdd("Town and Country").getName(), "Town and Country");
	}

}
