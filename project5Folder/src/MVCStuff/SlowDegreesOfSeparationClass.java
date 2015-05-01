package MVCStuff;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import sports.TeamSeason;
import countryComponents.Person;

public class SlowDegreesOfSeparationClass {
	
	private int minSize=0;

	/**
	 * A deque to be added to/subtracted from when building degrees of separation routes
	 */
	private ArrayDeque<Person> journey= new ArrayDeque<Person>();
	
	
	
	private ArrayDeque<TeamSeason> seasonJourney= new ArrayDeque<TeamSeason>();

	/**
	 * The paths of shortest degrees of separation
	 */
	private ArrayList<ArrayList<Person>> journeys= new ArrayList<ArrayList<Person>>();

	private TreeSet<Person> checkedPersons= new TreeSet<Person>();
	
	/**
	 * The seasons that have already been checked for the getDegrees method
	 */
	private TreeSet<TeamSeason> checkedSeasons= new TreeSet<TeamSeason>();

	int count=0;

	
	public SlowDegreesOfSeparationClass(Person person1, Person person2)
	{
			getDegrees(person1, person2, TeamSeason.makeEmptyTeamSeason());
			System.out.println(minSize);
			System.out.print(journeys);
	}

	/**
	 * A method for getting the shortest degrees of separation paths between two people
	 * @param start The person to start from
	 * @param end	The destination person
	 * @param current	The intermediate person currently being checked
	 * @return whether or not a new shortest degrees of freedom path was found.
	 */
	public void getDegrees(Person start, Person end, TeamSeason inSeason) throws StackOverflowError
	{
		//System.out.println(count);
		System.out.println(journey);
		//count++;
		if (journey.size()+1-1>=4||journey.size()+1-1>minSize&&minSize!=0){}
		else
		{
			
			journey.addLast(start);
			seasonJourney.addLast(inSeason);
			if(journey.peekLast()==end)
			{
				System.out.println("minSize is "+minSize);
				addJourney();
				System.out.println("minSize is "+minSize);
			}
			else
			{
				for (TeamSeason currentSeason: journey.peekLast().getTeamSeasons())
				{
					if(!checkedSeasons.contains(currentSeason))
					{
					//	checkedSeasons.add(currentSeason);

						for(Person currentPlayer: currentSeason.getPlayers())
						{
							if (!(checkedPersons.contains(currentPlayer)||currentPlayer==start))
							{
								checkedPersons.add(currentPlayer);
								getDegrees(currentPlayer, end, currentSeason);
								checkedPersons.remove(currentPlayer);
							}
						}
					//	checkedSeasons.remove(currentSeason);
					}
				}
			}
			journey.removeLast();
		}
	}
		
	public void addJourney()
	{
		if (journey.size()-1<minSize)
			journeys.clear();
		ArrayList<Person> people= new ArrayList<Person>();
		Iterator<Person> journeyIterator= journey.iterator();
		while (journeyIterator.hasNext())
		{
			people.add(journeyIterator.next());
		}
		journeys.add(people);

		minSize=journey.size()-1;
		
	}
		
	
	public ArrayList<ArrayList<Person>> getJourneys()
	{
		return journeys;
	}
	
	public int getMinSize()
	{
		return minSize;
	}
}
