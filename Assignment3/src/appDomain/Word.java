package appDomain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Word implements Comparable<Word>, Serializable {
    private static final long serialVersionUID = 1L;
    private String word;
    private Map<String, List<Integer>> occurrences; // filename -> list of line numbers
    
    public Word(String word) {
        this.word = word.toLowerCase();
        this.occurrences = new HashMap<>();
    }
    
    public void addOccurrence(String filename, int lineNumber) {
        occurrences.computeIfAbsent(filename, k -> new ArrayList<>()).add(lineNumber);
    }
    
    public String getWord() {
        return word;
    }
    
    public Map<String, List<Integer>> getOccurrences() {
        return occurrences;
    }
    
    public int getTotalOccurrences() {
        return occurrences.values().stream()
                .mapToInt(List::size)
                .sum();
    }
    
    @Override
    public int compareTo(Word other) {
        return this.word.compareTo(other.word);
    }
    
    @Override
    public String toString() {
        return word;
    }
} 