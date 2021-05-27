import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

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
        System.out.println("Longest word/words: " + longestStringsArray.toString());
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
        System.out.println("Shortest word/words: " + shortestStringsArray.toString());
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

    public void countWordsToFile() throws FileNotFoundException {
        Map<String, Integer> stringIntegerMap = new HashMap<String, Integer>();
        for(String word : content){
            if(stringIntegerMap.containsKey(word)){
                stringIntegerMap.replace(word, stringIntegerMap.get(word) + 1);
            }
            else {
                stringIntegerMap.put(word, 1);
            }
        }
        Map<String,Integer> sortedMap = stringIntegerMap.entrySet().stream().sorted(Map.Entry.<String,Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
        String mapAsString = sortedMap.keySet().stream().map(key -> key + " " + sortedMap.get(key) + "\n").collect(Collectors.joining());
        System.out.println("File result.txt was generated!");
        PrintWriter outputFile = new PrintWriter("result.txt");
        outputFile.println(mapAsString);
        outputFile.close();
    }
    public Solution(String[] args) throws IOException {
        convertToString(args[0]);
    }

}
