package MVCStuff;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import sports.SportsStuff;
import sports.Team;
import sports.TeamSeason;
import countryComponents.City;
import countryComponents.Country;
import countryComponents.DateFormatter;
import countryComponents.Person;
import countryComponents.State;

/**
 *The model for a Country object. It extends Country to fire events whenever things are added to it.
 * Project #4
 * CS 2334, Section 010
 * April 20, 2015
 */
public class CountryModel extends Country {
	
	/**
	 * The Views to be held by this model
	 */
	private ArrayList<View> views= new ArrayList<View>();
	/**
	 * Constructor
	 */
	public CountryModel()
	{
		super();
		//Prepares the 51 States of United States of America (DC is counted as a state)
		try
		{
			prepareStatesFromTextFile("states.txt");
			}catch (Exception e){
			System.out.println("The program had trouble with using the file states.txt,or states.txt does not exist in an accessible path");
		}
		
		
		//TODO CountryModel constructor
	}
	
	//Adder methods
	
	/**
	 * Adds a View to this model
	 * @param view The View to be added
	 */
	public synchronized void addActionListener (View view)
	{
		views.add(view);
	}
	
	/**
	 * Method for adding a person. Places them in State/City based on their attributes
	 * @param person The person to be added
	 */
	public void addPerson(Person person)
	{
		super.addPerson(person);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, Constants.PERSON_ADDED));
	}
	
	public void addPerson(City city, Person person)
	{
		super.addPerson(city, person);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, Constants.PERSON_ADDED));
	}
	
	public void addPeople(ArrayList<Person> people)
	{	
		for (int i=0; i<people.size(); i++)
			if (this.findPerson(people.get(i).getFullName())==null)
				addPersonWithoutTriggeringEvent(people.get(i));
		forceUpdate();
	}
	
	/**
	 * Method for adding a person without triggering an ActionEvent. DO NOT CALL THIS METHOD WTIHOUT LATER CALLING FORCEUPDATE
	 * @param aPerson The person to be added
	 */
	public void addPersonWithoutTriggeringEvent(Person aPerson)
	{
		super.addPerson(aPerson);
	}
	
	/**
	 * Builds a country from personStrings
	 * @param personStrings The strings from which the Person objects will be constructed
	 * @return The error messages encountered during this
	 */
	public String addStringList(ArrayList<String> personStrings)
	{
		String errors="";
		for (String personString: personStrings)
		{
			try
			{
				Person aPerson=Person.convertStringToPerson(personString, DateFormatter.formatter);
				if(findPerson(aPerson.getFullName())==null)
				this.addPersonWithoutTriggeringEvent(aPerson);
			}
			catch (Exception e)
			{
				errors+=(e.getMessage()+"\n");
			}
		}
		treeMapNeedsBuilding=true;
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, Constants.PERSON_ADDED));
		return errors;
	}
	
	/**
	 * Method for adding a City
	 * @param state the State the City is in
	 * @param city the City being added
	 */
	public void addCity(State state, City city)
	{
		super.addCity(state, city);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, Constants.CITY_ADDED));
	}
	
	
	/**
	 * Method for adding a State
	 * @param state The State to be added
	 */
	public void addState(State state)
	{
		super.addState(state);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, Constants.STATE_ADDED));
	}
	
	/**
	 * Finds an returns a <code>City</code> in the specified <code>State</code> matching the name
	 * @param state The State to find the city in
	 * @param cityName The name of the city to be found
	 * @return 
	 */
	public City findCityOrAdd(State state, String cityName)
	{
		City foundCity= super.findCityOrAdd(state, cityName);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, Constants.CITY_ADDED));
		return foundCity;
	}
	/**
	 * Finds and returns a <code>State</code> matching the name
	 * @param name The name of the State to be found
	 * @return The State that was found
	 */
	public State findStateOrAdd(String name)
	{
		State foundState=super.findStateOrAdd(name);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, Constants.STATE_ADDED));
		return foundState;
	}
	
	//Team addition methods
	
	public void addTeam(Team team)
	{
		super.addTeam(team);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, Constants.TEAM_ADDED));
	}
	
	public void addTeamSeason(TeamSeason teamSeason)
	{
		super.addTeamSeason(teamSeason);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, Constants.SEASON_ADDED));
	}
	
	public void setSportsStuff(SportsStuff sportsStuff)
	{
		super.setSportsStuff(sportsStuff);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, Constants.TEAM_ADDED));
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, Constants.SEASON_ADDED));
		
	}
	
	
	//Other methods
	
	public void removePerson(Person person)
	{
		super.removePerson(person);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, Constants.PERSON_ADDED));
	}
	
	public void removeCity(City city)
	{
		super.removeCity(city);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, Constants.CITY_ADDED));
	}
	
	/**
	 * Removes a view from this model
	 * @param view The View to be removed
	 */
	public synchronized void removeActionListener(View view)
	{
		//TODO removeActionListener method from TeamMateModel method
	}
	
	public void forceUpdate()
	{
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, Constants.PERSON_ADDED));
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, Constants.STATE_ADDED));
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, Constants.CITY_ADDED));
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, Constants.TEAM_ADDED));
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, Constants.SEASON_ADDED));
	}
	
	
	/**
	 * Processes an ActionEvent
	 * @param e the actionEvent to be added
	 */
	private void processEvent(ActionEvent e)
	{
		ArrayList<View> list;

		synchronized (this) {
			if (views == null) return;
			list = (ArrayList<View>)views.clone();
		}

		for (int i = 0; i < list.size(); i++) {
			View view = list.get(i);
			view.actionPerformed(e);
		}
	}
	
	
	/**
	 * Prepares some states for this country from a file specified by the parameter
	 * @param fileName The name of the file
	 * @throws FileNotFoundException thrown if file not found
	 * @throws IOException thrown sometimes. This is very descriptive.
	 */
	private void prepareStatesFromTextFile(String fileName) throws FileNotFoundException, IOException
	{
	BufferedReader bufferedReader=new BufferedReader(new FileReader("states.txt"));
	for (int i=0; i<51; i++)
	{
		this.addState(new State(bufferedReader.readLine()));
	}
	bufferedReader.close();	
	}

	

}
