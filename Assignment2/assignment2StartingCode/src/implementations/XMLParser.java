package implementations;

import java.io.*;
import java.util.regex.*;
import exceptions.EmptyQueueException;

public class XMLParser {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java XMLParser <filename.xml>");
            return;
        }

        String filename = args[0];
        MyStack<String> tagStack = new MyStack<>();
        MyQueue<String> errorLines = new MyQueue<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                if (line.isEmpty() || line.startsWith("<?xml") || line.startsWith("<!--")) {
                    continue;
                }

                Pattern tagPattern = Pattern.compile("<(/?)(\\w+)[^>]*(/?)>");
                Matcher matcher = tagPattern.matcher(line);

                while (matcher.find()) {
                    String slash = matcher.group(1); // "/" if closing tag
                    String tag = matcher.group(2);   // tag name
                    String selfClose = matcher.group(3); // "/" if self-closing

                    if (slash.equals("/")) {
                        if (tagStack.isEmpty() || !tagStack.peek().equals(tag)) {
                            errorLines.enqueue("Line " + lineNumber + ": Unexpected closing tag </" + tag + ">");
                        } else {
                            tagStack.pop();
                        }
                    } else if (!selfClose.equals("/")) {
                        tagStack.push(tag);
                    }
                }
            }

            while (!tagStack.isEmpty()) {
                errorLines.enqueue("Unclosed tag: <" + tagStack.pop() + ">");
            }

            if (!errorLines.isEmpty()) {
                System.out.println("Malformed XML:");
                while (!errorLines.isEmpty()) {
                    System.out.println(errorLines.dequeue());
                }
            } else {
                System.out.println("XML is well-formed.");
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (EmptyQueueException e) {
            System.out.println("Queue error: " + e.getMessage());
        }
    }
}
