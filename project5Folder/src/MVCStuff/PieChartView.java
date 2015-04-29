package MVCStuff;

import java.awt.event.ActionEvent;

import otherClasses.Pie;

/**

 * Project #4
 * CS 2334, Section 010
 * April 20, 2015

 *The view for displaying a pie chart
 */
public class PieChartView extends View {
	
	
	
	/**
	 * The pie to maket this view with
	 */
	Pie pie;
	
	/**
	 * Constructor for PieChartView
	 * @param pie The pie to make the view for
	 */
	public PieChartView(Pie pie)
	{
		super();
		this.pie=pie;
		this.add(this.pie);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
	}
	
}