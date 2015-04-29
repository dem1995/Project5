package countryComponents;

import java.awt.geom.Point2D;
import java.io.Serializable;

/**
 * Project #4
 * CS 2334, Section 010
 * April 16, 2015
 * <p>
 * Holds a <code>PersonList</code> of people, emulating the population of a city
 * </p>
 * @version 2.0
 */
public class City implements Comparable<City>, Serializable {
	//Comparators
	
	/**
	 * Comparator that compares city names, then state names.
	 */
	public static CompareCityThenState compareCityThenState= new CompareCityThenState(); 
	
	/**
	 * Comparator that compares state names, then city names.
	 */
	public static CompareStateThenCity compareStateThenCity= new CompareStateThenCity();
	
	//Instance variables~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * Holds all of the people in this <code>City</code>
	 */
	private PersonList personList;
	
	/**
	 * The name of the city
	 */
	private String cityName;
	
	/**
	 * The State that the city's in
	 */
	private State theState;

	/**
	 * the latitude of this city
	 */
	private double latitude;
	/**
	 * the longitude of this city
	 */
	private double longitude;
	
	
	//Instance method
	/**
	 * Creates a <code>City</code> with the name <code>cityName</code> and an empty <code>PersonList</code>
	 * @param cityName The name of the city
	 * @param state The state the city's in.
	 */
	public City(String cityName, State state)
	{
		this.theState=state;
		this.cityName=cityName;
		this.personList=new PersonList();
	}
	
	//Accessor methods
	/**
	 * Retrieves <code>personList</code>
	 * @return A PersonList of Person objects in this City's PersonList personList
	 */
	public PersonList getPersonList()
	{
		return personList;
	}
	

	
	/**
	 * A getter method for cityName
	 * @return the name of the city
	 */
	public String getName()
	{
		return cityName;
	}
	
	/**
	 * A getter method for coordinates
	 * @return The geographic coordinates of this city
	 */
	public Point2D getCoordinate()
	{
		return new Point2D.Double(latitude, longitude);
	}
	
	/**
	 * @return the state that the city's in
	 */
	public State getState()
	{
		return theState;
	}
	
	//Setter methods
	
	/**
	 * Sets the State that this city is in
	 * @param state the state the city is in.
	 */
	public void setState(State state)
	{
		theState=state;
	}
	/**
	 * Adjusts the y-value of this city's geographic location
	 * @param latitude the y-value of this city's geographic location
	 */
	public void setLatitude(int latitude)
	{
		this.latitude = latitude;
	}
	/**
	 * Adjusts the x-value of this city's geographic location
	 * @param longitude the x-value of this city's geographic location
	 */
	public void setLongitude(int longitude)
	{
	    this.longitude = longitude;
	}
	
	/**
	 * Sets the latitude and longitude of this city
	 * @param latitude The latitude of this city
	 * @param longitude The longitude of this city
	 */
	public void setCoordinates(int latitude, int longitude)
	{
		this.latitude=latitude;
		this.longitude=longitude;		
	}
	
	//Adder methods

	/**
	 * Adds a person to this City. THE ONLY CLASS THAT SHOULD CALL THIS METHOD IS STATE
	 * @param person The person to be added
	 */
	void addPerson(Person person)
	{
		personList.addPerson(person);
	}
	

	
	//Interface-required methods
	public int compareTo(City city) {
		return this.getName().compareTo(city.getName());
	}
	

	public String toString()
	{
		if (this.getState()!=null)
			return this.getName()+", "+this.getState().getName();
		else
			return this.getName();
	}
	
}
