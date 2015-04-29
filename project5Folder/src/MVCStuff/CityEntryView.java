package MVCStuff;

//import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import countryComponents.City;
import countryComponents.State;

/**
 *The view for entering a city.
 */
public class CityEntryView extends View {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The State of which the created City will be a part of.
	 */
	private State state;
	/**
	 * The field in which a user will enter the name of the city to be created
	 */
	JTextField cityEntryJTextField=new JTextField();
	
	/**
	 * The field for entering the latitudinal coordinate of this city
	 */
	JTextField latitudeEntryJTextField=new JTextField();
	
	/**
	 * The field for entering the longitudinal coordinate of this city
	 */
	JTextField longitudeEntryJTextField=new JTextField();
	
	/**
	 * The button used to combine the fields into a new city and add it to the database
	 */
	JButton enterButton = new JButton("Enter");
	
	/**
	 * Constructor for CityEntryView. The created City will be in the State provided.
	 * @param state the State from which to grab the cities
	 */
	public CityEntryView(State state)
	{
		super();
		this.state=state;
		setLayout(new GridBagLayout());
		GridBagConstraints constraints=new GridBagConstraints();
		constraints.fill=GridBagConstraints.BOTH;
		constraints.gridy=0;
		constraints.gridx=0;
		constraints.weightx=1;
		constraints.weighty=1;
		constraints.gridwidth=GridBagConstraints.REMAINDER;
		add(new JLabel("Enter the city name, latitude, and longitude below, then press the \"enter\" button", SwingConstants.CENTER), constraints);
		constraints.gridy=1;
		constraints.gridx=0;
		constraints.gridwidth=1;
		add(new JLabel("City Name"), constraints);
		constraints.gridx=1;
		add(new JLabel("Latitude"), constraints);
		constraints.gridx=2;
		add(new JLabel("Longitude"), constraints);
		constraints.gridy=2;
		constraints.gridx=0;
		add(cityEntryJTextField, constraints);
		constraints.gridx=1;
		add(latitudeEntryJTextField, constraints);
		constraints.gridx=2;
		add(longitudeEntryJTextField, constraints);
		constraints.gridy=3;
		constraints.gridx=0;
		constraints.gridwidth=GridBagConstraints.REMAINDER;
		add(enterButton, constraints);
	}
	
	
	/**
	 * Retrieves the Enter Button from this view
	 * @return the Enter Button
	 */
	public JButton getEnterButton()
	{
		return enterButton;
	}
	
	/**
	 * Returns a city from the selected spots on the 
	 * @return A city made from the textFields' data
	 */
	public City getFullCity()
	{
		City city= new City(cityEntryJTextField.getText(), state);
		city.setLatitude(Integer.parseInt(latitudeEntryJTextField.getText()));
		city.setLongitude(Integer.parseInt(longitudeEntryJTextField.getText()));
		return city;
	}
	
	/**
	 * Closes this view.
	 */
	public void closeWindow()
	{
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//Do nothing. No need to update here.	
	}
	
}
