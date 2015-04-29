package countryComponents;

import java.util.Comparator;

/**
 * Project #3
 * CS 2334, Section 010
 * Feb 18, 2015
 * <p>
 * A comparator object that takes in two <code>Person</code>s and compares their <code>firstName</code>s
 * </p>
 * @version 1.0
 */
public class CompareFirstName implements Comparator<Person>{


	public int compare(Person person1, Person person2) {
		return person1.getFirstName().compareToIgnoreCase(person2.getFirstName());
	}

}
