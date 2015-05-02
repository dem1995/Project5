package MVCStuff;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import sports.SportsYear;
import sports.Team;

public class YearSelectionView extends View{
	
	JButton enterButton= new JButton("Submit");
	JDialog yearDialog;
	JList<SportsYear> yearsList;
	
	public YearSelectionView(Team team, CountryModel countryModel)
	{
		super();
		this.countryModel=countryModel;
		this.setLayout(new GridLayout(2, 1));
		yearsList=new JList<SportsYear>(countryModel.getSportsStuff().getYears().values().toArray(new SportsYear[0]));
		this.add(new JScrollPane(yearsList));
		this.add(enterButton);
		
	}
	
	public JButton getEnterButton()
	{
		return enterButton;
	}
	
	public ArrayList<SportsYear> getSelectedYears()
	{
		return new ArrayList<SportsYear>(yearsList.getSelectedValuesList());
	}
	
	public void setModal(boolean modality)
	{
		yearDialog.setModal(modality);
	}

}
