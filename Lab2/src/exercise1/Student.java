package exercise1;
import java.util.Comparator;

public class Student implements Comparable<Student>
{
	private String name;
	private int age;
	
	public Student( String name, int age )
	{
		this.name = name;
		this.age = age;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getAge()
	{
		return age;
	}
	
	@Override
	public int compareTo(Student other)
	{
		return name.compareTo(other.name);
	}

	public static Comparator<Student> ageComparator = new Comparator<Student>()
	{
		@Override
		public int compare(Student s1, Student s2)
		{
			if (s1.getAge() == s2.getAge())
				return s1.getName().compareTo(s2.getName());
			else
				return Integer.compare(s1.getAge(), s2.getAge());
		}
	};
	
	@Override
	public String toString()
	{
		return name + " " + age;
	}
	
}
