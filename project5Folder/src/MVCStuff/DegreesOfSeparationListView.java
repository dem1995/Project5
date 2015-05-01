package MVCStuff;

import java.awt.event.ActionEvent;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
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
	
	public DegreesOfSeparationClass dos;
	
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
		dos=new DegreesOfSeparationClass(start, end);
		System.out.println(dos.getMinSize());
		System.out.print(dos.getJourneys());
	}
	
	public void actionPerformed(ActionEvent e)
	{
		//TODO
	}
	

		
	
}
