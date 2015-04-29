package countryComponents;

import java.awt.Color;
import java.awt.geom.Arc2D;

/**
 *An {@link Arc2D} object that will be a part of a <code>Pie</code> object. It has a weight (how large it should be on the pie) and a name.
 */
public class Sector extends Arc2D.Double{
	
	/**
	 * This sector's weight with respect to the pie chart
	 */
	private int weight;
	
	/**
	 * This sector's name
	 */
	private String name;
	
	/**
	 * The <code>Color</code> that the <code>Graphics2D</code> Object will use to draw this <code>Sector</code>
	 */
	private Color color;
	
	/**
	 * Constructor for this <code>Sector</code> that assigns the following arguments to this <code>Sector</code>'s <code>name</code> and <code>weight</code>. 
	 * @param name The name of the group this sector represents
	 * @param weight Corresponds to amount of the the pie occupied
	 */
	public Sector(String name, int weight)
	{
		super(Arc2D.PIE);
		this.weight=weight;
		this.name=name;
	}
	
	/**
	 * Getter for this <code>Sector</code>'s weight
	 * @return weight
	 */
	public int getWeight()
	{
		return weight;
	}
	
	/**
	 * Getter for this <code>Sector</code>'s name
	 * @return The name of this sector
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Getter for this <code>Sector</code>'s color
	 * @return The color to be used to draw this object
	 */
	public Color getColor()
	{
		return color;
	}
	
	/**
	 * Setter for this <code>Sector</code>'s color
	 * @param color The color to be used to draw this object
	 */
	public void setColor(Color color)
	{
		this.color=color;
	}
	
	
	
}
