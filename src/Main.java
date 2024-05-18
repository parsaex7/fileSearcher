import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        int wordCounter = 0;
        int letterCounter = 0;
        int avgLetterPerWord = 0;
        String minWord = null;
        String maxWord = null;
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 1; i <= 20; i++) {
            String path = "C:/Users/hamid/Desktop/ap stuff/ap workshop/fileSearcher/src/assets/";
            path = path + "file_" + i + ".txt";
            executorService.execute(new fileAnalyzer(path));
        }

        executorService.awaitTermination(20, TimeUnit.SECONDS);

        for (int wordCNT : fileAnalyzer.wordCounterList) {
            wordCounter += wordCNT;
        }
        for (int letterCNT : fileAnalyzer.letterCounterList) {
            letterCounter += letterCNT;
        }
        avgLetterPerWord = letterCounter / wordCounter;
        for (String word : fileAnalyzer.minWordList) {
            if (minWord == null) {
                minWord = word;
            }
            if (word.length() <= minWord.length()) {
                minWord = word;
            }
        }
        for (String word : fileAnalyzer.maxWordList) {
            if (maxWord == null) {
                maxWord = word;
            }
            if (word.length() <= maxWord.length()) {
                maxWord = word;
            }
        }

        System.out.println("number of words : " + wordCounter);
        System.out.println("number of letters : " + letterCounter);
        System.out.println("average length of words : " + avgLetterPerWord);
        System.out.println("word with minimum length : " + minWord);
        System.out.println("word with maximum length : " + maxWord);
    }
}
