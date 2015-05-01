package MVCStuff;

import java.awt.event.ActionEvent;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.swing.JLabel;

import otherClasses.HelperMethods;
import countryComponents.Person;
import sports.TeamSeason;

/**
 *A view for showing degrees of separation
 * Project #5
 * CS 2334, Section 010
 * April 24, 2015
 */
public class DegreesOfSeparationListViewOld extends View {

	JLabel stuff= new JLabel();
	
	
	/**
	 * Constructor for DegreesOfSeparationListView
	 */
	public DegreesOfSeparationListViewOld(Person person, TeamSeason teamSeason)
	{
		super();
		stuff.setText(person.getFullName()+" "+ teamSeason);
		this.add(stuff);
	}
	
	public DegreesOfSeparationListViewOld(Person start, Person end)
	{
		getDegrees(start, end, start);
		System.out.println(curSize);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		//TODO
	}
	
	
	private int curSize=0;

	/**
	 * A deque to be added to/subtracted from when building degrees of separation routes
	 */
	private ArrayDeque<Person> path= new ArrayDeque<Person>();

	/**
	 * The paths of shortest degrees of separation
	 */
	private ArrayList<ArrayDeque<Person>> paths= new ArrayList<ArrayDeque<Person>>();

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
		public boolean getDegrees(Person start, Person end, Person curPlayer)
		{
			if (curSize!=0)
				if(path.size()>curSize)
					return false;
			if(path.contains(curPlayer))
				return false;
			path.push(curPlayer);
			
			for (TeamSeason season: curPlayer.getTeamSeasons())
			{
				if (season.contains(end))
					{
					
						paths.add(path);
						curSize=path.size();
					}
				else
					if(checkedSeasons.contains(season))
						return true;
					else
						for (Person player: season.getPlayers())
							if(!getDegrees(start, end, player))
								path.pop();
			}
			return true;
		}
	
}
