import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileProcessor {

    public static void main(String[] args) {

        String inputFile = "input.txt";     // File to read
        String outputFile = "output.txt";   // File to write results

        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;
        int nonEmptyLines = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {

            String line;

            // Read file line by line
            while ((line = br.readLine()) != null) {
                lineCount++;

                if (!line.trim().isEmpty()) {
                    nonEmptyLines++;
                }

                charCount += line.length();

                String[] words = line.trim().split("\\s+");
                if (!line.trim().isEmpty()) {
                    wordCount += words.length;
                }
            }

            // Write results to output file
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
                bw.write("=== File Processing Results ===\n");
                bw.write("Total Lines: " + lineCount + "\n");
                bw.write("Non-Empty Lines: " + nonEmptyLines + "\n");
                bw.write("Total Words: " + wordCount + "\n");
                bw.write("Total Characters: " + charCount + "\n");
            }

            System.out.println("Processing complete!");
            System.out.println("Results written to: " + outputFile);

        } catch (FileNotFoundException e) {
            System.out.println("Error: Input file not found (" + inputFile + ")");
        } catch (IOException e) {
            System.out.println("An I/O error occurred: " + e.getMessage());
        }
    }
}


// for compilation and execution
//javac FileProcessor.java  
//java FileProcessor