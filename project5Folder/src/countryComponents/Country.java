package countryComponents;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

import sports.SportsStuff;
import sports.Team;
import sports.TeamSeason;

/**
 * Project #4
 * CS 2334, Section 010
 * April 16, 2015
 * <p>
 *Holds a bunch of <code>State</code> objects
 *</p>
 *@version 2.0
 */
public class Country implements Serializable {

	//Instance variables
	/**
	 * The <code>State</code>s contained by this <code>Country</code>
	 */
	private ArrayList<State> states;
	
	private ArrayList<City> allTheCities= new ArrayList<City>();
	
	private ArrayList<Person> allThePeople= new ArrayList<Person>();
	
	private SportsStuff sportsStuff= new SportsStuff();
	
	/**
	 * Lets you know if this Country's treeMap of people (basically a quick way to search for people if you don't know their city or state) needs to be updated due to changes to this Country.
	 */
	protected boolean treeMapNeedsBuilding=true;
	
	/**
	 * A treemap of players. Used to assign birthdates to players
	 */
	private TreeMap<String, Person> treeMap=new TreeMap<String, Person>();
	
	//Instance methods	
	/**
	 * Constructs a <code>Country</code> with an empty <code>ArrayList{@literal <}State{@literal >}</code>
	 */
	public Country() {
		this.states=new ArrayList<State>();
	}

	/**
	 * Constructs a <code>Country</code> with an <code>ArrayList{@literal <}State{@literal >}</code> that is a copy of <code>states</code>
	 * @param states The States you want this Country to hold
	 */
	public Country(ArrayList<State> states) {
		this.states = states;
	}
	
	//Accessor Methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * @return this <code>country</code>'s states
	 */
	/**
	 * Returns all the states in this country. THE RETURNED OBJECT SHOULD NOT HAVE ITS CONTENTS MODIFIED DIRECTLY
	 * @return all the states in this country.
	 */
	public ArrayList<State> getStates()
	{
		return this.states;
	}
	
	/**
	 * Returns all the cities in this country. THE RETURNED OBJECT SHOULD NOT HAVE ITS CONTENTS MODIFIED DIRECTLY
	 * @return all the cities in this country.
	 */
	public ArrayList<City> getCities()
	{
		return allTheCities;
	}
	
	/**
	 * Returns all the people in this country. THE RETURNED OBJECT SHOULD NOT HAVE ITS CONTENTS MODIFIED DIRECTLY
	 * @return all the people in this country.
	 */
	public ArrayList<Person> getPeople()
	{
		return allThePeople;
	}
	

	public SportsStuff getSportsStuff()
	{
		return sportsStuff;
	}
	

	//Search methods

	
	/**
	 * Method for finding a city.
	 * @param state the State the City to be found is in
	 * @param name The city to be found.
	 * @return the found city with the given name in the given state. Returns null if there are no matches.
	 */
	public City findCity(State state, String name)
	{
		return state.findCity(name);
	}
	
	/**
	 * Finds and returns a <code>State</code> matching the <code>name</code> without adding any extra states
	 * @param name The name of the State to be found
	 * @return The State that was found
	 */
	public State findState(String name)
	{
		for (State state: states)
		{
			if (state.getName().equalsIgnoreCase(name))
				return state;
		}
		return null;
	}
	

	
	/**
	 * Searches this Country for a Person. Only to be used if the State and City of the Person are unknown
	 * @param personName The name of the Person this method is searching for
	 * @return A Person with the name given via this method's parameters
	 */
	public Person findPerson(String personName)
	{
		if(treeMapNeedsBuilding)
		{
			for (int i=0; i<allThePeople.size(); i++)
			{
				Person tempPerson=allThePeople.get(i);
				treeMap.put(tempPerson.getFullName(), tempPerson);
			}
		}
		treeMapNeedsBuilding=false;
		return (treeMap.get(personName));
	}
	
	//Adder methods
	
	
	/**
	 * Method for adding a person to this country
	 * @param aPerson The person to be added
	 */
	protected void addPerson(Person aPerson)
	{
		if(aPerson.getCityName()=="")
			aPerson.setCityName("Unknown");
		if(aPerson.getStateName()=="")
			aPerson.setStateName("Unknown");
		State foundState=findStateOrAdd(aPerson.getStateName());
		City foundCity= findCityOrAdd(foundState, aPerson.getCityName());
		foundState.addPerson(aPerson);
		allThePeople.add(aPerson);
		//allTheCities.add(foundCity);
		treeMapNeedsBuilding=true;
		Collections.sort(allThePeople);
	}
	
	
	
	protected void addPerson(City city, Person aPerson)
	{
		aPerson.setStateName(city.getState().getName());
		aPerson.setCityName(city.getName());
		addPerson(aPerson);
		treeMapNeedsBuilding=true;
		Collections.sort(allThePeople);
	}
	
	/**
	 * Adds a given City to a given State and sets that State as the city's state
	 * @param state The state to add the city to
	 * @param city The city to add
	 */
	protected void addCity(State state, City city)
	{
		city.setState(state);
		state.addCity(city);
		allTheCities.add(city);
		Collections.sort(allTheCities, City.compareStateThenCity);
	}
	
	/**
	 * Adds a <code>State</code> to <code>states</code>
	 * @param state The State to be added to states
	 */
	protected void addState(State state)
	{
		states.add(state);
		Collections.sort(states);
	}
	
	/**
	 * Finds and returns a <code>State</code> matching the <code>name</code>. Calls this.addState(name) and returns a new State(name) if no matches are found.
	 * @param name The name of the State to be found
	 * @return The State that was found
	 */
	protected State findStateOrAdd(String name)
	{
		for (State state: states)
		{
			//System.out.println(name+" "+state.getName());
			//System.out.println(name.equalsIgnoreCase(state.getName()));
			if (state.getName().equalsIgnoreCase(name))
				return state;
		}
		State newState=new State(name);
		states.add(newState);
		return newState;
	}
	
	protected void addTeam(String ID, Team team)
	{
		sportsStuff.addTeam(ID, team);
	}
	
	protected void addTeamSeason(String ID, TeamSeason teamSeason, int year)
	{
		sportsStuff.addTeamSeason(ID, teamSeason, year);
	}
	
	/**
	 * Finds and returns a City matching the name. Calls this.addCity(state, city) and returns a new City(name) if no matches are found.
	 * @param state the state the city will be in
	 * @param name the city to add the state to
	 * @return The found city, or a new city with the given name.
	 */
	protected City findCityOrAdd(State state, String name)
	{
		ArrayList<City> cities = state.getCities();
		
		for (int i=0; i<cities.size(); i++)
		{
			if (cities.get(i).getName().equalsIgnoreCase(name))
				return cities.get(i);
		}
		City newCity = new City(name, state);
		this.addCity(state, newCity);
		return newCity;			
	}
	
	protected void setSportsStuff(SportsStuff sportsStuff)
	{
		this.sportsStuff=sportsStuff;
	}
	
	
	/**
	 * Removes a person from this country
	 * @param person
	 */
	protected void removePerson(Person person)
	{
		Collections.sort(allThePeople);
		int location=Collections.binarySearch(allThePeople, person);
		allThePeople.remove(location);
		//treeMap.remove(person);
		treeMapNeedsBuilding=true;
		City theCity=findCityOrAdd(findStateOrAdd(person.getStateName()),person.getCityName());
		theCity.getPersonList().removePerson(person);
	}
	
	/**
	 * Deletes a city and all the people inside it from this country
	 * @param city
	 */
	protected void removeCity(City city)
	{
		allTheCities.remove(city);
		city.getState().removeCity(city);
		ArrayList<Person> cityPeople=city.getPersonList().getPeople();
		for (int i=0; i<cityPeople.size(); i++)
		{
			this.removePerson(cityPeople.get(i));
		}
	}

	
	
	/**
	 * @return The Country as an arrayList of Person objects
	 */
	public PersonList toPersonList()
	{
		PersonList tempList=new PersonList();
		for (State s: states)
			for (City c: s.getCities())
				for (Person p: c.getPersonList().getPeople())
					tempList.addPerson(p);
		return tempList;
	}
	
	//Other methods ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	

	public String toString()
	{
		return toPersonList().toString();
	}
	
	

	

}