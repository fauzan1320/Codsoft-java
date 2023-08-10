import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class WordCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str;
        int count = 0;
        
        System.out.println("Enter '1' to input text manually \nEnter '2' to provide a text file \n Enter Your choice: ");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume the newline
        
        if (choice == 1) {
            System.out.println("Enter The Text:");
            str = sc.nextLine();
            count = countWords(str);
        } else if (choice == 2) {
            System.out.println("Enter the path of the text file:");
            String filePath = sc.nextLine();
            count = countWordsFromFile(filePath);
        } else {
            System.out.println("Invalid choice.");
            return;
        }

        System.out.println("Total words are " + count);
    }

    public static int countWords(String str) {
        int count = 0;
        boolean inWord = false;

        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);

            if (currentChar != ' ' && !inWord) {
                count++;
                inWord = true;
            } else if (currentChar == ' ') {
                inWord = false;
            }
        }
        return count;
    }

    public static int countWordsFromFile(String filePath) {
        int count = 0;
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            StringBuilder content = new StringBuilder();
            while (fileScanner.hasNextLine()) {
                content.append(fileScanner.nextLine());
                content.append(" "); // Add space between lines
            }
            count = countWords(content.toString());
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        return count;
    }
}
