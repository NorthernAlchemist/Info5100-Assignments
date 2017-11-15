package Assignment8;

import java.io.FileReader;
import java.io.IOException;
//import java.nio.file.Paths;
import java.util.Scanner;

public class FileAnalyzer {
    public static void main(String[] args) throws IOException {
//      get the current directory
//      String s = Paths.get(".").toAbsolutePath().normalize().toString();
//      System.out.println("Current relative path is: " + s);

        System.out.println("Filename: ");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        FileCounter counter = new FileCounter();
        FileReader reader = new FileReader(name);
        Scanner fileIn = new Scanner(reader);
        counter.read(fileIn);
        fileIn.close();
        reader.close();
        System.out.println("Characters: " + counter.getCharacterCount());
        System.out.println("Words: " + counter.getWordCount());
        System.out.println("Lines : " + counter.getLineCount());
        in.close();
    }
}