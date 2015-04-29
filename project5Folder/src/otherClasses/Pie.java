package otherClasses;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JComponent;

import sports.TeamSeason;
import countryComponents.Person;
import countryComponents.PersonList;
import countryComponents.Sector;
/**
 * A Pie chart.
 */
public class Pie extends JComponent {
	
	/**
	 * The total weight of the pie (the sum of the pie's sectors' weights)
	 */
	private int weightSum=0;
	
	/**
	 * The name of the team whose members' ages are being plotted
	 */
	private String teamName="";
	
	/**
	 * The ages of the members of the pie chart
	 */
	private int ageArray[]= new int[120];
	
	/**
	 * The pieces of the pie chart
	 */
	private ArrayList<Sector> sectors=new ArrayList<Sector>();
	
	/**
	 * The default constructor for Pie charts. Sets the pie chart's size to 1024x1024
	 */
	public Pie()
	{
		this.setPreferredSize(new Dimension (1000, 1000));
	}
	
	public Pie(TeamSeason teamSeason)
	{
		this(teamSeason.getPlayers(), teamSeason.toString());
	}
	
	/**
	 * Creates a Pie chart from a personlist object
	 * @param personList the personList to construct the pie chart from
	 * @param teamName The name of the team that holds the personList. If this is just a general personList (i.e., not exclusively part of exclusively one team), then this value should be an empty String
	 */
	public Pie(PersonList personList, String teamName)
	{
		this();
		this.teamName=teamName;
		ArrayList<Person> personArrayList=personList.getPeople();
		for (Person person: personArrayList)
		{
			ageArray[person.getAge()]=ageArray[person.getAge()]+1;
			
		}
		//ageArray[100]=1;
		for (int i=0; i<ageArray.length; i++)
		{
			if (!(ageArray[i]==0))
			{
				int number=ageArray[i];
				String aName=(""+i);
				Sector aSector=new Sector(aName, number);
				sectors.add(aSector);
			}
		}
		
		for(Sector sector: sectors)
		{
			weightSum+=sector.getWeight();
		}
		for(Sector sector: sectors)
		{
			sector.setAngleExtent(((double)sector.getWeight())/weightSum*360);
			sector.setColor(new Color((int)(Math.random() * 0x1000000)));
		}
	}
	
	
	public void paint(Graphics g)
	{
		Graphics2D g2D=(Graphics2D)g;
		 RenderingHints renderingHints = new RenderingHints(
	             RenderingHints.KEY_TEXT_ANTIALIASING,
	             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	    g2D.setRenderingHints(renderingHints);
		Rectangle2D boundaries=this.getBounds();
		Rectangle2D boundingRectangle=new Rectangle2D.Double();
		if (boundaries.getWidth()<boundaries.getHeight())
			boundingRectangle=new Rectangle2D.Double(boundaries.getX(), boundaries.getY(), boundaries.getWidth(), boundaries.getWidth());
		else
			boundingRectangle=new Rectangle2D.Double(boundaries.getX(), boundaries.getY(), boundaries.getHeight(), boundaries.getHeight());
		g2D.draw(new Ellipse2D.Double(boundingRectangle.getX(), boundingRectangle.getY(), boundingRectangle.getWidth(), boundingRectangle.getHeight()));
		double currentAngle=0;
		for (Sector sector: sectors)
		{
			sector.setFrame(boundingRectangle);
			sector.setAngleStart(currentAngle);
			currentAngle+=sector.getAngleExtent();
			g2D.setColor(sector.getColor());
			g2D.fill(sector);
			double xChordMidpoint= (sector.getEndPoint().getX()+sector.getStartPoint().getX())/2.0;
			double yChordMidpoint= (sector.getEndPoint().getY()+sector.getStartPoint().getY())/2.0;
			if ((int)sector.getAngleExtent()==360)
				xChordMidpoint-= getParent().getWidth()/2;
			if ((int)sector.getAngleExtent()==180)
			{
				if (sector.contains(xChordMidpoint, yChordMidpoint-10))
						yChordMidpoint-=10.0;
				else
					yChordMidpoint+=10.0;
			}
			g2D.setColor(Color.BLACK);
			if(!sector.getName().equals("0"))
			g2D.drawString("Age: "+ sector.getName()+". Count: "+sector.getWeight()+"", (int)xChordMidpoint, (int)yChordMidpoint);
			else
				g2D.drawString("Age: Unknown"+". Count: "+sector.getWeight()+"", (int)xChordMidpoint, (int)yChordMidpoint);
			//if (teamName!="")
			g2D.drawString("The age data for the team of "+teamName, getParent().getWidth()/2, 20);
		}
	}
}
