package MVCStuff;
//Version 1.1 Final. Date April 12, 2015. 3:20 AM

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**

 * Project #4
 * CS 2334, Section 010
 * April 20, 2015

 *The basic View class that contains the methods/components that any View class should have. 
 *Abstract so as to prevent it being mistakenly used as a view with complete functionality. All 
 *classes that extend this should override actionPerformed and make a super call to this classes 
 *actionPerformed in that method.
 */
public abstract class View extends JFrame implements ActionListener 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The TeamMateModel used by this view
	 */
	protected CountryModel countryModel;

	/**
	 * The title of this window.
	 */
	private String windowTitle;
	
	/**
	 * Constructor that sets up this JFrame and makes it visible.
	 */
	public View()
	{
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setTitle(windowTitle);
		this.setAlwaysOnTop(true);
		setSize(500,200);
		setLocation(400, 200);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	/**
	 * Sets the TeamMateModel to be used by this view
	 * @param modelParam the TeamMateModel to be used by this view
	 */
	public void setModel(CountryModel modelParam)
	{
		countryModel=modelParam;
		if (countryModel!=null)
			countryModel.addActionListener(this); //registers this view as an actionListener for its model countryModel
		repaint();
	}
	
	/**
	 * Returns the TeamMateModel used by this view
	 * @return teamMateModel the TeamMateModel used by this view
	 */
	public CountryModel getModel()
	{
		return countryModel;
	}
}
