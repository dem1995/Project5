package degreesOfSeparationClasses;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.TreeSet;

import sports.TeamSeason;
import countryComponents.Person;

/**
 * A superclass for classes that handle degrees of separation between two players 
 */
public class AbstractDegreesOfSeparationClass {

	/**
	 * The size of the smallest current shortest Degrees of Separation path
	 */
	protected int minSize=0;

	/**
	 * A deque to be added to/subtracted from when building degrees of separation routes
	 */
	protected ArrayDeque<Person> journey= new ArrayDeque<Person>();
	
	/**
	 * The path through the teamSeasons taken along the Degrees of Separation path
	 */
	protected ArrayDeque<TeamSeason> seasonJourney= new ArrayDeque<TeamSeason>();

	/**
	 * The paths of shortest degrees of separation
	 */
	protected ArrayList<ArrayList<Person>> journeys= new ArrayList<ArrayList<Person>>();

	/**
	 * The people who are already part of the current journey
	 */
	protected TreeSet<Person> checkedPersons= new TreeSet<Person>();
	
	/**
	 * The TeamSeasons that have already been checked for the getDegrees method
	 */
	protected TreeSet<TeamSeason> checkedSeasons= new TreeSet<TeamSeason>();

	/**
	 * What level of recursion the program is currently at.
	 */
	protected int count=0;
	
	public int getMinSize()
	{
		return minSize;
	}

	public ArrayDeque<Person> getJourney()
	{
		return journey;
	}
	
	public ArrayList<ArrayList<Person>> getJourneys()
	{
		return journeys;
	}
	
}
