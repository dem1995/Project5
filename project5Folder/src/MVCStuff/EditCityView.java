package MVCStuff;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import countryComponents.City;

/**
 *A view for editing a given City
 */
public class EditCityView extends View{
	
	private City city;
	
	private JTextField cityName= new JTextField();
	
	private JTextField latitudeField= new JTextField();
	
	private JTextField longitudeField= new JTextField();
	
	private JButton enterButton= new JButton("Submit");
	
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
	
	
	public JButton getEnterButton()
	{
		return enterButton;
	}
	
	public String getName()
	{
		return cityName.getText();
	}
	
	public double getLatitude()
	{
		return (Double.parseDouble(latitudeField.getText()));
	}
	
	public double getLongitude()
	{
		return (Double.parseDouble(longitudeField.getText()));
	}

	public City getCity()
	{
		return city;
	}
}
