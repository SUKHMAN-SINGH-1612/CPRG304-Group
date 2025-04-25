package appDomain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// The Word class represents a word and its occurrences in different files.
public class Word implements Comparable<Word>, Serializable {
    
    // Serial version UID for serialization compatibility
    private static final long serialVersionUID = 1L;

    // The actual word (stored in lowercase to standardize comparison)
    private String word;

    // A map to store occurrences of the word, where the key is the filename,
    // and the value is a list of line numbers where the word appears
    private Map<String, List<Integer>> occurrences; 
    
    // Constructor to initialize the word and its occurrences map
    public Word(String word) {
        this.word = word.toLowerCase(); // Store the word in lowercase for case-insensitive comparison
        this.occurrences = new HashMap<>(); // Initialize the map to track occurrences
    }
    
    // Method to add an occurrence of the word at a specific line in a specific file
    public void addOccurrence(String filename, int lineNumber) {
        // If the file is not already in the map, create a new list to store the line numbers
        occurrences.computeIfAbsent(filename, k -> new ArrayList<>()).add(lineNumber);
    }
    
    // Getter method to retrieve the word
    public String getWord() {
        return word;
    }
    
    // Getter method to retrieve all occurrences of the word across all files
    public Map<String, List<Integer>> getOccurrences() {
        return occurrences;
    }
    
    // Method to get the total number of occurrences of the word across all files
    public int getTotalOccurrences() {
        // Sum up the size of all the lists in the map, which represent the line numbers
        return occurrences.values().stream()
                .mapToInt(List::size) // Get the size of each list (number of occurrences in that file)
                .sum(); // Sum them up
    }
    
    // Overriding the compareTo method to compare words lexicographically
    @Override
    public int compareTo(Word other) {
        // Compare the words alphabetically (case-sensitive due to word being stored in lowercase)
        return this.word.compareTo(other.word);
    }
    
    // Overriding toString method to return the word as its string representation
    @Override
    public String toString() {
        return word;
    }
}
