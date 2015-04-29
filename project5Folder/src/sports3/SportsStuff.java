package sports3;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import MVCStuff.CountryModel;
import otherClasses.HelperMethods;
import countryComponents.DateFormatter;
import countryComponents.Person;
import countryComponents.PersonList;

public class SportsStuff implements Serializable{
	
	
	
	/**
	 * A HashMap where the keys are String team IDs and the values are Teams
	 */
	TreeMap<String, Team> teams;
	
	
	/**
	 *All of the teamSeasons (for rendering to JList)
	 */
	ArrayList<TeamSeason> teamSeasons;
	
	/**
	 * All of the SportsYears in this object
	 */
	TreeMap<Integer, SportsYear> sportsYears;
	
	
	public SportsStuff(){
		teams= new TreeMap<String, Team>();
		teamSeasons=new ArrayList<TeamSeason>();
		sportsYears=new TreeMap<Integer, SportsYear>();
	}
	
	/**
	 * Adds a team with the specified ID to the teams TreeMap
	 * @param ID The ID of the team
	 * @param team The team itself
	 */
	public void addTeam(String ID, Team team)
	{
		if(teams.get(ID)==null)
			teams.put(ID, team);
	}
	
	public void addTeamSeason(String ID, TeamSeason teamSeason, int year)
	{
		teamSeasons.add(teamSeason);
	}
	
	public static void main (String[] args)
	{
		SportsStuff sportsStuff= new SportsStuff();
		try{
		sportsStuff.prepareFromCSVUsingCountryModel("hello", new CountryModel());
		}catch (Exception e)
		{
			System.out.println ("Help");
		}
	}
	
	public void prepareFromCSVUsingCountryModel(String fileName, CountryModel countryModel)
	{
		ArrayList<String> strings= new ArrayList<String>();
		try{
		strings=HelperMethods.convertCSVToStringList("teams2.csv");
		}catch (Exception e)
		{
			System.out.println("Could not grab from "+fileName);
		}
		String ID="";
		SportsYear sportsYear;
		TeamSeason teamSeason;
		
		String name;
		int year;
		String cityName=new String();
		String stateName= new String();
		PersonList personList= new PersonList();
		
		for (int i=0; i<strings.size(); i++)
		{
			try
			{
			String line= strings.get(i);
			String[] seasonParts = line.split(";");
			year=Integer.parseInt(seasonParts[0].trim());
			name=seasonParts[1].trim();
			cityName=seasonParts[2].trim();
			stateName=seasonParts[3].trim();
			personList	= new PersonList();
			for(int j = 4; j<seasonParts.length;j++)
			{
				Person tempPerson=countryModel.findPerson(seasonParts[j].trim());
				if (tempPerson!=null)
					personList.addPerson(tempPerson);
				else
				{
					tempPerson=new Person(seasonParts[j].trim());
					countryModel.addPerson(tempPerson);
					personList.addPerson(tempPerson);
					System.out.println(seasonParts[j].trim()+" wasn't in country. They've been added with empty data.");
				}
				
			}
			
			sportsYear=findOrAddSportsYearFromInt(year);
			teamSeason=new TeamSeason(name, ID, sportsYear);
			teamSeasons.add(teamSeason);
			}
			catch (Exception e)
			{
				try
				{
					String tempString= strings.get(i).trim();
					ID=tempString.substring(0, tempString.length()-1);
				}catch (Exception f)
				{
					System.out.println("Parsing error in SportsStuff");
				}
			}

		}
	}
	
	public SportsYear findOrAddSportsYearFromInt(int i)
	{
		if (sportsYears.get(new Integer(i))==null)
		{
			SportsYear sy=new SportsYear(i);
			sportsYears.put(new Integer(i), sy);
			return sy;
		}
		else
			return sportsYears.get(new Integer(i));
	}
	
	public ArrayList<TeamSeason> getTeamSeasons()
	{
		return teamSeasons;
	}
	
}
