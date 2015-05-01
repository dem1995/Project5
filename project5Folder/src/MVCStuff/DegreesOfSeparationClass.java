package MVCStuff;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import sports.TeamSeason;
import countryComponents.Person;

public class DegreesOfSeparationClass {
	
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
	
	private ArrayList<TreeSet<Person>> checkedPersonsV2= new ArrayList<TreeSet<Person>>(4);
	
	private ArrayList<TreeSet<Person>> okayedPersonsV2= new ArrayList<TreeSet<Person>>(4);
	
	/**
	 * The seasons that have already been checked for the getDegrees method
	 */
	private TreeSet<TeamSeason> checkedSeasons= new TreeSet<TeamSeason>();

	int count=0;

	
	public DegreesOfSeparationClass(Person person1, Person person2)
	{
			for (int i=0; i<5; i++)
			{
			checkedPersonsV2.add(new TreeSet<Person>());
			okayedPersonsV2.add(new TreeSet<Person>());
			}
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
		count++;
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
							if (!(checkedPersons.contains(currentPlayer)||currentPlayer==start)
									&&(!(checkedPersonsV2.get(count).contains(currentPlayer)&&(!okayedPersonsV2.get(count).contains(currentPlayer)))))
							{
								checkedPersonsV2.get(count).add(currentPlayer);
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
		count--;
	}
		
	public void addJourney()
	{
		int counter=0;
		if (journey.size()-1<minSize)
			journeys.clear();
		ArrayList<Person> people= new ArrayList<Person>();
		Iterator<Person> journeyIterator= journey.iterator();
		while (journeyIterator.hasNext())
		{
			counter++;
			Person personToAdd=journeyIterator.next();
			okayedPersonsV2.get(counter).add(personToAdd);
			people.add(personToAdd);
		}
		journeys.add(people);
		//System.out.println(journey);
		//System.out.println(people.get(0));
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
