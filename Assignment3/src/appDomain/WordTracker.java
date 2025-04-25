package appDomain;

import implementations.BSTree;
import implementations.BSTreeNode;
import utilities.Iterator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

// The WordTracker class manages tracking and processing of word occurrences in files.
public class WordTracker {
    
    // Path to the serialized repository file that stores the word tree
    private static final String REPOSITORY_FILE = "repository.ser";
    
    // The binary search tree (BST) used to store and search words
    private BSTree<Word> wordTree;
    
    // Constructor to initialize the WordTracker and load the repository
    public WordTracker() {
        loadRepository();
    }
    
    // Loads the repository from the serialized file, if it exists
    private void loadRepository() {
        File file = new File(REPOSITORY_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                // Deserialize the wordTree from the file
                wordTree = (BSTree<Word>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error loading repository: " + e.getMessage());
                // If there was an error, initialize an empty wordTree
                wordTree = new BSTree<>();
            }
        } else {
            // If the file does not exist, initialize an empty wordTree
            wordTree = new BSTree<>();
        }
    }
    
    // Saves the current state of the wordTree to the repository file
    private void saveRepository() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(REPOSITORY_FILE))) {
            // Serialize the wordTree and save it to the file
            oos.writeObject(wordTree);
        } catch (IOException e) {
            System.err.println("Error saving repository: " + e.getMessage());
        }
    }
    
    // Processes a file by reading its content and tracking word occurrences
    public void processFile(String filename) {
        try {
            // Read the entire content of the file as a string
            String content = new String(Files.readAllBytes(Paths.get(filename)));
            // Split the content into lines
            String[] lines = content.split("\\r?\\n");
            
            // Process each line of the file
            for (int i = 0; i < lines.length; i++) {
                String[] words = lines[i].split("\\s+"); // Split line into words
                
                // Process each word
                for (String word : words) {
                    // Remove any punctuation and convert word to lowercase
                    word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
                    if (!word.isEmpty()) {
                        Word wordObj = new Word(word); // Create a Word object
                        // Search for the word in the word tree
                        BSTreeNode<Word> node = wordTree.search(wordObj);
                        if (node == null) {
                            // If word not found, add it to the tree
                            wordTree.add(wordObj);
                            node = wordTree.search(wordObj); // Get the newly added node
                        }
                        // Add the current line number to the word's occurrences in the file
                        node.getElement().addOccurrence(filename, i + 1);
                    }
                }
            }
            
            // Save the updated word tree to the repository
            saveRepository();
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
    }
    
    // Prints words in a specified format
    public void printWords(PrintStream out, String format) {
        out.println("Displaying -" + format.substring(1) + " format");
        // Get an iterator for inorder traversal of the word tree
        Iterator<Word> iterator = wordTree.inorderIterator();
        
        // Iterate through all words in the tree
        while (iterator.hasNext()) {
            Word word = iterator.next();
            // Print the word information based on the specified format
            switch (format) {
                case "-pf":
                    printFiles(out, word); // Print only files containing the word
                    break;
                case "-pl":
                    printFilesAndLines(out, word); // Print files and line numbers
                    break;
                case "-po":
                    printFilesLinesAndFrequency(out, word); // Print files, line numbers, and frequency
                    break;
            }
        }
        out.println("\nNot exporting file.");
    }
    
    // Helper method to print the files where the word occurs
    private void printFiles(PrintStream out, Word word) {
        StringBuilder result = new StringBuilder();
        result.append("Key : ===").append(word.getWord()).append("=== ");
        
        boolean firstFile = true;
        // Iterate through each file where the word is found
        for (String filename : word.getOccurrences().keySet()) {
            if (!firstFile) {
                result.append(", ");
            }
            result.append("found in file: ").append(filename);
            firstFile = false;
        }
        out.println(result.toString());
    }
    
    // Helper method to print files and corresponding line numbers
    private void printFilesAndLines(PrintStream out, Word word) {
        StringBuilder result = new StringBuilder();
        result.append("Key : ===").append(word.getWord()).append("=== ");
        
        boolean firstFile = true;
        // Iterate through each file and its associated line numbers
        for (Entry<String, List<Integer>> entry : word.getOccurrences().entrySet()) {
            if (!firstFile) {
                result.append(", ");
            }
            result.append("found in file: ").append(entry.getKey())
                  .append(" on lines: ")
                  .append(entry.getValue().stream()
                         .map(String::valueOf)
                         .collect(Collectors.joining(","))); // Format line numbers
            firstFile = false;
        }
        out.println(result.toString());
    }
    
    // Helper method to print files, line numbers, and word frequency
    private void printFilesLinesAndFrequency(PrintStream out, Word word) {
        StringBuilder result = new StringBuilder();
        result.append("Key : ===").append(word.getWord()).append("=== number of entries: ")
              .append(word.getTotalOccurrences()); // Get total occurrences
        
        boolean firstFile = true;
        // Iterate through each file and its associated line numbers
        for (Entry<String, List<Integer>> entry : word.getOccurrences().entrySet()) {
            if (!firstFile) {
                result.append(", ");
            }
            result.append(" found in file: ").append(entry.getKey())
                  .append(" on lines: ")
                  .append(entry.getValue().stream()
                         .map(String::valueOf)
                         .collect(Collectors.joining(","))); // Format line numbers
            firstFile = false;
        }
        out.println(result.toString());
    }
    
    // Main method to execute the WordTracker functionality
    public static void main(String[] args) {
        // Check if the correct number of arguments are provided
        if (args.length < 2) {
            System.out.println("Usage: java -jar WordTracker.jar <input.txt> -pf/-pl/-po [-f<output.txt>]");
            return;
        }
        
        String inputFile = args[0]; // Input file to process
        String format = args[1]; // Output format (-pf, -pl, or -po)
        String outputFile = null;
        
        // Check if an output file is specified
        if (args.length > 2 && args[2].startsWith("-f")) {
            outputFile = args[2].substring(2); // Extract the file name
        }
        
        // Create a WordTracker instance and process the input file
        WordTracker tracker = new WordTracker();
        tracker.processFile(inputFile);
        
        try {
            // Determine where to output the results (console or file)
            PrintStream out = outputFile != null ? 
                new PrintStream(new FileOutputStream(outputFile)) : 
                System.out;
            
            // Print the word data in the specified format
            tracker.printWords(out, format);
            
            // Close the output file stream if output file is used
            if (outputFile != null) {
                out.close();
                System.out.println("Results have been written to " + outputFile);
            }
        } catch (IOException e) {
            System.err.println("Error writing output: " + e.getMessage());
        }
    }
}
