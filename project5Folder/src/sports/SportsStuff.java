package sports;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.TreeMap;
import java.util.TreeSet;

import countryComponents.City;
import countryComponents.Person;
import countryComponents.State;
import otherClasses.HelperMethods;
import MVCStuff.CountryModel;

public class SportsStuff implements Serializable{
	//Structural components
	private TreeMap<String, Team> teams= new TreeMap<String, Team>();
	private TreeMap<Integer, SportsYear> sportsYears= new TreeMap<Integer, SportsYear>();
	
	//Items for access
	private TreeSet<TeamSeason> teamSeasons= new TreeSet<TeamSeason>();
	
	/**
	 * Constructor for SportsStuff
	 */
	public SportsStuff()
	{
		Calendar calendar= Calendar.getInstance();
		int endYear=calendar.get(Calendar.YEAR);
		for (int i=1946; i<endYear+1; i++)
		{
			sportsYears.put(new Integer(i), new SportsYear(i));
		}
	}
	
	public TreeMap<String, Team> getTeams()
	{
		return teams;
	}
	
	public TreeMap<Integer, SportsYear> getYears()
	{
		return sportsYears;
	}
	
	public TreeSet<TeamSeason> getTeamSeasons()
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
	
	public void removeTeam(Team team)
	{
		ArrayList<TeamSeason> teamSeasonsForRemoving= new ArrayList<TeamSeason>(team.getSeasons().values());
		for (int i=0; i<teamSeasonsForRemoving.size(); i++)
		{
			this.teamSeasons.remove(teamSeasonsForRemoving.get(i));
			ArrayList<Person> peopleToRemoveSeasonFrom=new ArrayList<Person>(teamSeasonsForRemoving.get(i).getPlayers());
			for (int j=0; j<peopleToRemoveSeasonFrom.size(); j++)
			{
				peopleToRemoveSeasonFrom.get(j).getTeamSeasons().remove(teamSeasonsForRemoving.get(i));
			}
		}
		this.getTeams().remove(team.getID());
	}
	
	/**
	 * Adds a seasons to the SportsStuff.
	 * @param season
	 */
	public void addTeamSeason(TeamSeason season)
	{
		teamSeasons.add(season);		
	}
	
	
	public static void prepareFromCSVUsingCountryModel(String fileName, CountryModel countryModel)
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
	
	private static void prepareFromStringsUsingCountryModel(ArrayList<String> strings, CountryModel countryModel)
	{
		char splitter=';';
		Iterator<String> stringIterator= strings.iterator();
		Team theTeam= new Team("");
		while(stringIterator.hasNext())
		{
			String nextString=stringIterator.next().trim();
			if(nextString.startsWith("sep="))
			{
				splitter=nextString.charAt(nextString.length()-1);
				System.out.println(splitter);
			}
			else
			{
				String[] curStrings=nextString.split(""+splitter);
				//Checks to see if first line is defining how the csv file is split
				//Checks to see if the teamID is the thing to prepare
				if (!curStrings[0].matches("\\d+"))
				{
					if (theTeam.getName()!="")
					{
						countryModel.getSportsStuff().addTeam(theTeam);
					}
					theTeam= new Team(curStrings[0]);
					countryModel.addTeam(theTeam);
				}
				else
				{
					addSeasonFromStringsToTeam(curStrings, theTeam, countryModel);
				}
			}
		}
		//Updates the model
		countryModel.forceUpdate();

	}		



	
	private static void addSeasonFromStringsToTeam(String[] teamStrings, Team theTeam, CountryModel countryModel)
	{
		Integer curYearInteger=new Integer(teamStrings[0].trim());
		if(countryModel.getSportsStuff().getYears().get(curYearInteger)==null)
			countryModel.getSportsStuff().getYears().put(curYearInteger, new SportsYear(curYearInteger));
		SportsYear sportsYear= countryModel.getSportsStuff().getYears().get(curYearInteger);
		String seasonName=teamStrings[1].trim();
		State state= countryModel.findStateOrAdd(teamStrings[3].trim());
		City city=countryModel.findCityOrAdd(state, teamStrings[2].trim());
		
		//Readies the teamSeason
		TeamSeason theSeason= new TeamSeason(seasonName, theTeam, sportsYear, city, state);
		
		
		//Adds players to the teamSeason
		for (int i=4; i<teamStrings.length; i++)
		{
			String curString=teamStrings[i].trim();
			Person curPerson=countryModel.findPerson(curString);
			if (curPerson==null)
			{
				curPerson=new Person(curString);
				countryModel.addPersonWithoutTriggeringEvent(curPerson);
			}
			theSeason.addPlayer(curPerson);
			
			curPerson.addSeason(theSeason);
			
		}
		countryModel.addTeamSeason(theSeason);

	}
	

}
