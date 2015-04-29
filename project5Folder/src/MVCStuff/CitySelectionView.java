package MVCStuff;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;

import countryComponents.City;
import countryComponents.State;

/**
 * Project #4
 * CS 2334, Section 010
 * April 20, 2015
 */
public class CitySelectionView extends View {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * A list of cities for display
	 */
	private JList<City> cityList;
	
	/**
	 * A scrolling pane for viewing cityList
	 */
	private JScrollPane cityScrollPane;
	
	/**
	 * The button used to enter data
	 */
	private JButton enterButton= new JButton("Enter");
	
	/**
	 * Constructor for CitySelectionView. this contains all city within the state
	 * @param the State from which to grab the cities
	 */
	public CitySelectionView(State state)
	{
		super();
		this.setLayout(new GridLayout(2, 1));
		cityList= new JList<City>(state.getCities().toArray(new City[0]));
		cityScrollPane= new JScrollPane(cityList);
		this.add(cityScrollPane);
		cityList.setSelectedIndex(0);
		this.add(enterButton);
	}
	
	public City getSelectedCity()
	{
		return cityList.getSelectedValue();
	}
	
	public JButton getEnterButton()
	{
		return enterButton;
	}

}
