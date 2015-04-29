package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import otherClasses.HelperMethods;
import sports.SportsYear;
import sports.TeamSeason;
import countryComponents.Person;

public class DegreesofSeparationTest {

	@Test
	public void test() {
		//Create some people here
		Person person1=new Person("a");
		Person person2=new Person("b");
		Person person3=new Person("c");
		Person person4=new Person("d");
		Person person5=new Person("e");
		Person person6=new Person("f");
		
		//Put them in teams
		SportsYear year= new SportsYear(1998);
		TeamSeason season1= new TeamSeason("fred", "george", year);
		TeamSeason season2= new TeamSeason("harold", "irwin", year);
		TeamSeason season3= new TeamSeason("john", "karl", year);
		//Put them in teams here (//TODO)
		
		//Find the shortest distance between two of them
		HelperMethods helperMethods=new HelperMethods();
		helperMethods.getDegrees(person1, person6, person1);
		
		//assert here (//TODO)
	}

}
