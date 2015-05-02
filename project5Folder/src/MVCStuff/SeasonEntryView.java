package MVCStuff;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import countryComponents.City;
import sports.SportsYear;
import sports.Team;
import sports.TeamSeason;
/**
 * Project #4
 * CS 2334, Section 010
 * April 20, 2015
 */
public class SeasonEntryView extends View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The text field for the name of this season
	 */
	private JTextField nameField=new JTextField();
	
	/**
	 * The team this season belongs to
	 */
	private Team team;
	
	/**
	 * The year the Season being added occurs during
	 */
	private SportsYear sportsYear;
	
	/**
	 * A list of the Cities from which to choose a City to assign this Season to
	 */
	private JList<City> cityList;
	
	/**
	 * The button used to submit a Season
	 */
	private JButton enterButton= new JButton("Enter");
	
	/**
	 * Constructor for PieChartView
	 * @param team The team this season belongs to
	 * @param sportsYear The year this season occurred during
	 * @param countryModel The model that this season is a part of
	 */
	public SeasonEntryView(Team team, SportsYear sportsYear, CountryModel countryModel)
	{
		super();
		this.team=team;
		this.sportsYear=sportsYear;
		this.countryModel=countryModel;
		this.setLayout(new GridLayout(2,3));
		this.add(new JLabel("Team Name"));
		this.add(new JLabel("City"));
		this.add(new JLabel());
		this.add(nameField);
		cityList=new JList<City>(countryModel.getCities().toArray(new City[0]));
		cityList.setSelectedIndex(0);
		cityList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.add(new JScrollPane(cityList));
		this.add(enterButton);
		this.validate();
	}
	
	/**
	 * Retrieves the button used to submit the Season
	 * @return the button used to submit the Season
	 */
	public JButton getEnterButton()
	{
		return enterButton;
	}
	
	/**
	 * Gets a Season created from the fields of this View
	 * @return a Season created from the fields of this View
	 */
	public TeamSeason getTeamSeason()
	{
		return new TeamSeason(nameField.getText(), team, sportsYear, cityList.getSelectedValue(), cityList.getSelectedValue().getState());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO	
	}
	
}
