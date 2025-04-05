package implementations;

import java.io.*;
import java.util.regex.*;

public class XMLParser {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java XMLParser <filename.xml>");
            return;
        }

        String filename = args[0];
        MyStack<String> tagStack = new MyStack<>();
        boolean isWellFormed = true;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                // Skip XML declaration, comments, and empty lines
                if (line.isEmpty() || line.startsWith("<?xml") || line.startsWith("<!--")) {
                    continue;
                }

                // Check for malformed opening tag with extra >
                if (line.matches(".*<[^>]+>[^<]*>.*")) {
                    String malformedTag = line.replaceAll(".*(<[^>]+>).*", "$1");
                    System.out.println("Error at line: " + lineNumber + " " + malformedTag + " is not constructed correctly.");
                    isWellFormed = false;
                    continue;
                }

                // Improved regex to handle tags with attributes and self-closing tags
                Pattern tagPattern = Pattern.compile("<(/?)([A-Za-z][A-Za-z0-9-]*)(\\s+[^>]*?)?(/?)>");
                Matcher matcher = tagPattern.matcher(line);

                while (matcher.find()) {
                    String isClosing = matcher.group(1);
                    String tagName = matcher.group(2);
                    String isSelfClosing = matcher.group(4);

                    // Skip processing instructions
                    if (tagName.startsWith("?")) {
                        continue;
                    }

                    if (!isSelfClosing.isEmpty()) {
                        // Self-closing tag - no action needed
                        continue;
                    }

                    if (!isClosing.isEmpty()) {
                        // Closing tag
                        if (tagStack.isEmpty()) {
                            System.out.println("Error at line: " + lineNumber + " </" + tagName + "> has no matching opening tag.");
                            isWellFormed = false;
                        } else {
                            String expectedTag = tagStack.peek();
                            if (!expectedTag.equalsIgnoreCase(tagName)) {
                                System.out.println("Error at line: " + lineNumber + " </" + tagName + "> does not match opening tag <" + expectedTag + ">");
                                isWellFormed = false;
                            } else {
                                tagStack.pop();
                            }
                        }
                    } else {
                        // Opening tag
                        tagStack.push(tagName);
                    }
                }

                // Check for tags that might span multiple lines
                if (line.contains("<") && !line.contains(">")) {
                    System.out.println("Error at line: " + lineNumber + " Unclosed tag starting with: " + 
                                      line.substring(line.indexOf("<")));
                    isWellFormed = false;
                }
            }

            // Check for unclosed tags at end of file
            while (!tagStack.isEmpty()) {
                String unclosedTag = tagStack.pop();
                System.out.println("Error: Unclosed tag <" + unclosedTag + "> at end of file.");
                isWellFormed = false;
            }

            if (isWellFormed) {
                System.out.println("XML document is constructed correctly.");
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}