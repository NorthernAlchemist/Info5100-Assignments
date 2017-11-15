package Assignment8;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


class LyricTest{
    public static void main(String[] args) throws IOException {
        LyricAnalyzer test = new LyricAnalyzer();
        //File file = new File("/Users/lifanqian/IdeaProjects/5100/Question2_test1.txt");
        //File file = new File("/Users/lifanqian/IdeaProjects/5100/Question2_test2.txt");
        //File file = new File("/Users/lifanqian/IdeaProjects/5100/Question2_test3.txt");
        File file = new File("/Users/lifanqian/IdeaProjects/5100/Question2_test4.txt");
        test.read(file);
        test.displayWords();
        System.out.println("-----------------------------------------");
        System.out.println("The number of unique words is " +test.count());
        System.out.println("The most frequent word is " + test.mostFrequentWord());
        File Lyrics = new File("/Users/lifanqian/IdeaProjects/5100/OutputTest.txt");
        //Lyrics.createNewFile();
        test.writeLyrics(Lyrics);
    }
}




public class LyricAnalyzer {
    private HashMap<String, ArrayList<Integer>> map;

    public LyricAnalyzer(){
        map = new HashMap<>();
    }

    public void read(File file) throws FileNotFoundException {
        Scanner in = new Scanner(file);
        int position = 1;
        while (in.hasNextLine()) {
            String line = in.nextLine();
            Scanner scanner = new Scanner(line);
            while (scanner.hasNext()) {
                String word = scanner.next();
                if (scanner.hasNext()) {
                    if (map.containsKey(word)) {
                        add(word, position);
                    } else {
                        map.put(word, new ArrayList<>(Arrays.asList(position)));
                    }
                    position++;
                } else {
                    if (map.containsKey(word)) {
                        add(word, -position);
                    } else {
                        map.put(word, new ArrayList<>(Arrays.asList(-position)));
                    }
                    position++;
                }
            }
            scanner.close();
        }
        in.close();
    }

    private void add(String lyricWord, int wordPosition){
        map.get(lyricWord).add(wordPosition);
    }

    public void displayWords(){
        List<String> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        System.out.println("Word             Word Position(s)");
        System.out.println("==================================");
        for (String key: keys){
            System.out.print(key);
            for (int i = 0; i < 17 - key.length(); i++){
                System.out.print(" ");
            }
            System.out.println(map.get(key));
        }
    }

    public void writeLyrics(File file) throws IOException {
        int numberOfWords = 0;
        Set<String> keys = map.keySet();

        for (String key: keys){
            numberOfWords += map.get(key).size();
        }

        String[] wordList = new String[numberOfWords + 1];
        for (int i = 0; i <wordList.length; i++){
            wordList[i] = "";
        }

        for (String key: keys){
            for (Integer position: map.get(key)){
                if (position > 0){
                    wordList[position] = key;
                } else {
                    wordList[-position] = key + '\n';
                }
            }
        }

        FileWriter writer = new FileWriter(file);
        for (String string: wordList){
            writer.write(string);
            writer.write(" ");
        }

        writer.close();
    }

    public int count(){
        List<String> keys = new ArrayList<>(map.keySet());
        return keys.size();
    }

    public String mostFrequentWord(){
        int max = -1;
        String res = new String();
        Set<String> keys = map.keySet();
        for (String key: keys){
            if (map.get(key).size() > max){
                res = key;
                max = map.get(key).size();
            }
        }
        return res;
    }

}
