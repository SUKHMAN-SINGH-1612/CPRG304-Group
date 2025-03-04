ReadMe.txt
Project Overview:
This program is a sorting application designed to sort geometric shapes based on specific properties (height, base area, volume). The program supports six sorting algorithms: Bubble, Insertion, Selection, Merge, Quick, and a custom algorithm chosen by your group (Counting Sorting). The sorting is done in descending order using the Comparable and Comparator interfaces to compare shapes.
Prerequisites:
Java 8 or higher
Eclipse IDE (Recommended for running and debugging)
The program is packaged as a .jar file.
Setup Instructions:
Install Java:
Download and install Java 8 or higher from the official Oracle website if not already installed: Java Downloads.
Set up the JAVA_HOME environment variable if necessary.
Install Eclipse IDE (if not already installed):
Download Eclipse IDE for Java Developers from Eclipse Downloads.
Follow the installation instructions for your operating system.
Import the Project into Eclipse:
Open Eclipse.
Go to File > Import > General > Existing Projects into Workspace.
Browse to the folder where the project files are located, select the project, and click Finish.
Build and Export:
Once the project is imported, build it in Eclipse by selecting Project > Build Project.
You can export the project as a .jar file by going to File > Export > Java > Runnable JAR file and follow the instructions. Ensure that your sorting application is correctly packaged in Sort.jar.
Usage Instructions:
Running the Program:
To run the program from the command line, use the following format:
java -jar Sort.jar -f res/file_name -tv -sb
-f or -F specifies the input file path (e.g., shapes1.txt).
-t or -T specifies the comparison type:
v for volume
h for height
a for base area
-s or -S specifies the sorting algorithm:
b for Bubble Sort
s for Selection Sort
i for Insertion Sort
m for Merge Sort
q for Quick Sort
z for Counting Sort
Example Command:
java -jar Sort.jar -f res/shapes1.txt -Tv -Sq
This command sorts the shapes in the file shapes1.txt by volume in descending order using Quick Sort.
Notes:
Ensure that the file path provided is correct and accessible.
Command line arguments are case-insensitive.
The program will output the sorting results to the console, including the first sorted value, last sorted value, and every thousandth value in between.
Handling Errors:
If an incorrect argument is provided, the program will display an error message with guidance on how to correct the input.
Performance:
The program will display the time taken for each sorting algorithm to execute, in milliseconds.