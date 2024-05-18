import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class fileAnalyzer extends Thread {

    private String filePath;
    private BufferedReader in;
    private int wordsCounter;
    private int lettersCounter;
    private String minWord;
    private String maxWord;
    public static List<Integer> wordCounterList = Collections.synchronizedList(new ArrayList<>());
    public static List<Integer> letterCounterList = Collections.synchronizedList(new ArrayList<>());
    public static List<String> maxWordList = Collections.synchronizedList(new ArrayList<>());
    public static List<String> minWordList = Collections.synchronizedList(new ArrayList<>());

    public fileAnalyzer(String path) throws FileNotFoundException {
        filePath = path;
        FileReader fileReader = new FileReader(filePath);
        BufferedReader in = new BufferedReader(fileReader);
    }

    public void run() {
        try {
            String word;
            boolean first = true;
            while ((word = in.readLine()) != null) {
                lettersCounter += word.length();
                wordsCounter++;
                if (first) {
                    minWord = word;
                    maxWord = word;
                    first = false;
                }
                if (word.length() >= maxWord.length()) {
                    maxWord = word;
                }
                if (word.length() <= minWord.length()) {
                    minWord = word;
                }
            }
            maxWordList.add(maxWord);
            minWordList.add(minWord);
            wordCounterList.add(wordsCounter);
            letterCounterList.add(lettersCounter);
        } catch (FileNotFoundException e) {
            e.getStackTrace();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
