package appDomain;

import java.util.Scanner;
import shapes.*;
import shapeManager.*;

public class AppDriver
{

	public static void main( String[] args )
	{
		// TODO Auto-generated method stub

		// refer to demo001 BasicFileIO.java for a simple example on how to
		// read data from a text file

		// refer to demo01 Test.java for an example on how to parse command
		// line arguments and benchmarking tests

		// refer to demo02 Student.java for comparable implementation, and
		// NameCompare.java or GradeCompare for comparator implementations

		// refer to demo02 KittySort.java on how to use a custom sorting
		// algorithm on a list of comparables to sort using either the
		// natural order (comparable) or other orders (comparators)
		
		String fileName = null;
	    Scanner scanner = new Scanner(System.in);
	    
	    for (int i = 0; i < args.length; i++) {
            if (args[i].equalsIgnoreCase("-f")) {
                if (i + 1 < args.length) {
                    fileName = args[i + 1];
                    i++; // Skip next argument (filename)
                } else {
                    System.out.println("The -f option requires a filename. Please enter the file path:");
                    fileName = scanner.nextLine().trim();
                }
                break; // Stop after finding -f
            }
        }

        // If -f not found in args, prompt user
        if (fileName == null) {
            System.out.println("Please enter the file path (use -f <filename> to skip this prompt next time):");
            fileName = scanner.nextLine().trim();
        }

        // Validate filename
        if (fileName.isEmpty()) {
            System.err.println("Error: Filename cannot be empty.");
            System.exit(1);
        }

        try {
            Shape[] shapes = ShapeFileReader.readShapesFromFile(fileName);
            System.out.println("Successfully loaded " + shapes.length + " shapes.");
            
            // Proceed to sorting/benchmarking...
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        } finally {
            scanner.close();
        }
    }
}
