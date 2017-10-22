package Assignment6;

import java.io.File;
import java.io.RandomAccessFile;

public class Question2 {
    public static void main (String args[]){

    }

    public static void parse(File file) {
        RandomAccessFile input = null;
        String line = null;

        try {
            input = new RandomAccessFile(file, "r");
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
