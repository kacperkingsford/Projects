import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Solution {

    private String path;
    private String[] content;
    public void convertToString(String path) throws IOException {
        String content = Files.readString(Paths.get(path));
        this.content = content.replaceAll("\\p{Punct}", "").toLowerCase().split("\\s+"); // regex remove all punctation characters and convers to lowercase
    }

    public void getLongestString(){
        ArrayList<String> longestStringsArray = new ArrayList<String>();
        int currentMaxLength = this.content[0].length();
        for(String word : content){
            if(word.length() > currentMaxLength){ //new longest word
                longestStringsArray.clear();
                longestStringsArray.add(word);
                currentMaxLength = word.length();
            }
            if(word.length() == currentMaxLength && !longestStringsArray.contains(word)){ //if maximum length word is equal with current then save it in ArrayList to prevent case when there are more than one maximum length word
                longestStringsArray.add(word);
            }
        }
        System.out.println("Longest word/words length is: " + currentMaxLength);
        System.out.println("Longest words: " + longestStringsArray.toString());
    }

    //symetric function to getLongestString
    public void getLShortestString(){
        ArrayList<String> shortestStringsArray = new ArrayList<String>();
        int currentMinLength = this.content[0].length();
        for(String word : content){
            if(word.length() < currentMinLength){ //new longest word
                shortestStringsArray.clear();
                shortestStringsArray.add(word);
                currentMinLength = word.length();
            }
            if(word.length() == currentMinLength && !shortestStringsArray.contains(word)){ //if maximum length word is equal with current then save it in ArrayList to prevent case when there are more than one maximum length word
                shortestStringsArray.add(word);
            }
        }
        System.out.println("Shortest word/words length is: " + currentMinLength);
        System.out.println("Shortest words: " + shortestStringsArray.toString());
    }

    public int arraySize(){
        return this.content.length;
    }

    public long averageWordLength(){
        long sum = 0;
        for(String word : content){
            sum += word.length();
        }
        return sum / arraySize();

    }

    public Solution(String[] args) throws IOException {
        convertToString(args[0]);
    }

}
