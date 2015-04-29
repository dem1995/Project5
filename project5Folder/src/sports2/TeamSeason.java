package sports2;
import java.util.TreeSet;

import countryComponents.City;
import countryComponents.Person;
import countryComponents.PersonList;
import countryComponents.State;

public class TeamSeason {
	//Structural objects
	private SportsYear sportsYear;
	
	private String name;
	
	private Team team;
	
	private TreeSet<Person> players;
	
	private City city;
	
	private State state;
	
	public TeamSeason(String name, Team team, SportsYear sportsYear, City city, State state)
	{
		this.name=name;
		this.team=team;
		this.sportsYear=sportsYear;
		sportsYear.addSeason(this);
		team.addSeason(sportsYear,  this);
	}
	
	public SportsYear getSportsYear()
	{
		return sportsYear;
	}
	
	public TreeSet<Person> getPlayers()
	{
		return players;
	}
	
	void addPlayer(Person person)
	{
		players.add(person);
		sportsYear.makePersonUnavailable(person);
	}
	
	public String toString()
	{
		//TODO check what JList wants for String representation according to assignment
		return "Generic TeamSeason name";
	}
	
}
