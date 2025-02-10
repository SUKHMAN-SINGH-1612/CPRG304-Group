
package exercise1;

import java.util.Comparator;

/**
 * The Student class represents a student with a name and age.
 * It implements the Comparable interface to allow comparison by name.
 */
public class Student implements Comparable<Student> {
    private String name;
    private int age;

    /**
     * Constructs a new Student with the specified name and age.
     *
     * @param name the name of the student
     * @param age  the age of the student
     */
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Returns the name of the student.
     *
     * @return the name of the student
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the age of the student.
     *
     * @return the age of the student
     */
    public int getAge() {
        return age;
    }

    /**
     * Compares this student to another student by name.
     *
     * @param other the student to be compared
     * @return a negative integer, zero, or a positive integer as this student's name
     *         is less than, equal to, or greater than the specified student's name
     */
    @Override
    public int compareTo(Student other) {
        return name.compareTo(other.name);
    }

    /**
     * A comparator to compare students by age. If ages are equal, compares by name.
     */
    public static Comparator<Student> ageComparator = new Comparator<Student>() {
        @Override
        public int compare(Student s1, Student s2) {
            if (s1.getAge() == s2.getAge())
                return s1.getName().compareTo(s2.getName());
            else
                return Integer.compare(s1.getAge(), s2.getAge());
        }
    };

    /**
     * Returns a string representation of the student.
     *
     * @return a string representation of the student in the format "name age"
     */
    @Override
    public String toString() {
        return name + " " + age;
    }
}
