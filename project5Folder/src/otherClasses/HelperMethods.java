package otherClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import MVCStuff.CountryModel;
import countryComponents.Country;
import countryComponents.Person;
import sports.TeamSeason;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;











import countryComponents.Country;


/**
 * Project #5
 * CS 2334, Section 010
 * April 24, 2015
 * Methods to help the the other classes. Also contains stuff for degrees of separation
 */
public class HelperMethods {
	
	
/**
 * A deque to be added to/subtracted from when building degrees of separation routes
 */
private ArrayDeque<Person> deque;

/**
 * The paths of shortest degrees of separation
 */
private ArrayList<ArrayDeque<Person>> paths;

/**
 * The seasons that have already been checked for the getDegrees method
 */
private HashSet<TeamSeason> checkedSeasons;



	/**
	 * A method for getting the shortest degrees of separation paths between two people
	 * @param start The person to start from
	 * @param end	The destination person
	 * @param current	The intermediate person currently being checked
	 * @return whether or not a new shortest degrees of freedom path was found.
	 */
	public boolean getDegrees(Person start, Person end, Person current)
	{
		//TODO
		return true;
	}

	/**
	 * Method for converting a CSV file to a list of String objects in the form "Name, birthDate, birthCity, birthState" or "Name, birthDate, birthCity, birthState"
	 * @param fileName name of the CSV file to grab text from
	 * @return an ArrayList of the Strings, each of which was a line of the CSV file
	 * @throws Exception an exception
	 * @throws IOException due to use of BufferedReader
	 */
	public static ArrayList<String> convertCSVToStringList(String fileName) throws Exception
	{
		ArrayList<String> stringList = new ArrayList<String>();
		BufferedReader br=new BufferedReader(new FileReader(fileName));
		for (String lineOfCSV=""; lineOfCSV!=(null); lineOfCSV=br.readLine())
		{
			if(lineOfCSV!="")
			stringList.add(lineOfCSV);
		}
		br.close();
		if (stringList.isEmpty())
			{
			throw new Exception("CSV File was empty");
			}
		return stringList;
	}
	
	public static void saveToFile(CountryModel countryModel, String fileName)
	{
		ObjectOutputStream stream = null;
		try 
		{
			 stream = new ObjectOutputStream(new FileOutputStream(fileName));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}		
		try 
		{
			stream.writeObject(countryModel);
			stream.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		System.out.println("File printed");
		
	}

	public static CountryModel readFile(String fileName)
	{
		CountryModel readIn=new CountryModel();
		
		FileInputStream fis = null;
		try 
		{
			fis = new FileInputStream(fileName);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	    ObjectInputStream ois = null;
		try 
		{
			ois = new ObjectInputStream(fis);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

	    try 
	    {
			readIn = (CountryModel)ois.readObject();
		} 
	    catch (ClassNotFoundException | IOException e) 
	    {
			e.printStackTrace();
		}
	    
	    try 
	    {
			ois.close();
		} catch (IOException e) 
	    {
			e.printStackTrace();
		}
	    System.out.println("File read");
	    return readIn; 
		
	}
}