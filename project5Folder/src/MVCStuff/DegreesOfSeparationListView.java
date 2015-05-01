package MVCStuff;

import java.awt.event.ActionEvent;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.JLabel;

import countryComponents.Person;
import sports.TeamSeason;

/**
 *A view for showing degrees of separation
 * Project #5
 * CS 2334, Section 010
 * April 24, 2015
 */
public class DegreesOfSeparationListView extends View {

	JLabel stuff= new JLabel();
	
	
	/**
	 * Constructor for DegreesOfSeparationListView
	 */
	public DegreesOfSeparationListView(Person person, TeamSeason teamSeason)
	{
		super();
		stuff.setText(person.getFullName()+" "+ teamSeason);
		this.add(stuff);
	}
	
	public DegreesOfSeparationListView(Person start, Person end)
	{
		getDegrees(start, end, null);
		System.out.println(minSize);
		System.out.print(journeys);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		//TODO
	}
	
	
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



	/**
	 * A method for getting the shortest degrees of separation paths between two people
	 * @param start The person to start from
	 * @param end	The destination person
	 * @param current	The intermediate person currently being checked
	 * @return whether or not a new shortest degrees of freedom path was found.
	 */
	public void getDegrees(Person start, Person end, TeamSeason currentSeason) throws StackOverflowError
	{
		if (journey.size()>=4||journey.size()>minSize&&minSize!=0);
		else
		{
			
			journey.push(start);
			seasonJourney.push(currentSeason);
			if(journey.peek()==end)
				addJourney();
			else
			{
				for (TeamSeason tempSeason: journey.peek().getTeamSeasons())
				{
					//if(!checkedSeasons.contains(currentSeason))
					{
					//System.out.println(currentSeason.toString());
					//System.out.println(currentSeason.getPlayers());

					for(Person currentPlayer: tempSeason.getPlayers())
					{
						if (!checkedPersons.contains(currentPlayer))
						{
							checkedPersons.add(currentPlayer);
							checkedSeasons
							getDegrees(currentPlayer, end, tempSeason);
							checkedPersons.remove(currentPlayer);
						}
					}
					}
				}
				//System.out.println("_---------------------------------------------------");
			}
			journey.pop();
		}
	}
		
	public void addJourney()
	{
		journeys.clear();
		ArrayList<Person> people= new ArrayList<Person>();
		Iterator<Person> journeyIterator= journey.iterator();
		while (journeyIterator.hasNext())
		{
			people.add(journeyIterator.next());
		}
		journeys.add(people);
		System.out.println(journey);
		System.out.println(people.get(0));
		minSize=journey.size();
		
	}
		
		
	
}
