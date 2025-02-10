
package exercise1;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The Driver class contains the main method to demonstrate sorting of Student objects.
 */
public class Driver {

    /**
     * The main method creates a list of Student objects, prints the original list,
     * sorts the list by age, prints the sorted list, sorts the list by name, and prints the sorted list.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        // Create an ArrayList of Student objects
        ArrayList<Student> studs = new ArrayList<>();
        studs.add(new Student("Smith", 34));
        studs.add(new Student("Johnson", 21));
        studs.add(new Student("Williams", 67));
        studs.add(new Student("Brown", 53));
        studs.add(new Student("Jones", 48));
        studs.add(new Student("Miller", 36));
        studs.add(new Student("Davis", 44));
        studs.add(new Student("Wilson", 52));
        studs.add(new Student("Anderson", 34));
        studs.add(new Student("Moore", 33));

        // Print the original list of students
        System.out.println("Original list:");
        for (Student s : studs)
            System.out.println(s);

        // Sort the list of students by age using the ageComparator
        Collections.sort(studs, Student.ageComparator);

        // Print the list of students sorted by age
        System.out.println("\nSorted by age:");
        for (Student s : studs)
            System.out.println(s);

        // Sort the list of students by name using the compareTo method
        Collections.sort(studs, Student::compareTo);

        // Print the list of students sorted by name
        System.out.println("\nSorted by name:");
        for (Student s : studs)
            System.out.println(s);
    }
}
