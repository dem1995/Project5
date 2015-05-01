/**
 * 
 */
package countryComponents;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.TreeSet;

import MVCStuff.CountryModel;
import sports.TeamSeason;

/**
 * Project #4
 * CS 2334, Section 010
 * April 16, 2015
 * <p>
 * An object that contains data about a person, such as a first name, a last name, and a birthdate. Middle names are optional.
 * </p>
 * @version 2.0
 */
public class Person implements Comparable<Person>, Serializable {
	
	//Instance Variables~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 *The first name of the person 
	 */
	private String firstName="";
	/**
	 * The last name of the person
	 */
	private String lastName="";
	/**
	 * The middle names of the person, separated by spaces
	 */
	private String middleNames="";

	/**
	 * The name of the city the person lives in
	 */
	private String cityName="";
	
	/**
	 * The name of the state the person lives in
	 */
	private String stateName="";
	/**
	 * The date of birth of the person
	 */
	private Date birthDate;
	/**
	 * The date of death of the person
	 */
	private Date deathDate;
	/**
	 * the age of the person
	 */
	
	/**
	 * The teamSeasons this person has been a part of
	 */
	private TreeSet<TeamSeason> teamSeasons= new TreeSet<TeamSeason>();
	
	//Instance Methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * Constructor method for a person with a first name, a middle name, a last name, and a birthdate.
	 * @param firstName The person's first name
	 * @param lastName The person's last name
	 * @param birthDate The person's birth date.
	 */
	public Person(String firstName, String lastName, Date birthDate)
	{
		this.firstName=firstName;
		this.lastName=lastName;
		this.birthDate=birthDate;
	}
	/**
	 * Constructor method for a person with a first name, a middle name, a last name, and a birthdate.
	 * @param firstName The person's first name
	 * @param lastName	The person's last name
	 * @param birthDate The person's birth date.
	 * @param deathDate	the person's death date.
	 */
	public Person(String firstName, String lastName, Date birthDate, Date deathDate)
	{
		this.firstName=firstName;
		this.lastName=lastName;
		this.birthDate=birthDate;
		this.deathDate=deathDate;
	}
	
	/**
	 * Constructor method for person with a first name, a middle name, a last name, and a birthdate.
	 * @param firstName The person's first name
	 * @param middleNames The person's middle names, separated by spaces
	 * @param lastName The person's last name
	 * @param birthDate The person's birth date.
	 */
	public Person(String firstName, String middleNames, String lastName, Date birthDate)
	{
		this(firstName, lastName, birthDate);
		this.middleNames=middleNames;
	}
	
	/**
	 * Constructor method for a person with a given name
	 * @param fullName The person's fullName, is split up, and its components are assigned to firstName, lastName, and middleNames
	 * @param birthDate The person's birth date
	 */
	
	public Person(String fullName, Date birthDate)
	{
		String[] names=fullName.split(" ");
		for (int i=0; i<names.length; i++)
		{
			if (i==0)
				this.firstName=names[i];
			else if (i<names.length-2)
				this.middleNames+=(names[i]+" ");
			else if (i==names.length-2)
				this.middleNames+=(names[i]);
			else if (i==names.length-1)
				this.lastName=names[i];
		}			
		this.birthDate=birthDate;
	}
	
	public Person(String fullName, Date birthDate, Date deathDate)
	{
		this(fullName, birthDate);
		this.setDeathDate(deathDate);
	}


	/**
	 * Constructor method for a person with a given name
	 * @param fullName The person's fullName, is split up, and its components are assigned to firstName, lastName, and middleNames
	 */
	
	public Person(String fullName)
	{
		String[] names=fullName.split(" ");
		for (int i=0; i<names.length; i++)
		{
			if (i==0)
				this.firstName=names[i];
			else if (i<names.length-2)
				this.middleNames+=(names[i]+" ");
			else if (i==names.length-2)
				this.middleNames+=(names[i]);
			else if (i==names.length-1)
				this.lastName=names[i];
		}			
		//age = getAge(this.birthDate, null);
	}

	
	//Getter Methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * Getter method for <code>firstName</code>
	 * @return The firstName String of the person object
	 */
	public String getFirstName()
	{
		return firstName;
	}
	
	/**
	 * Getter method for <code>lastName</code>
	 * @return The lastName String of the person object
	 */
	public String getLastName()
	{
		return lastName;
	}
	
	/**
	 * Getter method for <code>middleNames</code>
	 * @return The middleNames String of the person object
	 */
	public String getMiddleNames()
	{
		return middleNames;
	}
	
	/**
	 * Returns the full name of the person, in the order First Middles Last
	 * @return The full name of the person, in the order First Middles Last
	 */
	public String getFullName()
	{
		if (lastName=="")
			return firstName;
		if (middleNames=="")
			return firstName+" "+lastName;
		return (firstName+" "+middleNames+" "+lastName);
	}
	
	/**
	 * Getter method for <code>birthDate</code>
	 * @return The date of birth of the Person
	 */
	public Date getBirthDate()
	{
		return birthDate;
	}
	
	/**
	 * Getter method for <code>stateName</code>
	 * @return the name of the state the person lives in
	 */
	public String getStateName()
	{
		return stateName;
	}
	
	/**
	 * Getter method for <code>deathDate</code>
	 * @return the date of death of the person
	 */
	public Date getDeathDate() {
		return deathDate;
	}
	
	/**
	 * Getter method for <code>cityName</code>
	 * @return The name of the city the person lives in
	 */
	public String getCityName()
	{
		return cityName;
	}
	
	/**
	 * Getter method for <code>age</code>
	 * @return The name of the city the person lives in
	 */
	public int getAge()
	{
		return getAge(this.birthDate, this.deathDate);
	}
	
	//Setter Methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * Setter method for <code>stateName</code>
	 * @param stateName	The name of the state
	 */
	void setStateName(String stateName)
	{
		this.stateName=stateName;
	}
	
	public void setBirthDate(Date birthDate)
	{
		this.birthDate= birthDate;
	}
	
	/**
	 * Setter method for <code>deathDate</code>
	 * @param deathDate	the date of death of the person
	 */
	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}
	
	/**
	 * Setter method for <code>cityName</code>
	 * @param cityName The name of the city
	 */
	void setCityName(String cityName)
	{
		this.cityName=cityName;
	}
	
	public String toString()
	{
		if(getDeathDate()==null)
		{
			if(getBirthDate() == null)
			{
				return getFullName()+","+getCityName()+","+getStateName();
			}
			else
			{
				return getFullName()+", "+getCityName()+", "+getStateName() +", "+getBirthDate() + ", " +getAge()+ " years old";
			}
		}
		else
		{
			return getFullName()+", "+getCityName()+", "+getStateName() +", "+getBirthDate() + ", " + getDeathDate() + ", " + getAge()+ " years old";
		}
	}
	
	/**
	 * Returns the age of the person at death, if they are dead. If deathDate is null, returns their current age (assumes person is still alive). If both birthDate and deathDate are unknown, returns 0.
	 * @param birth The birth Date of this person
	 * @param death The death Date of this person
	 * @return The age of the person at death, if they are dead. If deathDate is null, their current age (assumes person is still alive). If both birthDate and deathDate are unknown, 0.
	 */
	@SuppressWarnings("deprecation")
	public int getAge(Date birth, Date death)
	{
		int age;
		try{
			
		if(birth!=null)
		{
			if(death ==null)
			{
				age=new Date().getYear()-birth.getYear();
				//long age = (birth.getTime())/(1000*60*60*24*365);	
			}
			else
			{
				age=death.getYear()-birth.getYear();
				//long age = (death.getTime()-birth.getTime())/(1000*60*60*24*365); // from ms to seconds to minutes to hours to days to years
			}
			//System.out.println(age);
		
		
			return (int) age;
		}
		else
			return 0;
		}catch (Exception e)
			{return 0;}
		
	}
	
	public TreeSet<TeamSeason> getTeamSeasons()
	{
		return teamSeasons;
	}
	
	//Adder methods
	public void addSeason(TeamSeason teamSeason)
	{
		teamSeasons.add(teamSeason);
	}
	public void setSeasons(TreeSet<TeamSeason> teamSeasons)
	{
		this.teamSeasons.addAll(teamSeasons);
	}
	
	//Other methods
	public void remakeAs(CountryModel countryModel, Person newPerson, State state, City city)
	{				
		this.firstName=newPerson.getFirstName();
		this.middleNames=newPerson.getMiddleNames();
		this.lastName= newPerson.getLastName();
		this.setBirthDate(newPerson.getBirthDate());
		this.setDeathDate(newPerson.getDeathDate());
		countryModel.removePerson(this);
		this.stateName=state.getName();
		this.cityName=city.getName();
		countryModel.addPerson(this);
		
	}
	
	//Interface-required Methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public int compareTo(Person otherPerson) {
		return this.getFullName().compareToIgnoreCase(otherPerson.getFullName());
	}
	
	//Conversion methods
	/**
	 * Method for converting a String in the form of "Name, birthDate, birthCity, birthState" or ""Name, birthDate, birthCity, birthState" containing Person data to a Person object
	 * @param personString Sting representing a person object's data
	 * @param formatter Formats the date data
	 * @return a Person object generated from the personString input
	 * @throws Exception throws an exception if this method receives invalid input
	 */
	public static Person convertStringToPerson(String personString, SimpleDateFormat formatter) throws Exception
	{
		Person aPerson=null;
		String[] personParts = personString.split(",");
		String fullName=personParts[0];

		if (personParts.length<4||personParts.length>5)
			throw new Exception("\""+personString+"\""+"had too many/too few parts separated by commas");

		try
		{
			if (personParts[1].contains("/")) //Tests to see if person has a BirthDate. Runs if true.
			{
				aPerson=new Person(fullName, formatter.parse(personParts[1]));
				aPerson.setCityName(personParts[2]);
				aPerson.setStateName(personParts[3]);
				if(personParts.length == 5)
				{
					if(personParts[4].contains("/"))
					{
						aPerson.setDeathDate(formatter.parse(personParts[4]));
					}
				}
				
			}
			else if (!personParts[1].contains("/")) //Tests to see if person has a BirthDate. Runs if false.
			{
				aPerson=new Person(fullName, null);
				aPerson.setCityName(personParts[1]);
				aPerson.setStateName(personParts[2]);
				if(personParts.length == 4)
				{
					if(personParts[3].contains("/"))
					{
						aPerson.setDeathDate(formatter.parse(personParts[4]));
					}
				}
			}
		}
		catch (ParseException e)
		{throw new Exception("\""+personString+"\""+ "'s parts separated by commas were in the wrong formats");}

		return aPerson;	
	}
	

}