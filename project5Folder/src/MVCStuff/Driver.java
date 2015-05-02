package MVCStuff;


/**
 * The driver for the program. Sets up the views/controller/model
 * Project #5
 * CS 2334, Section 010
 * May 1, 2015
 */
public class Driver {
	/**
	 * The country from which the rest of the classes will derive their information from
	 */
	private CountryModel model=new CountryModel();
	
	/**
	 * The first view to show up.
	 */
	private SelectionView selectionView=new SelectionView();
	
	/**
	 * The controller
	 */
	private TeamMateController controller= new TeamMateController();
	
	/**
	 * Constructor method for driver. Views and models should be set for controllers here, and models should be set for views here.
	 */
	public Driver()
	{
		selectionView.setModel(model);
		controller.setModel(model);
		controller.setSelectionView(selectionView);
	}
	
	/**
	 * The main method
	 * @param args The program arguments
	 */
	public static void main (String[] args)
	{
		new Driver();
	}
	

}
