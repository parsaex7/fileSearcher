import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.IOException;

public class fileAnalyzer extends Thread {

    private String filePath;
    private BufferedReader in;
    private int wordsCounter;
    private int lettersCounter;
    private String minWord;
    private String maxWord;
    public static Set<String> words;
    public static List<Integer> wordCounterList = Collections.synchronizedList(new ArrayList<>());
    public static List<Integer> letterCounterList = Collections.synchronizedList(new ArrayList<>());
    public static List<String> maxWordList = Collections.synchronizedList(new ArrayList<>());
    public static List<String> minWordList = Collections.synchronizedList(new ArrayList<>());

    public fileAnalyzer(String path) throws FileNotFoundException {
        words = Collections.synchronizedSet(new HashSet<>());
        filePath = path;
        FileReader fileReader = new FileReader(filePath);
        in = new BufferedReader(fileReader);
    }

    public void run() {
        try {
            String word;
            boolean first = true;
            while ((word = in.readLine()) != null) {
                    words.add(word);
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
        } catch (IOException e) {
            e.getStackTrace();
        } finally{
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
