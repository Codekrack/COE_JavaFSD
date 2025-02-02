package firstweektask;

import java.io.*;
import java.util.*;

class Task9 {
    private static final List<String> KEYWORDS = Arrays.asList("ERROR", "WARNING");   
    public void analyzeLogFile(String inputFile, String outputFile) {
        Map<String, Integer> keywordCount = new HashMap<>();
        for (String keyword : KEYWORDS) {
            keywordCount.put(keyword, 0);
        }        
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {           
            String line;
            while ((line = reader.readLine()) != null) {
                for (String keyword : KEYWORDS) {
                    if (line.contains(keyword)) {
                        keywordCount.put(keyword, keywordCount.get(keyword) + 1);
                        writer.write(line);
                        writer.newLine();
                    }
                }
            }
            
            writer.write("\nSummary:\n");
            for (Map.Entry<String, Integer> entry : keywordCount.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
            
        } catch (IOException e) {
            System.out.println("Error reading or writing file: " + e.getMessage());
        }
    }  
    public static void main(String[] args) {
        Task9 analyzer = new Task9();
        analyzer.analyzeLogFile("log.txt", "output.txt");
        System.out.println("Log analysis completed. Check output.txt");
    }
}
