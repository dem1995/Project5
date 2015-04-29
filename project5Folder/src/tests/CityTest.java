package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import countryComponents.City;
import countryComponents.State;

public class CityTest {

	@Test
	public void test() {
		City city= new City("Los Angeles", new State("H"));
		City city2=new City("St. Louis", new State("I"));
		int firstComparison=city.compareTo(city2);
		assertEquals(firstComparison/Math.abs(firstComparison), -1);
		int oppositeComparison=city2.compareTo(city);
		assertEquals(oppositeComparison/Math.abs(oppositeComparison), 1);
		int equalsComparison=city.compareTo(city);
		assertEquals(equalsComparison, 0);
	}
}
