Word Tracker Program
===================

Description
-----------
Word Tracker is a Java application that reads text files, collects and stores unique words, and tracks their occurrences across multiple files. The program uses a Binary Search Tree (BST) to efficiently store and retrieve word information.

Installation
-----------
1. Ensure you have Java 8 or later installed on your system
2. Download the WordTracker.jar file
3. Place the jar file in your desired directory

Usage
-----
The program can be run from the command line using the following syntax:

java -jar WordTracker.jar <input.txt> -pf/-pl/-po [-f<output.txt>]

Where:
- <input.txt> is the path to the text file you want to process
- -pf/-pl/-po are the output format options:
  * -pf: Prints words with files
  * -pl: Prints words with files and line numbers
  * -po: Prints words with files, line numbers, and frequency
- [-f<output.txt>] is an optional argument to save output to a file

Examples
--------
1. Basic usage (prints to console):
   java -jar WordTracker.jar test1.txt -pf

2. Save output to file:
   java -jar WordTracker.jar test1.txt -po -fresults.txt

3. Process multiple files:
   java -jar WordTracker.jar test1.txt -pf
   java -jar WordTracker.jar test2.txt -pl

Output Formats
-------------
1. -pf format:
   Key : ===word=== found in file: filename

2. -pl format:
   Key : ===word=== found in file: filename on lines: 1,2,3

3. -po format:
   Key : ===word=== number of entries: X found in file: filename on lines: 1,2,3

Notes
-----
- The program maintains a repository.ser file to store word information between runs
- Words are case-insensitive and punctuation is removed
- Each word occurrence is tracked with its file and line number
- The program can process multiple files and maintain a cumulative word database

Error Handling
-------------
- The program will display error messages for:
  * Invalid command line arguments
  * File not found
  * File access issues
  * Serialization/deserialization errors

Requirements
-----------
- Java 8 or later
- Command line access
- Read/write permissions in the working directory
