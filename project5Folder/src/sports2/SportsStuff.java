package sports2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.TreeMap;
import java.util.TreeSet;

import countryComponents.City;
import countryComponents.Person;
import countryComponents.State;
import otherClasses.HelperMethods;
import MVCStuff.CountryModel;

public class SportsStuff {
	//Structural components
	private TreeMap<String, Team> teams;
	private TreeMap<Integer, SportsYear> sportsYears;
	
	//Items for access
	private TreeSet<TeamSeason> teamSeasons;
	
	/**
	 * Constructor for SportsStuff
	 */
	public SportsStuff()
	{}
	
	public TreeMap<String, Team> getTeams()
	{
		return teams;
	}
	
	public TreeMap<Integer, SportsYear> getYears()
	{
		return sportsYears;
	}
	
	public TreeSet<TeamSeason> getSeasons()
	{
		return teamSeasons;
	}
		
	//Adder methods
	
	/**
	 * Adds a team to the SportsStuff. Also adds the 
	 * @param team The team to be added
	 */
	public void addTeam(Team team)
	{
		teams.put(team.getID(), team);
		Iterator<TeamSeason> seasonsIterator=team.getSeasons().values().iterator();
		while(seasonsIterator.hasNext())
		{
			teamSeasons.add(seasonsIterator.next());
		}
		
		//Adds the years of this team to the SportsStuff
		Iterator<SportsYear> yearIterator=team.getYears().values().iterator();
		while (yearIterator.hasNext())
		{
			SportsYear year= yearIterator.next();
			sportsYears.put(year.getYear(), year);
		}
	}
	
	/**
	 * Adds a seasons to the SportsStuff.
	 * @param season
	 */
	void addSeason(TeamSeason season)
	{
		teamSeasons.add(season);		
	}
	
	
	public void prepareFromCSVUsingCountryModel(String fileName, CountryModel countryModel)
	{
		ArrayList<String> strings;
		try
		{
			strings=HelperMethods.convertCSVToStringList(fileName);
		}catch (Exception e)
		{
			System.out.println("Error reading in file");
			return;
		}
		
		if (strings!=null)
			prepareFromStringsUsingCountryModel(strings, countryModel);
		
	}
	
	private void prepareFromStringsUsingCountryModel(ArrayList<String> strings, CountryModel countryModel)
	{
		Iterator<String> stringIterator= strings.iterator();
		Team theTeam= new Team("");
		while(stringIterator.hasNext())
		{
			String[] curStrings=stringIterator.next().split(";");
			//Checks to see if the teamID is ready
			if (curStrings.length==0)
			{
				if (theTeam.getName()!="")
				{
					this.addTeam(theTeam);
				}
				theTeam= new Team(curStrings[0]);
			}
			else
			{
				addSeasonFromStringsToTeam(curStrings, theTeam, countryModel);
			}
		}		
			
		
	}
	
	private void addSeasonFromStringsToTeam(String[] teamStrings, Team theTeam, CountryModel countryModel)
	{
		Integer curYearInteger=new Integer(teamStrings[0].trim());
		if(sportsYears.get(curYearInteger)==null)
			sportsYears.put(curYearInteger, new SportsYear(curYearInteger));
		SportsYear sportsYear= sportsYears.get(curYearInteger);
		String seasonName=teamStrings[1].trim();
		State state= countryModel.findStateOrAdd(teamStrings[3].trim());
		City city=countryModel.findCityOrAdd(state, teamStrings[2].trim());
		
		//Readies the teamSeason
		TeamSeason theSeason= new TeamSeason(seasonName, theTeam, sportsYear, city, state);
		
		//Readies players to add if they are not found in the PersonList
		ArrayList<Person> peopleToAdd= new ArrayList<Person>();
		
		//Adds players to the teamSeason
		for (int i=0; i<teamStrings.length; i++)
		{
			String curString=teamStrings[i].trim();
			Person curPerson=countryModel.findPerson(curString);
			if (curPerson==null)
			{
				curPerson=new Person(curString);
				countryModel.addPersonWithoutTriggeringEvent(curPerson);
			}
			
			curPerson.AddSeason(theSeason);
			
		}
		//Updates the model
		countryModel.forceUpdate();
	}
	

}
