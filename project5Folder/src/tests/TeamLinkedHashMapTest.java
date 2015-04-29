package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import otherClasses.TeamMate;
import sports.Team;
import sports.TeamLinkedHashMap;

public class TeamLinkedHashMapTest {

	@Test
	public void test() {
		TeamLinkedHashMap tLHM=new TeamLinkedHashMap();
		Team team=TeamMate.convertStringToTeam("Milwaukee Bucks; Milwaukee; WI; Luc Richard Mbah a Moute; Darington O'Neal Hobson; Tobias John Harris; Ekpedeme Friday Udoh; Shaun Livingston; Jon Leuer; Ersan Ilyasova; Beno Udrih; Andrew Michael Bogut; Jonathan Rodney Brockman; Brandon Jennings; Andrew Melvin Gooden; Michael Joseph Dunleavy; Carlos Francisco Delfino; Larry Sanders");
		Team team2=TeamMate.convertStringToTeam("Minnesota Timberwolves; Minneapolis; MN; Bradley Alan Miller; Darko Milicic; Malcolm T. Lee; Wayne Robert Ellington; Anthony Lamar Tolliver; Nikola Pekovic; Kevin Wesley Love; Martell Webster; Jose Juan Barea Mora; Lukas Robin Ridnour; Anthony Erwin Randolph; Michael Paul Beasley; Wesley JaMarr Johnson; Ricard Rubio I Vives; Derrick Williams");
		tLHM.addTeam(team);
		tLHM.addTeam(team2);
		team=(tLHM.get("Milwaukee Bucks"));
		System.out.println(team.getTeamMembers());
	}

}
