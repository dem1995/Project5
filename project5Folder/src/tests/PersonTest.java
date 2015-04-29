package tests;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import countryComponents.Person;
import otherClasses.Pie;

public class PersonTest {

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
		//System.out.println(d.getYear());
		Person person=(new Person("fred", "smith", null));
	}

}
