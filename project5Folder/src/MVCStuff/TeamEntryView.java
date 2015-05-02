package MVCStuff;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JTextField;

import sports.Team;
/**
 * Project #4
 * CS 2334, Section 010
 * April 20, 2015
 */
public class TeamEntryView extends View {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField teamNameField= new JTextField();
	
	private JButton enterButton=new JButton ("Enter team");
	
	
	/**
	 * Constructor for TeamEntryView
	 */
	public TeamEntryView()
	{
		super();
		this.setLayout(new GridLayout(2, 1));
		this.add(teamNameField);
		this.add(enterButton);
		this.validate();
		//TODO
	}
	
	public JButton getEnterButton()
	{
		return enterButton;
	}
	
	public Team getTeam()
	{
		return new Team(teamNameField.getText());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO	
	}
	
}
