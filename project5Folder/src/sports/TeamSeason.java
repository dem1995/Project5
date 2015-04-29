package sports;

import java.io.Serializable;

import countryComponents.DateFormatter;
import countryComponents.Person;
import countryComponents.PersonList;

public class TeamSeason implements Serializable {
	

	/**
	 * What year this season is
	 */
	SportsYear sportsYear;
	
	/**
	 * The name of the team this season
	 */
	String name;
	
	/**
	 * The ID of the team
	 */
	String ID;
	
	/**
	 * The players who played this season
	 */
	PersonList players;
	
	/**
	 * The city that the team was based in this season
	 */
	
	String cityName;
	/**
	 * The state that the team was based in this season
	 */
	String stateName;
	public TeamSeason(String name, String ID, SportsYear sportsYear)
	{
		this.name=name;
		this.ID=ID;
		this.sportsYear=sportsYear;
	}

	
	public PersonList getPlayers()
	{
		return players;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String toString()
	{
		return name+", "+sportsYear.getYear();
	}
	



	
	
	
	
}
