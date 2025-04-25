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

public class WordTracker {
    private static final String REPOSITORY_FILE = "repository.ser";
    private BSTree<Word> wordTree;
    
    public WordTracker() {
        loadRepository();
    }
    
    private void loadRepository() {
        File file = new File(REPOSITORY_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                wordTree = (BSTree<Word>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error loading repository: " + e.getMessage());
                wordTree = new BSTree<>();
            }
        } else {
            wordTree = new BSTree<>();
        }
    }
    
    private void saveRepository() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(REPOSITORY_FILE))) {
            oos.writeObject(wordTree);
        } catch (IOException e) {
            System.err.println("Error saving repository: " + e.getMessage());
        }
    }
    
    public void processFile(String filename) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filename)));
            String[] lines = content.split("\\r?\\n");
            
            for (int i = 0; i < lines.length; i++) {
                String[] words = lines[i].split("\\s+");
                for (String word : words) {
                    // Remove punctuation and convert to lowercase
                    word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
                    if (!word.isEmpty()) {
                        Word wordObj = new Word(word);
                        BSTreeNode<Word> node = wordTree.search(wordObj);
                        if (node == null) {
                            wordTree.add(wordObj);
                            node = wordTree.search(wordObj);
                        }
                        node.getElement().addOccurrence(filename, i + 1);
                    }
                }
            }
            
            saveRepository();
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
    }
    
    public void printWords(PrintStream out, String format) {
        out.println("Displaying -" + format.substring(1) + " format");
        Iterator<Word> iterator = wordTree.inorderIterator();
        while (iterator.hasNext()) {
            Word word = iterator.next();
            switch (format) {
                case "-pf":
                    printFiles(out, word);
                    break;
                case "-pl":
                    printFilesAndLines(out, word);
                    break;
                case "-po":
                    printFilesLinesAndFrequency(out, word);
                    break;
            }
        }
        out.println("\nNot exporting file.");
    }
    
    private void printFiles(PrintStream out, Word word) {
        StringBuilder result = new StringBuilder();
        result.append("Key : ===").append(word.getWord()).append("=== ");
        
        boolean firstFile = true;
        for (String filename : word.getOccurrences().keySet()) {
            if (!firstFile) {
                result.append(", ");
            }
            result.append("found in file: ").append(filename);
            firstFile = false;
        }
        out.println(result.toString());
    }
    
    private void printFilesAndLines(PrintStream out, Word word) {
        StringBuilder result = new StringBuilder();
        result.append("Key : ===").append(word.getWord()).append("=== ");
        
        boolean firstFile = true;
        for (Entry<String, List<Integer>> entry : word.getOccurrences().entrySet()) {
            if (!firstFile) {
                result.append(", ");
            }
            result.append("found in file: ").append(entry.getKey())
                  .append(" on lines: ")
                  .append(entry.getValue().stream()
                         .map(String::valueOf)
                         .collect(Collectors.joining(",")));
            firstFile = false;
        }
        out.println(result.toString());
    }
    
    private void printFilesLinesAndFrequency(PrintStream out, Word word) {
        StringBuilder result = new StringBuilder();
        result.append("Key : ===").append(word.getWord()).append("=== number of entries: ")
              .append(word.getTotalOccurrences());
        
        boolean firstFile = true;
        for (Entry<String, List<Integer>> entry : word.getOccurrences().entrySet()) {
            if (!firstFile) {
                result.append(", ");
            }
            result.append(" found in file: ").append(entry.getKey())
                  .append(" on lines: ")
                  .append(entry.getValue().stream()
                         .map(String::valueOf)
                         .collect(Collectors.joining(",")));
            firstFile = false;
        }
        out.println(result.toString());
    }
    
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java -jar WordTracker.jar <input.txt> -pf/-pl/-po [-f<output.txt>]");
            return;
        }
        
        String inputFile = args[0];
        String format = args[1];
        String outputFile = null;
        
        if (args.length > 2 && args[2].startsWith("-f")) {
            outputFile = args[2].substring(2);
        }
        
        WordTracker tracker = new WordTracker();
        tracker.processFile(inputFile);
        
        try {
            PrintStream out = outputFile != null ? 
                new PrintStream(new FileOutputStream(outputFile)) : 
                System.out;
            
            tracker.printWords(out, format);
            
            if (outputFile != null) {
                out.close();
                System.out.println("Results have been written to " + outputFile);
            }
        } catch (IOException e) {
            System.err.println("Error writing output: " + e.getMessage());
        }
    }
} 