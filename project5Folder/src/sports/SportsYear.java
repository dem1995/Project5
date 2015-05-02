package sports;
import java.io.Serializable;
import java.util.Iterator;
import java.util.TreeSet;

import countryComponents.Person;

public class SportsYear implements Comparable<SportsYear>, Serializable {

	//Structural items
	/**
	 * The integer representation of the year this SportsYear is representing
	 */
	private int year;
	
	/**
	 * The players already on teams during this sportsYear
	 */
	private TreeSet<Person> usedPlayers= new TreeSet<Person>();
	
	/**
	 * The players not already on teams during this sportsYear
	 */
	private TreeSet<Person> unusedPlayers= new TreeSet<Person>();
	
	//Access items
	private TreeSet<TeamSeason> teamSeasons= new TreeSet<TeamSeason>();
	
	/**
	 * Constructs a SportsYear to represent the given year
	 * @param theYear The integer value of the year to be represented
	 */
	public SportsYear(int theYear)
	{
		year=theYear;
	}
	
	
	//Getter methods
	/**
	 * Getter method for the integer representation of the year this SportsYear is representing
	 * @return The integer representation of the year this SportsYear is representing
	 */
	public int getYear()
	{
		return year;
	}
	
	
	/**
	 * Getter method for the seasons that went on during this year
	 * @return
	 */
	public TreeSet<TeamSeason> getSeasons()
	{
		return teamSeasons;
	}
	
	//Adder methods
	/**
	 * Adds a season to this and makes the players in that season unavailable for use (unavailable to be added to other teamSeasons for this year without first removing them from the teamSeason being added). 
	 * @param season the season to be added
	 */
	void addSeason(TeamSeason season)
	{
		teamSeasons.add(season);
		
		Iterator<Person> personIterator=season.getPlayers().descendingIterator();
		while (personIterator.hasNext())
		{
			makePersonUnavailable(personIterator.next());
		}
	}

	
	//Other methods
	/**
	 * Marks a person as not being on a team for this year.
	 * @param person The person to be marked as not on a team for this year.
	 */
	void makePersonAvailable(Person person)
	{
		usedPlayers.remove(person);
		unusedPlayers.add(person);
	}
	
	/**
	 * Marks a person as already being on a team for this year.
	 * @param person The person to be marked as on a team for this year.
	 */
	void makePersonUnavailable(Person person)
	{
		usedPlayers.add(person);
		unusedPlayers.remove(person);
	}

	public String toString()
	{
		return year+"";
	}

	@Override
	public int compareTo(SportsYear o) {
		return (this.getYear()-o.getYear());
	}
}
