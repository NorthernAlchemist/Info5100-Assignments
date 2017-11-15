package Assignment8;

import java.util.Scanner;
import java.io.IOException;
import java.util.StringTokenizer;

public class FileCounter {
    private int characterCount, wordCount, lineCount;

    public FileCounter(){
        characterCount = 0;
        wordCount = 0;
        lineCount = 0;
    }

    public void read(Scanner in) throws IOException {
        while (in.hasNextLine()){
            lineCount++;
            String line = in.nextLine();
            //assume space is also regard as one character
            characterCount += line.length();
            wordCount += new StringTokenizer(line, " ,.!?").countTokens();
        }
    }

    public int getCharacterCount() {
        return characterCount;
    }

    public int getLineCount() {
        return lineCount;
    }

    public int getWordCount() {
        return wordCount;
    }
}


