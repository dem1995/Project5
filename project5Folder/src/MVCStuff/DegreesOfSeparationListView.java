package MVCStuff;

import java.awt.event.ActionEvent;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import countryComponents.Person;
import degreesOfSeparationClasses.AbstractDegreesOfSeparationClass;
import degreesOfSeparationClasses.ExperimentalDegreesOfSeparationClass;
import degreesOfSeparationClasses.FastDegreesOfSeparationClass;
import degreesOfSeparationClasses.SlowDegreesOfSeparationClass;
import sports.TeamSeason;

/**
 *A view for showing degrees of separation
 * Project #5
 * CS 2334, Section 010
 * May 1, 2015
 */
public class DegreesOfSeparationListView extends View {

	JLabel stuff= new JLabel();
	
	public AbstractDegreesOfSeparationClass dos;
	
	/**
	 * Constructor for DegreesOfSeparationListView
	 */
	public DegreesOfSeparationListView(Person person, TeamSeason teamSeason)
	{
		super();
		stuff.setText(person.getFullName()+" "+ teamSeason);
		this.add(stuff);
	}
	
	public DegreesOfSeparationListView(ArrayList<Person> journeys)
	{
		
	}
	
	public DegreesOfSeparationListView(Person start, Person end)
	{
		String[] options= {"Fast", "Slow", "Experimental"};
		 int messageType = JOptionPane.QUESTION_MESSAGE;
	      int code = JOptionPane.showOptionDialog(null, 
	         "Do you prefer a fast Degrees of Separation search, or a slow Degrees of Separation search? While the slow one will give you all shortest paths, it takes a very long time for large amounts of data. The experimental one is experimental "
	         + "On the other hand, the fast one only returns one example of a shortest Degrees of Separation path.", 
	         "Degrees of Separation Type Chooser", 0, JOptionPane.QUESTION_MESSAGE, 
	         null, options, null);
	    if (code==0)
	    	dos=new FastDegreesOfSeparationClass(start, end);
	    else if (code==1)
	    	dos=new SlowDegreesOfSeparationClass(start,end);
	    else if (code==2)
	    	dos=new ExperimentalDegreesOfSeparationClass(start, end);
		System.out.println(dos.getMinSize());
		System.out.print(dos.getJourneys());
	}
	
	public void actionPerformed(ActionEvent e)
	{
		//TODO
	}
	

		
	
}
