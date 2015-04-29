package sports;
import java.util.TreeSet;

import countryComponents.City;
import countryComponents.Person;
import countryComponents.PersonList;
import countryComponents.State;

public class TeamSeason implements Comparable<TeamSeason>{
	//Structural objects
	private SportsYear sportsYear;
	
	private String name;
	
	private Team team;
	
	private TreeSet<Person> players= new TreeSet<Person>();
	
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
		return sportsYear.getYear()+" "+name;
	}

	@Override
	public int compareTo(TeamSeason otherTeamSeason) {
		return this.toString().compareTo(otherTeamSeason.toString());
	}
	
}
