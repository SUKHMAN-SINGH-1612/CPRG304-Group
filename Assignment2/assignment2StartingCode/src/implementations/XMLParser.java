package implementations;

import java.io.*;
import java.util.regex.*;
import java.util.Stack;

/**
 * The XMLParser class is responsible for parsing an XML file and checking if it is well-formed.
 * It checks for common XML syntax errors such as mismatched tags, extra '>', unclosed tags, and invalid self-closing tags.
 * It uses regular expressions to identify XML tags and validate the structure of the document.
 */
public class XMLParser {

    /**
     * The TagInfo class is used to store information about an XML tag, specifically its name
     * and the line number where it appears in the XML file.
     */
    static class TagInfo {
        String name;          // Name of the XML tag
        int lineNumber;       // Line number where the tag appears

        /**
         * Constructs a TagInfo object with a tag name and line number.
         *
         * @param name The name of the XML tag
         * @param lineNumber The line number where the tag appears
         */
        TagInfo(String name, int lineNumber) {
            this.name = name;
            this.lineNumber = lineNumber;
        }
    }

    /**
     * The main method reads an XML file and checks its well-formedness.
     * It performs various checks on XML tags, such as matching opening and closing tags,
     * validating self-closing tags, and ensuring there are no malformed or unclosed tags.
     *
     * @param args Command line arguments, where the first argument is the XML filename to parse
     */
    public static void main(String[] args) {
        // If no filename is provided, print usage instructions
        if (args.length < 1) {
            System.out.println("Usage: java XMLParser <filename.xml>");
            return;
        }

        String filename = args[0];  // The XML file to parse
        Stack<TagInfo> tagStack = new Stack<>();  // Stack to track opening tags
        boolean isWellFormed = true;  // Flag to track the overall well-formedness of the XML

        // Try-with-resources block to open the file and read it line by line
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 0;  // Line number counter

            // Read each line of the file
            while ((line = br.readLine()) != null) {
                lineNumber++;  // Increment the line number
                line = line.trim();  // Remove leading and trailing whitespaces

                // Skip lines that are empty or contain XML declarations/comments
                if (line.isEmpty() || line.startsWith("<?xml") || line.startsWith("<!--")) {
                    continue;
                }

                // Check for malformed lines containing extra '>'
                if (line.matches(".*>[^<]*>.*")) {
                    System.out.printf("Error at line %d: Malformed tag (extra '>') - \"%s\"%n", lineNumber, line);
                    isWellFormed = false;
                }

                // Regular expression pattern to match XML tags (including self-closing and with attributes)
                Pattern tagPattern = Pattern.compile("</?([A-Za-z][A-Za-z0-9-]*)(\\s+[^>]*?)?(/?)>");
                Matcher matcher = tagPattern.matcher(line);

                // Process all matches of tags in the line
                while (matcher.find()) {
                    String fullTag = matcher.group(0);  // Full tag (e.g., <Tag> or </Tag>)
                    String tagName = matcher.group(1);  // Tag name (e.g., Tag)
                    boolean isClosing = fullTag.startsWith("</");  // Check if it's a closing tag
                    boolean isSelfClosing = fullTag.endsWith("/>");  // Check if it's a self-closing tag

                    // Handle self-closing tags
                    if (isSelfClosing) {
                        // Ensure the self-closing tag has proper syntax
                        if (!fullTag.matches("<\\S+?\\s.*?/>")) {
                            System.out.printf("Error at line %d: Invalid self-closing tag - \"%s\"%n", lineNumber, fullTag);
                            isWellFormed = false;
                        }
                        continue;  // Skip further processing for self-closing tags
                    }

                    // Handle closing tags
                    if (isClosing) {
                        // Ensure there is a corresponding opening tag for the closing tag
                        if (tagStack.isEmpty()) {
                            System.out.printf("Error at line %d: Closing tag </%s> with no opening tag%n", lineNumber, tagName);
                            isWellFormed = false;
                        } else {
                            TagInfo expected = tagStack.pop();  // Pop the expected opening tag from the stack
                            // Check if the closing tag matches the expected opening tag
                            if (!expected.name.equals(tagName)) {
                                System.out.printf("Error at line %d: Mismatched </%s> (expected </%s> opened at line %d)%n",
                                        lineNumber, tagName, expected.name, expected.lineNumber);
                                isWellFormed = false;
                            }
                        }
                    }
                    // Handle opening tags
                    else {
                        // Push the opening tag onto the stack with its line number
                        tagStack.push(new TagInfo(tagName, lineNumber));
                    }
                }

                // Detect lines with unclosed tag fragments (i.e., '<' without '>')
                if (line.contains("<") && !line.contains(">")) {
                    System.out.printf("Error at line %d: Unclosed tag fragment - \"%s\"%n", lineNumber, line);
                    isWellFormed = false;
                }
            }

            // Report remaining unclosed tags after processing all lines
            while (!tagStack.isEmpty()) {
                TagInfo unclosed = tagStack.pop();
                System.out.printf("Error: Unclosed <%s> opened at line %d%n", unclosed.name, unclosed.lineNumber);
                isWellFormed = false;
            }

            // If no errors were found, print that the XML is well-formed
            if (isWellFormed) {
                System.out.println("XML is well-formed");
            }

        } catch (IOException e) {
            // Handle errors that may occur while reading the file
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
