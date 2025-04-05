package implementations;

import java.io.*;
import java.util.regex.*;
import java.util.Stack;

public class XMLParser {
    static class TagInfo {
        String name;
        int lineNumber;

        TagInfo(String name, int lineNumber) {
            this.name = name;
            this.lineNumber = lineNumber;
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java XMLParser <filename.xml>");
            return;
        }

        String filename = args[0];
        Stack<TagInfo> tagStack = new Stack<>();
        boolean isWellFormed = true;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                // Skip XML declaration/comments
                if (line.isEmpty() || line.startsWith("<?xml") || line.startsWith("<!--")) {
                    continue;
                }

                // Detect malformed lines with extra '>' (e.g., <Category>>)
                if (line.matches(".*>[^<]*>.*")) {
                    System.out.printf("Error at line %d: Malformed tag (extra '>') - \"%s\"%n", lineNumber, line);
                    isWellFormed = false;
                }

                // Improved regex for tags (supports attributes and self-closing)
                Pattern tagPattern = Pattern.compile("</?([A-Za-z][A-Za-z0-9-]*)(\\s+[^>]*?)?(/?)>");
                Matcher matcher = tagPattern.matcher(line);

                while (matcher.find()) {
                    String fullTag = matcher.group(0);
                    String tagName = matcher.group(1);
                    boolean isClosing = fullTag.startsWith("</");
                    boolean isSelfClosing = fullTag.endsWith("/>");

                    // Handle self-closing tags
                    if (isSelfClosing) {
                        if (!fullTag.matches("<\\S+?\\s.*?/>")) { // Validate proper syntax
                            System.out.printf("Error at line %d: Invalid self-closing tag - \"%s\"%n", lineNumber, fullTag);
                            isWellFormed = false;
                        }
                        continue;
                    }

                    // Handle closing tags
                    if (isClosing) {
                        if (tagStack.isEmpty()) {
                            System.out.printf("Error at line %d: Closing tag </%s> with no opening tag%n", lineNumber, tagName);
                            isWellFormed = false;
                        } else {
                            TagInfo expected = tagStack.pop();
                            if (!expected.name.equals(tagName)) {
                                System.out.printf("Error at line %d: Mismatched </%s> (expected </%s> opened at line %d)%n",
                                        lineNumber, tagName, expected.name, expected.lineNumber);
                                isWellFormed = false;
                            }
                        }
                    }
                    // Handle opening tags
                    else {
                        tagStack.push(new TagInfo(tagName, lineNumber));
                    }
                }

                // Detect unclosed tag starts
                if (line.contains("<") && !line.contains(">")) {
                    System.out.printf("Error at line %d: Unclosed tag fragment - \"%s\"%n", lineNumber, line);
                    isWellFormed = false;
                }
            }

            // Report remaining unclosed tags
            while (!tagStack.isEmpty()) {
                TagInfo unclosed = tagStack.pop();
                System.out.printf("Error: Unclosed <%s> opened at line %d%n", unclosed.name, unclosed.lineNumber);
                isWellFormed = false;
            }

            if (isWellFormed) {
                System.out.println("XML is well-formed");
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}