package sports;

import java.util.TreeMap;

/**
 * An object that holds all the seasons of team.
 */
public class Team {
	
	//Structural objects	
	/**
	 * A three-letter name that represents all the seasons this team has, regardless of what those seasons' actual full names were.
	 */
	private String ID;
	
	/**
	 * The seasons this team has
	 */
	private TreeMap<SportsYear, TeamSeason> seasons= new TreeMap<SportsYear, TeamSeason>();
	
	//Reference objects
	/**
	 * References to all the years this team's seasons occurred in.
	 */
	private TreeMap<Integer, SportsYear> years= new TreeMap<Integer, SportsYear>();
	
	
	//Constructor methods
	/**
	 * Constructs a team given an ID (a generic three-letter name that represents all the seasons this team has, regardless of what those seasons' actual full names were).
	 * @param ID The team's three-letter ID.
	 */
	public Team(String ID){
		this.ID=ID;
	}
	
	//Getter methods
	/**
	 * @return the three-letter name that represents all the seasons this team has, regardless of what those seasons' actual full names were.
	 */
	public String getID()
	{
		return ID;
	}
	
	/**
	 * Returns a season of this team when provided with the integer key for the SportsYear they played in.
 	 * @param year The integer representation of the key value for the team that this year played (the integer year this team played in).
	 * @return The season that occurred during the year provided in the parameter to this method, or <code>null</code> if that season doesn't exist.
	 */
	public TeamSeason getSeason(int year)
	{
		return seasons.get(years.get(new Integer(year)));
	}
	
	public TreeMap<SportsYear, TeamSeason> getSeasons()
	{
		return seasons;
	}
	
	public String getName()
	{
		return ID;
	}
	
	public TreeMap<Integer, SportsYear> getYears()
	{
		return years;
	}
	
	//Adder methods
	/**
	 * Adds a season to this team. Additionally, adds the year this team played in to the years of this team.
	 * @param sportsYear 
	 * @param teamSeason
	 */
	void addSeason(SportsYear sportsYear, TeamSeason teamSeason)
	{
		seasons.put(sportsYear, teamSeason);
		years.put(sportsYear.getYear(), sportsYear);
	}
}
