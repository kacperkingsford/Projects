import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        try {
            Solution solution = new Solution(args);
            System.out.println("Text contains " + solution.arraySize() + " words!");
            System.out.println("Text average word lengths is  " + solution.averageWordLength());
            solution.getLongestString();
            solution.getLShortestString();
            solution.countWordsToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
