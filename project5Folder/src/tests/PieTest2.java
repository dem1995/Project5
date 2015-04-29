package tests;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JFrame;

import org.junit.Test;

import otherClasses.Pie;
import countryComponents.Person;
import countryComponents.PersonList;

public class PieTest2 {

	@Test
	public void test() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d=null;
		Date d2=null;
		Date d3=null;
		Pie pie=null;
		try{
		d = sdf.parse("21/12/1990");
		d2 = sdf.parse("04/12/1994");
		d3 = sdf.parse("03/12/1999");
		}catch(Exception e)
		{
			System.out.println("parse error");
		}
		PersonList personList=new PersonList();
		personList.addPerson(new Person("fred", "smith", d, d3));
		personList.addPerson(new Person("george", "zebra", d, d2));
		personList.addPerson(new Person("harold", "cobra", d2));
		try
		{
			pie= new Pie(personList, "test");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println(personList.getPerson(0).getAge());
		System.out.println(personList);
		JFrame jFrame=new JFrame();
		//jFrame.setPreferredSize(new Dimension(1024, 1024));
		jFrame.add(pie);
		jFrame.pack();
		jFrame.setVisible(true);
		System.out.println("Press enter");
		Scanner reader=new Scanner(System.in);
		reader.nextLine();
		//fail("Not yet implemented");
	}

}
