package MVCStuff;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import countryComponents.City;
import countryComponents.DateFormatter;
import countryComponents.Person;
import countryComponents.State;
/**
 * Project #4
 * CS 2334, Section 010
 * April 20, 2015
 */
public class EditPersonView extends View {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8908144781011843194L;


	/**
	 * The person being edited
	 */
	private Person person;

	
	/**
	 * The text field for the name of the person
	 */
	private JTextField nameField= new JTextField();
	
	/**
	 * The list of states to assign the person to
	 */
	private JList<State> stateList= new JList<State>();
	
	/**
	 * The list of cities to assign the person to
	 */
	private JList<City> cityList= new JList<City>();
	
	/**
	 * The field to enter the birth date into
	 */
	private JTextField birthDateField= new JTextField();
	
	/**
	 * The field to enter the death date into
	 */
	private JTextField deathDateField= new JTextField();
	
	/**
	 * The button to submit changes
	 */
	private JButton enterButton= new JButton("Enter");
	

	/**
	 * View for editing a Person
	 * @param p the person to be edited
	 * @param countryModel the model the person is in
	 */
	public EditPersonView(Person p, CountryModel countryModel)
	{
		super();
		this.countryModel=countryModel;
		this.setLayout(new GridLayout(2,6));
		this.add(new JLabel("Name"));
		this.add(new JLabel("State"));
		this.add(new JLabel("City"));
		this.add(new JLabel("Birth Date"));
		this.add(new JLabel("Death Date"));
		this.add(new JLabel());
		person=p;
		
		//Prepare name field and add it
		nameField= new JTextField(person.getFullName());
		this.add(nameField);
		
		//Prepare state list and state list scroller, add the state list to the scroller, and add the scroller to the frame
		stateList= new JList<State>(countryModel.getStates().toArray(new State[0]));
		stateList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		State state=countryModel.findStateOrAdd(p.getStateName());
		stateList.setSelectedValue(state, true);
		JScrollPane stateScroller= new JScrollPane(stateList);
		this.add(stateScroller);
		
		//Prepare city list and city list scroller, add the city list to the scroller, and add the scroller to the frame
		cityList=new JList<City>(state.getCities().toArray(new City[0]));
		cityList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cityList.setSelectedValue(countryModel.findCityOrAdd(state, p.getCityName()), true);
		stateList.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e)
			{
				cityList.setListData(stateList.getSelectedValue().getCities().toArray(new City[0]));
				cityList.validate();
				cityList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			}
		});
		JScrollPane cityScroller= new JScrollPane(cityList);
		this.add(cityScroller);
		
		
		if (p.getBirthDate()!=null)
		birthDateField.setText(DateFormatter.formatter.format(p.getBirthDate()));	
		this.add(birthDateField);
		if (p.getDeathDate()!=null)
		deathDateField.setText(DateFormatter.formatter.format(p.getDeathDate()));
		this.add(deathDateField);
		this.add(enterButton);
	}
	
	
	/**
	 * Retrieves the button used to submit the changes
	 * @return the button used to submit the changes
	 */
	public JButton getEnterButton()
	{
		return enterButton;
	}
	
	/**
	 * Retrieves the person being edited
	 * @return the person being edited
	 */
	public Person getPerson()
	{
		return person;
	}
	
	/**
	 * Returns a person created from the fields present in this view
	 * @return a person created from the fields present in this view
	 * @throws ParseException when date parsing fails
	 */
	public Person getCreatedPerson() throws ParseException
	{
		Person p;
		try{
		if(birthDateField.getText()!="")
			if (deathDateField.getText()!="")
				p=new Person(nameField.getText(), DateFormatter.formatter.parse(birthDateField.getText()), DateFormatter.formatter.parse(deathDateField.getText()));
			else
				p=new Person(nameField.getText(), DateFormatter.formatter.parse(birthDateField.getText()));
		else
			p= new Person(nameField.getText());
		}catch (ParseException e)
		{
			System.out.println("date parsing failed");
		}
		p=new Person(nameField.getText());
		
		return p;
	}
	
	/**
	 * Retrieves the State selected in the State list
	 * @return the state selected in the State list
	 */
	public State getSelectedState()
	{
		return stateList.getSelectedValue();
	}
	
	/**
	 * Retrieves the City selected in the City list 
	 * @return the City selected in the City list
	 */
	public City getSelectedCity()
	{
		return cityList.getSelectedValue();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO	
	}
	
	
}
