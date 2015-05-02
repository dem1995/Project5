package MVCStuff;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import countryComponents.City;

/**
 *A view for editing a given City
 * Project #5
 * CS 2334, Section 010
 * May 1, 2015
 */
public class EditCityView extends View{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1482684638752972245L;

	/**
	 * The city being edited
	 */
	private City city;
	
	/**
	 * The name of the city
	 */
	private JTextField cityName= new JTextField();
	
	/**
	 * The latitude of the city
	 */
	private JTextField latitudeField= new JTextField();
	
	/**
	 * The longitude of the city
	 */
	private JTextField longitudeField= new JTextField();
	
	/**
	 * The button to input data for the city
	 */
	private JButton enterButton= new JButton("Submit");
	
	/**
	 * View for editing the city
	 * @param city The city to be edited
	 */
	public EditCityView(City city)
	{
		super();
		this.city=city;
		this.setLayout(new GridLayout(2,4));
		
		this.add(new JLabel("City Name"));
		
		this.add(new JLabel("latitude"));
		
		this.add(new JLabel("longitude"));
		
		this.add(new JLabel());
		
		cityName.setText(city.getName());
		this.add(cityName);
		
		latitudeField.setText(city.getLatitude()+"");
		this.add(latitudeField);
		
		longitudeField.setText(city.getLongitude()+"");
		this.add(longitudeField);
		
		this.add(enterButton);
	}
	
	
	

	/**
	 * Gives the enter button
	 * @return The enter button
	 */
	public JButton getEnterButton()
	{
		return enterButton;
	}
	
	/**
	 * Gives the contents of the name field
	 * @return The contents of the name field
	 */
	public String getName()
	{
		return cityName.getText();
	}
	
	/**
	 * Returns a double parsed from the contents of the latitude field
	 * @return a double parsed from the contents of the latitude field
	 */
	public double getLatitude()
	{
		return (Double.parseDouble(latitudeField.getText()));
	}
	
	/**
	 * Returns a double parsed from the contents of the longitude field
	 * @return a double parsed from the contents of the longitude field
	 */
	public double getLongitude()
	{
		return (Double.parseDouble(longitudeField.getText()));
	}

	/**
	 * Gets the city being edited by this view
	 * @return The city being edited by this view
	 */
	public City getCity()
	{
		return city;
	}
}
