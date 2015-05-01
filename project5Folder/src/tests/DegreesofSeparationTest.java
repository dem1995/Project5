package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import MVCStuff.CountryModel;
import MVCStuff.DegreesOfSeparationListView;
import otherClasses.HelperMethods;
import sports.SportsStuff;
import sports.SportsYear;
import sports.TeamSeason;
import countryComponents.Person;

public class DegreesofSeparationTest {

	//A test involving one direct path
	@Test
	public void testInvolvingOneDirectPath() {
		
		CountryModel countryModel= new CountryModel();
		SportsStuff.prepareFromCSVUsingCountryModel("TestCSVFiles/testoneroute.csv", countryModel);
		DegreesOfSeparationListView dosView= new DegreesOfSeparationListView(countryModel.findPerson("Person A"), countryModel.findPerson("Person C"));
		//dosView.getDegrees(countryModel.findPerson("Player 1"), countryModel.findPerson("Player 2"), TeamSeason.makeEmptyTeamSeason());
		ArrayList<ArrayList<Person>> journeys= dosView.dos.getJourneys();
		System.out.println(journeys);
		assertEquals(new Integer(2), new Integer(dosView.dos.getJourneys().get(0).size()-1));
	}
	
	//A test involving two direct paths
	@Test
	public void testInvolvingTwoDirectPaths() {
		
		CountryModel countryModel= new CountryModel();
		SportsStuff.prepareFromCSVUsingCountryModel("TestCSVFiles/testTwoRoutes.csv", countryModel);
		DegreesOfSeparationListView dosView= new DegreesOfSeparationListView(countryModel.findPerson("Person A"), countryModel.findPerson("Person E"));
		//dosView.getDegrees(countryModel.findPerson("Player 1"), countryModel.findPerson("Player 2"), TeamSeason.makeEmptyTeamSeason());
		ArrayList<ArrayList<Person>> journeys= dosView.dos.getJourneys();
		System.out.println(journeys); //For reasons that I'm not taking the time to look at, for some reason this test duplicates something that the normal code doesn't.
		assertEquals(new Integer(3), new Integer(dosView.dos.getJourneys().get(0).size()-1));
	}
	

}
