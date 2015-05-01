package degreesOfSeparationClasses;

import java.util.ArrayList;
import java.util.Iterator;

import sports.TeamSeason;
import countryComponents.Person;

public class FastDegreesOfSeparationClass extends AbstractDegreesOfSeparationClass {
	
	
	
	public FastDegreesOfSeparationClass(Person person1, Person person2)
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
		if (journey.size()+1-1>=4||journey.size()+1-1>=minSize&&minSize!=0){}
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
}
