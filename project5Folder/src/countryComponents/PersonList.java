package countryComponents;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Project #4
 * CS 2334, Section 010
 * April 16, 2015
 * <p>
 * A sort-of wrapper for an <code>ArrayList</code> of <code>Person</code>s
 * </p>
 * @version 2.0
 */
public class PersonList implements Serializable{
	
	//Static Comparator<Person> objects. To be used when sorting and searching for things		
	/**
	 * Comparator for searching/sorting by first name
	 */
	public static CompareFirstName firstNameComparator=new CompareFirstName();
	/**
	 * Comparator for searching/sorting by last name
	 */
	public static CompareLastName lastNameComparator=new CompareLastName();
	/**
	 * Comparator for searching/sorting by middle names
	 */
	public static CompareMiddleNames middleNamesComparator=new CompareMiddleNames();
	/**
	 * Comparator for searching/sorting by birthdate
	 */
	public static CompareBirthDate birthDateComparator=new CompareBirthDate();

	
	//Instance variables~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * How the list is currently sorted
	 */
	public String currentlySortedBy="";
	
	/**
	 * An <code>ArrayList</code> of <code>Person</code>s
	 */
	private ArrayList<Person> people;
	
	
	//Instance methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * Creates a <code>PersonList</code> with an empty <code>people</code>
	 */
	public PersonList()
	{
		people=new ArrayList<Person>();
	}
	
	public PersonList(ArrayList<Person> people)
	{
		this.people=people;
	}
	
	//Getter methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * A method for getting a <code>Person</code> from <code>people</code>
	 * @param i The index of the person to be gotten
	 * @return The person at index i of PersonList's people object
	 */
	public Person getPerson(int i)
	{
		return (people.get(i));
	}
	
	/**
	 * Getter method for <code>people</code>
	 * @return people
	 */
	public ArrayList<Person> getPeople()
	{
		return people;
	}
	
	//Sorting methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * Sorts <code>people</code> using <code>comparator</code>. If <code>comparator</code> is <code>null</code>, uses natural ordering. Changes <code>currentlySortedBy</code>.
	 * @param comparator How people should be sorted
	 */
	void sortUsing(Comparator<Person> comparator)
	{
		Collections.sort(people, comparator);
		if (comparator==null)
			currentlySortedBy="natural";
		else
			currentlySortedBy=comparator.getClass().getSimpleName();
	}
	
	/**
	 * Sorts <code>people</code> using <code>comparator</code>. If <code>comparator</code> is <code>null</code>, uses natural ordering. Changes <code>currentlySortedBy</code>.
	 * @param comparator How people should be sorted
	 * @return This object after it has been sorted
	 */
	public PersonList returnAfterSortingUsing(Comparator<Person> comparator)
	{
		Collections.sort(people, comparator);
		if (comparator==null)
			currentlySortedBy="natural";
		else
			currentlySortedBy=comparator.getClass().getSimpleName();
		return this;
	}
	
	
	//Searching methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
	/**
	 * Searches <code>people</code> for and returns <code>Person</code>s with data that exactly match <code>key</code>
	 * @param key Either a full name, a first name, a last name, or a String representation of a date, depending on the comparator
	 * @param comparator How the searching will be done
	 * @return A PersonList with Person objects whose data exactly matches the key, if there is none return null
	 */
	public PersonList exactSearchFor(String key, Comparator<Person> comparator)
	{
		PersonList personList=new PersonList();
		Person keyPerson=new Person(key, null);
		if(comparator==null)
		{
			if (!currentlySortedBy.equals("natural"))
			{
				sortUsing(comparator);
			}
		}
		else if (!currentlySortedBy.equals(comparator.getClass().getSimpleName()))
			sortUsing(comparator);
		int keyMatch=Collections.binarySearch(people, keyPerson, comparator);
		if (keyMatch>0)
		{
			personList.addPerson(people.get(keyMatch));
			return personList;
		}
		return null;
	}
	
	/**
	 * Searches <code>people</code> for and returns <code>Person</code>s with full names that contain <code>key</code>
	 * @param name What the Persons in the returned PersonList should contain in their full names
	 * @return A PersonList with Person objects whose full names contain the key
	 */
	public PersonList partialSearchNamesFor(String name)
	{
		PersonList personNameMatchList=new PersonList();
		name=name.toLowerCase();
		for (Person p:people)
		{
			if (p.getFullName().toLowerCase().contains(name))
				personNameMatchList.addPerson(p);
		}
		return personNameMatchList;
	}
	

	//Other methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * Merges two <code>Person</code>s into one
	 * @param person The Person to be merged with the Person this is called on.
	 * @return a combination of the PersonList that the combineWith method was called for and the PersonList the combineWith method was sent
	 */
	private PersonList combineWith(PersonList person)
	{
		people.addAll(person.people);
		return this;
	}
	
	/**
	 * Combines two PersonLists in a way that does not jeopardize the safety of City objects
	 * @param a a PersonList
	 * @param b a PersonList
	 * @return A PersonList made from using combineWith(a) and combineWith(b) on an empty personList;
	 */
	public static PersonList combineTwoPersonLists(PersonList a, PersonList b)
	{
		PersonList c=new PersonList();
		c.combineWith(a);
		c.combineWith(b);
		return c;
	}
	
	
	/**
	 * A method for adding a <code>Person</code> to <code>people</code>
	 * @param person The person to be added to People
	 */
	public void addPerson(Person person)
	{
		people.add(person);		
	}
	
	/**
	 * A method for removing a person from this city
	 * @param person The person to be removed
	 */
	public void removePerson(Person person)
	{
		people.remove(person);
	}
	

	
	
	public String toString()
	{
		String cheese="";
		for (int i=0; i<people.size(); i++)
			try
		{
			cheese+=people.get(i).toString()+"\n";
		}
		catch(Exception e)
		{}
		return cheese;
	}
	

}
